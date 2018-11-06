package Network.Node;

import Network.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class NodeClass implements Node, NodeInCreation {

    private final List<Task> tasks;

    private final List<Task> previousTasks;

    private NodeClass() {
        this.tasks = new ArrayList<>();
        this.previousTasks = new ArrayList<>();
    }

    public static NodeClass createNode() {
        return new NodeClass();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addPreviousTask(Task task) {
        previousTasks.add(task);
    }

    @Override
    public boolean isReady() {
        for (Task task : previousTasks) {
            if (!task.hasFinished())
                return false;
        }
        return true;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void initialize() {
        for (Task task : tasks)
            task.initialize();
    }
}
