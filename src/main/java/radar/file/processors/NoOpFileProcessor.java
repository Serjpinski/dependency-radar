package radar.file.processors;

import radar.file.FileProcessor;
import radar.project.Project;

import java.io.File;

public class NoOpFileProcessor implements FileProcessor {

    @Override
    public void process(Project project, File file) {

    }
}
