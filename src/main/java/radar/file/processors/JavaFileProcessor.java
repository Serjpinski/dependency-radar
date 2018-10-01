package radar.file.processors;

import radar.file.FileProcessor;
import radar.project.Project;
import radar.project.Prop;
import radar.util.FileUtils;
import radar.util.RegexUtils;

import java.io.File;
import java.util.regex.Pattern;

public class JavaFileProcessor implements FileProcessor {

    private static final Pattern FEIGN_CLIENT = Pattern.compile(".*@FeignClient\\(([^)]+)\\).*");
    private static final Pattern FEIGN_CLIENT_NAME_1 = Pattern.compile("\"([^\"]+)\"");
    private static final Pattern FEIGN_CLIENT_NAME_2 = Pattern.compile(".*name[ \t]*=[ \t]*\"([^\"]+)\".*");

    @Override
    public void process(Project project, File file) {

        String content = FileUtils.read(file);

        processFeignClient(project, RegexUtils.getOne(FEIGN_CLIENT, content));
    }

    private void processFeignClient(Project project, String value) {

        if (value != null) {
            project.addProp(Prop.FEIGN_CLIENT_NAME, RegexUtils.getOne(FEIGN_CLIENT_NAME_1, value));
            project.addProp(Prop.FEIGN_CLIENT_NAME, RegexUtils.getOne(FEIGN_CLIENT_NAME_2, value));
        }
    }
}
