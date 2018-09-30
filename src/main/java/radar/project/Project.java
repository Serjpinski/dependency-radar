package radar.project;

import radar.file.FileType;

import java.io.File;
import java.util.*;

public class Project {

    private final String name;
    private final Set<String> nameAliases;
    private final File root;
    private final Map<FileType, List<File>> files;

    public Project(String name, String path) {

        this.name = name;

        this.nameAliases = new HashSet<>();
        nameAliases.add(name);

        this.root = new File(path);

        if (!root.isDirectory()) {
            throw new RuntimeException("Input path is not a directory");
        }

        files = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Set<String> getNameAliases() {
        return nameAliases;
    }

    public File getRoot() {
        return root;
    }

    public Map<FileType, List<File>> getFiles() {
        return files;
    }
}
