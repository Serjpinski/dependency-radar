package radar.util;

import java.io.File;

public class GitUtils {

    public static void cloneOrPull(String basePath, String project, String cloneUrl) {

        File projectFolder = new File(basePath + "/" + project);

        if (projectFolder.exists()) {
            CommandUtils.execute("cd " + basePath + "; git pull");
        }
        else {
            if (projectFolder.mkdir()) {
                CommandUtils.execute("cd " + basePath + "; git clone " + cloneUrl);
            }
            else {
                throw new RuntimeException("Could not create directory " + projectFolder.getAbsolutePath());
            }
        }
    }

    public static void cloneOrPullGithub(String basePath, String project, String organization) {
        cloneOrPull(basePath, project, getGithubCloneUrl(organization, project));
    }

    private static String getGithubCloneUrl(String organization, String project) {
        return "https://github.com/" + organization + "/" + project + ".git";
    }
}
