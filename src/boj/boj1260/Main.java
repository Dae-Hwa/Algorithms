package boj.boj1260;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line1 = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = line1[0];
        int M = line1[1];
        int V = line1[2];

        Nodes nodes = Nodes.create(N);

        for (int i = 0; i < M; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            nodes.link(edge[0] - 1, edge[1] - 1);
        }

        nodes.sort();

        Stack<Node> dfs = new Stack<>();
        dfs.push(nodes.get(V - 1));

        StringBuilder sb = new StringBuilder();

        while (!dfs.isEmpty()) {
            Node cur = dfs.pop();

            if (cur.distance != -1) {
                continue;
            }

            if (cur.before != null) {
                cur.distance = cur.before.distance + 1;
            } else {
                cur.distance = 0;
            }

            sb.append(cur.index)
                    .append(" ");

            for (int i = cur.linked.size() - 1; 0 <= i; i--) {
                Node next = cur.linked.get(i);

                if (next.distance == -1) {
                    next.before = cur;
                    dfs.push(next);
                }
            }
        }

        System.out.println(sb);

        nodes.clear();
        Queue<Node> bfs = new ArrayDeque<>();
        bfs.offer(nodes.get(V - 1));
        nodes.get(V - 1).distance = 0;
        sb = new StringBuilder();

        while (!bfs.isEmpty()) {
            Node cur = bfs.poll();

            sb.append(cur.index)
                    .append(" ");

            for (Node next : cur.linked) {
                if (next.distance == -1) {
                    next.distance = cur.distance + 1;
                    bfs.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}

class Nodes {
    private List<Node> nodes;

    public Nodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public static Nodes create(int size) {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            nodes.add(new Node(i + 1));
        }

        return new Nodes(nodes);
    }

    public Node get(int index) {
        return nodes.get(index);
    }

    public Nodes link(int indexA, int indexB) {
        Node nodeA = nodes.get(indexA);
        Node nodeB = nodes.get(indexB);

        nodeA.link(nodeB);
        nodeB.link(nodeA);

        return this;
    }

    public void sort() {
        for (Node node : nodes) {
            Collections.sort(node.linked);
        }
    }

    public void clear() {
        for (Node node : nodes) {
            node.distance = -1;
            node.before = null;
        }
    }
}

class Node implements Comparable<Node> {
    List<Node> linked = new ArrayList<>();
    int index;
    int distance = -1;
    Node before;

    public Node(int index) {
        this.index = index;
    }

    public Node link(Node node) {
        linked.add(node);

        return this;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(index, o.index);
    }
}
