package radar.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    public static String read(File file, boolean keepNewLines) {

        try {
            return Files.lines(file.toPath()).reduce("", (a, b) -> a + b + (keepNewLines ? "\n" : ""));
        }
        catch (IOException e) {
            LogUtils.log("Failed to read file: " + file.getAbsolutePath());
            throw new RuntimeException(e);
        }
    }

    public static String read(File file) {
        return read(file, false);
    }
}
