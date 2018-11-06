package Network.Node;

import Network.Task.Task;

public interface NodeInCreation {
    void addTask(Task task);

    void addPreviousTask(Task task);
}
