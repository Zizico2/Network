package Network.Node;

import Network.Task.Task;

import java.util.List;

public interface Node {
    boolean isReady();

    List<Task> getTasks();

    void initialize();
}
