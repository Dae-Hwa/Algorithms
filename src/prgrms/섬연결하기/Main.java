package prgrms.섬연결하기;

import java.util.*;

class Solution {

    public int solution(int n, int[][] costs) {
        List<Edge> edges = new ArrayList<>();

        for (int[] cost : costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }

        UnionFind unionFind = new UnionFind(n);

        int sum = 0;

        for (Edge edge : edges) {
            if (unionFind.find(edge.getLeft()) != unionFind.find(edge.getRight())) {
                sum += edge.getWeight();
                unionFind.union(edge.getLeft(), edge.getRight());
            }
        }

        return sum;
    }
}

class Edge implements Comparable<Edge> {
    private int left;
    private int right;
    private int weight;

    public Edge(int left, int right, int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}

class UnionFind {
    private int[] parent;
    private int size;

    public UnionFind(int size) {
        this.size = size;
        init();
    }

    private void init() {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = -1; // -1로 초기화한다.
        }
    }

    public int find(int x) {
        if (parent[x] < 0) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (parent[rootY] * -1 < parent[rootX] * -1) {
            parent[rootX] += parent[rootY];
            parent[rootY] = rootX;
            return true;
        }

        parent[rootY] += parent[rootX];
        parent[rootX] = rootY;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                        4,
                        new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}
                ))
        ));

        for (Object obj : inputs) {
            List<Object> input = (List<Object>) obj;

            int result = new Solution().solution((int) input.get(0), (int[][]) input.get(1));
            System.out.println(result);
        }
    }
}
