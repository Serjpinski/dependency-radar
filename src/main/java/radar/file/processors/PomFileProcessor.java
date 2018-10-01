package radar.file.processors;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import radar.file.FileProcessor;
import radar.project.Project;
import radar.project.Prop;
import radar.util.XPathUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PomFileProcessor implements FileProcessor {

    private static final DocumentBuilder DOCUMENT_BUILDER;
    static {
        try {
            DOCUMENT_BUILDER = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void process(Project project, File file) {

        try {
            Document document = DOCUMENT_BUILDER.parse(file);

            project.addProp(Prop.POM_NAME, XPathUtils.getOne(document, "/project/name"));
            project.addProp(Prop.POM_GROUP, XPathUtils.getOne(document, "/project/groupId"));
            project.addProp(Prop.POM_ARTIFACT, XPathUtils.getOne(document, "/project/artifactId"));

            XPathUtils.get(document, "/project/dependencies/dependency").forEach(k -> {
                Map<String, Object> map = (Map<String, Object>) k;
                project.addProp(Prop.POM_DEPENDENCY, map.get("groupId") + "." + map.get("artifactId"));
            });
        }
        catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
