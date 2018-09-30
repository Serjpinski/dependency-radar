package radar.graph;

import radar.project.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyGraph {

    private final Map<Project, List<Project>> dependencies;

    public DependencyGraph() {
        this.dependencies = new HashMap<>();
    }
}
