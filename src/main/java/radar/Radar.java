package radar;

import radar.project.Project;
import radar.project.ProjectProcessor;
import radar.util.LogUtils;

import java.io.File;
import java.util.Objects;

public class Radar {

    public static void main(String[] args) {

        String basePath = "C:/Users/Serjpinski/Desktop/projects";

        File baseFolder = new File(basePath);

        if (baseFolder.isDirectory()) {

            for (File file : Objects.requireNonNull(baseFolder.listFiles())) {

                if (file.isDirectory()) {

                    Project project = new Project(file.getName(), file.getAbsolutePath());
                    LogUtils.log(project, "Detected project");
                    ProjectProcessor.process(project);
                }
            }
        }
        else {
            throw new RuntimeException("Input projects folder path is not a directory: " + basePath);
        }
    }
}
