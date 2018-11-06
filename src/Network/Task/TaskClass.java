package Network.Task;

public class TaskClass implements Task {

    private final int duration;

    private final int previousNode;

    private final int nextNode;

    private int elapsedTime = -1;


    private TaskClass(int previousNode, int nextNode, int duration) {
        this.duration = duration;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    public static TaskClass createTask(int previousNode, int nextNode, int duration) {
        return new TaskClass(previousNode, nextNode, duration);
    }

    @Override
    public int getNextNode() {
        return nextNode;
    }

    @Override
    public int getPreviousNode() {
        return previousNode;
    }

    @Override
    public boolean hasFinished() {
        return elapsedTime == 1;
    }

    @Override
    public void clock(int time) {
        elapsedTime += time;
        if (elapsedTime == duration)
            finish();
    }


    @Override
    public void initialize() {
        elapsedTime = 0;
    }

    private void finish() {
        elapsedTime = -1;
    }
}
