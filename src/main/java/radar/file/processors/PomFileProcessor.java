package radar.file.processors;

import radar.file.FileProcessor;
import radar.project.Project;
import radar.util.FileUtils;
import radar.util.LogUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PomFileProcessor implements FileProcessor {

    private static final Pattern NAME_PATTERN = Pattern.compile(".*<name>([^<]+)</name>.*");

    @Override
    public void process(Project project, File file) {

        String content = FileUtils.read(file);

        Matcher nameMatcher = NAME_PATTERN.matcher(content);
        if (nameMatcher.matches()) {
            String name = nameMatcher.group(1);
            LogUtils.log(project, "Detected name from POM file: " + name);
        }
    }
}
