package Network.Task;

public interface Task {
    int getNextNode();

    int getPreviousNode();

    boolean hasFinished();

    void clock(int time);

    void initialize();
}
