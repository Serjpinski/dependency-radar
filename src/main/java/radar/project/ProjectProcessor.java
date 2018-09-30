package radar.project;

import radar.file.FileType;
import radar.util.LogUtils;

import java.io.File;
import java.util.Objects;

public class ProjectProcessor {

    public static void process(Project project) {
        processFolder(project, project.getRoot());
    }

    private static void processFolder(Project project, File folder) {

        for (File file : Objects.requireNonNull(folder.listFiles())) {

            if (file.isFile()) {

                FileType fileType = detectFileType(file);

                if (fileType != null) {
                    LogUtils.log(project, "Detected " + fileType + " file: " + file.getName());
                    fileType.getFileProcessor().process(project, file);
                }
            }
            else if (file.isDirectory()) {
                processFolder(project, file);
            }
        }
    }

    private static FileType detectFileType(File file) {

        String name = file.getName();

        if (name.endsWith(".java")) {
            return FileType.JAVA;
        }

        if (name.endsWith(".py")) {
            return FileType.PY;
        }

        if (name.endsWith(".js")) {
            return FileType.JS;
        }

        if (name.equals("pom.xml")) {
            return FileType.POM;
        }

        if (name.equals("application.yml")) {
            return FileType.APP_YML;
        }

        if (name.equals("bootstrap.yml")) {
            return FileType.BOOT_YML;
        }

        return null;
    }
}
