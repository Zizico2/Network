package Network;

import Network.Task.Task;

import java.util.List;

public interface Network {
    void initialize();

    void run();

    int getNumberOfPostponableTasks();

    List<Task> getMaxWorkload();
}
