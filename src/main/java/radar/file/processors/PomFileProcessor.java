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

    private static final String NAME_XPATH = "/project/name/text()";
    private static final String GROUP_XPATH = "/project/groupId/text()";
    private static final String ARTIFACT_XPATH = "/project/artifactId/text()";

    @Override
    public void process(Project project, File file) {

        try {
            Document document = DOCUMENT_BUILDER.parse(file);
            XPathUtils.xpath(document, NAME_XPATH).forEach(k -> project.addProp(Prop.POM_NAME, k));
            XPathUtils.xpath(document, GROUP_XPATH).forEach(k -> project.addProp(Prop.POM_GROUP, k));
            XPathUtils.xpath(document, ARTIFACT_XPATH).forEach(k -> project.addProp(Prop.POM_ARTIFACT, k));
        }
        catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
