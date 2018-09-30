package radar.file;

import radar.project.Project;

import java.io.File;

public interface FileProcessor {

    void process(Project project, File file);
}
