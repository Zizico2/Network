package Network;

import Network.Node.Node;
import Network.Task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NetworkClass implements Network {

    private final Node[] nodes;
    private final List<Task> runningTasks;
    private final List<Task> maxWorkload;
    private final List<Task> postponableTasks;
    private int time = -1;

    private NetworkClass(Node[] nodes) {
        this.nodes = nodes;
        this.maxWorkload = new ArrayList<>();
        this.runningTasks = new ArrayList<>();
        this.postponableTasks = new ArrayList<>();
    }


    public static NetworkClass createNetwork(Node[] nodes) {
        return new NetworkClass(nodes);
    }

    //pre: has a Node with previousTasks.isEmpty()
    @Override
    public void initialize() {
        time = 0;
        //noinspection ConstantConditions
        this.runningTasks.addAll(getInitialNode().getTasks());
        for (Node node : nodes) {
            node.initialize();
        }
    }

    private Node getInitialNode() {
        for (Node node : nodes) {
            if (node.isReady())
                return node;
        }
        return null;
    }

    //pre: !runningTasks.isEmpty()
    @Override
    public void run() {
        while (time < 21) {
            clock();
            System.out.println(runningTasks.size());
            List<Task> auxRunningTasks = new ArrayList<>(runningTasks);
            for (Task task : auxRunningTasks) {
                List<Task> tasksToRemove = getFinishedTasks();
                runningTasks.addAll(getTasksToAdd(tasksToRemove));
                runningTasks.removeAll(tasksToRemove);
                if (runningTasks.isEmpty())
                    break;
            }
            updateMaxRunningTasks();
        }
    }

        @Override
        public int getNumberOfPostponableTasks () {
            return postponableTasks.size();
        }

        @Override
        public List<Task> getMaxWorkload() {
            return maxWorkload;
        }

        private void updateMaxRunningTasks () {
            if (maxWorkload.size() < runningTasks.size()) {
                maxWorkload.clear();
                maxWorkload.addAll(runningTasks);
            }
        }

        private List<Task> getTasksToAdd (List < Task > tasksToRemove) {
            List<Task> tasksToStart = new ArrayList<>();
            for (Node node : getReadyNodes(tasksToRemove))
                tasksToStart.addAll(node.getTasks());
            return tasksToStart;
        }

        private List<Task> getFinishedTasks () {
            List<Task> tasksToRemove = new ArrayList<>();
            for (Task task : runningTasks)
                if (task.hasFinished()) {
                    tasksToRemove.add(task);
                }
            return tasksToRemove;
        }

        private List<Node> getReadyNodes (List < Task > tasksToRemove) {
            List<Node> readyNodes = new ArrayList<>();
            for (Task task : tasksToRemove) {
                Node node = nodes[task.getNextNode()];
                if (!node.isReady())
                    addPostponableTask(task);
                else if (!readyNodes.contains(node))
                    readyNodes.add(node);
            }
            return readyNodes;
        }

        private void addPostponableTask (Task task){
            postponableTasks.add(task);
        }

        private void clock () {
            time++;
        }
    }
