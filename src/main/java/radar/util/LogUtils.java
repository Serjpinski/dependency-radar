package radar.util;

import radar.project.Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private static final String LOG_FORMAT = "%s [ %s ] %s";

    public static void log(Project project, String line) {
        System.out.println(String.format(LOG_FORMAT, DATE_FORMAT.format(new Date()),
                project == null ? "?" : project.getName(), line));
    }

    public static void log(String line) {
        log(null, line);
    }
}
