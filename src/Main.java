import Network.Network;
import Network.NetworkClass;
import Network.Node.Node;
import Network.Node.NodeClass;
import Network.Node.NodeInCreation;
import Network.Task.Task;

import java.util.List;
import java.util.Scanner;

import static Network.Node.NodeClass.createNode;
import static Network.Task.TaskClass.createTask;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String type = in.next().trim();
        Network network = createNetwork(in);
        network.initialize();
        network.run();
        outputInfo(network, type);
        System.out.println("Done!");

    }

    private static void outputInfo(Network network, String type) {
        switch (type) {
            case "A":
                List<Task> maxWorkload = network.getMaxWorkload();
                System.out.println(maxWorkload.size());
                for (Task task : maxWorkload)
                    System.out.println((task.getPreviousNode() + 1) + " " + (task.getNextNode() + 1));
                break;
            case "B":
        }
    }


    private static Network createNetwork(Scanner in) {
        NodeInCreation[] nodes = createNodes(in);
        int nTasks = in.nextInt();
        addTasks(in, nodes, nTasks);
        return NetworkClass.createNetwork((Node[]) nodes);
    }

    private static NodeInCreation[] createNodes(Scanner in) {
        NodeInCreation[] nodes = new NodeClass[in.nextInt()];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = createNode();
        }

        return nodes;
    }

    private static void addTasks(Scanner in, NodeInCreation[] nodes, int nTasks) {
        for (int i = 0; i < nTasks; i++) {
            int previousNode = in.nextInt() - 1;
            int nextNode = in.nextInt() - 1;
            Task task = createTask(previousNode, nextNode, in.nextInt());
            nodes[previousNode].addTask(task);
            nodes[nextNode].addPreviousTask(task);
           // System.out.println((task.getPreviousNode() + 1) + " " + (task.getNextNode() + 1));
        }
    }
}
