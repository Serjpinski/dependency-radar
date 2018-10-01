package radar.project;

import radar.file.FileType;
import radar.util.LogUtils;

import java.io.File;
import java.util.*;

public class Project {

    private final String name;
    private final File root;
    private final Map<FileType, List<File>> files;
    private final Map<Prop, List<Object>> props;

    public Project(String name, String path) {

        this.name = name;

        this.root = new File(path);

        if (!root.isDirectory()) {
            throw new RuntimeException("Input path is not a directory");
        }

        files = new HashMap<>();
        props = new HashMap<>();
    }

    public void addFile(FileType fileType, File file) {
        //LogUtils.log(this, "Detected " + fileType + " file: " + file.getName());
        files.computeIfAbsent(fileType, k -> new ArrayList<>()).add(file);
    }

    public void addProp(Prop prop, Object value) {

        if (value != null) {
            LogUtils.log(this, "Added prop " + prop + " = " + value);
            props.computeIfAbsent(prop, k -> new ArrayList<>()).add(value);
        }
    }

    public String getName() {
        return name;
    }

    public File getRoot() {
        return root;
    }

    public Map<FileType, List<File>> getFiles() {
        return files;
    }

    public Map<Prop, List<Object>> getProps() {
        return props;
    }
}
