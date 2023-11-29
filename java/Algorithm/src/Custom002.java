import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


class Node {
    int value = 0;
    Node outNode = null;
    int enterNodeCount = 0;
}

public class Custom002 {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);

        int T = Integer.parseInt(br.readLine());

        for (int testNum = 1; testNum <= T; testNum++) {
            int[] inputData = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = inputData[0], k = inputData[1];

            String[][] connections = new String[n][];
            for (int i = 0; i < n; i++) {
                connections[i] = new String[2];
                connections[i] = br.readLine().split(" ");
            }

            System.out.printf("#%d: %d\n", testNum, solution(n, k, connections));
        }

        br.close();
        ir.close();
    }

    private static int solution(int n, int k, String[][] connections) {
        HashMap<String, Node> nodes = new HashMap<>();
        for (String[] connection : connections) {
            String parent = connection[0], child = connection[1];
            nodes.computeIfAbsent(parent, key -> new Node()).enterNodeCount += 1;
            nodes.computeIfAbsent(child, key -> new Node()).outNode = nodes.get(connection[0]);
        }


        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodes.values()) {
            if (node.enterNodeCount == 0) {
                node.value = 1;
                queue.add(node);
            }
        }

        int totalWeight = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            totalWeight += currentNode.value;

            if (currentNode.outNode != null) {
                currentNode.outNode.value += currentNode.value;
                if (--currentNode.outNode.enterNodeCount == 0) queue.add(currentNode.outNode);
            }
        }

        return k / totalWeight;
    }
}
