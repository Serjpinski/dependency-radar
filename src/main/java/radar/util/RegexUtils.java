package radar.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static List<String> get(Pattern pattern, String string) {

        Matcher matcher = pattern.matcher(string);

        if (matcher.matches()) {

            List<String> groups = new ArrayList<>();

            for (int i = 1; i <= matcher.groupCount(); i++) {
                groups.add(matcher.group(i));
            }

            return groups;
        }

        return null;
    }

    public static String getOne(Pattern pattern, String string) {

        Matcher matcher = pattern.matcher(string);

        if (matcher.matches()) {
            return matcher.group(1);
        }

        return null;
    }
}
