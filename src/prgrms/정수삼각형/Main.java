package prgrms.정수삼각형;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int result;
        int[][] input1 = new int[][]{
                {7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}
        };

        result = new Solution().solution(input1);

        System.out.println(result);
    }
}

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        Triangle t = new Triangle();

        for (int[] row : triangle) {
            t.addRow(row);
        }

        System.out.println(t);

        System.out.println(t.getMax());

        return answer;
    }
}

class Triangle {
    private List<Row> rows = new ArrayList<>();

    public Triangle addRow(int[] row) {
        Row newRow = Row.create(row);

        if (!rows.isEmpty()) {
            rows.get(rows.size() - 1).addChild(newRow);
        }
        rows.add(newRow);

        return this;
    }

    public int getMax() {
        Row top = rows.get(0);

        Node topNode = top.get(0);

        int result = topNode.getNumber();

        while (topNode.hasChild()) {
            topNode = topNode.getMaxChild();
            System.out.println(topNode.getNumber());
            result += topNode.getNumber();
        }

        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "rows=" + rows +
                '}';
    }
}

class Row {
    private List<Node> nodes;

    public Row(List<Node> nodes) {
        this.nodes = nodes;
    }

    public static Row create(int[] row) {
        List<Node> nodes = new ArrayList<>();

        for (int nodeNumber : row) {
            nodes.add(new Node(nodeNumber));
        }

        return new Row(nodes);
    }

    public int size() {
        return nodes.size();
    }

    public Node get(int index) {
        return nodes.get(index);
    }

    public void addChild(Row child) {
        List<Node> childNodes = child.nodes;

        if (size() + 1 != childNodes.size()) {
            throw new IllegalArgumentException("개수 오류 : parent size : " + size() + ", child size : " + childNodes.size());
        }

        for (int i = 0; i < size(); i++) {
            Node parent = nodes.get(i);
            parent.addChild(childNodes.get(i));
            parent.addChild(childNodes.get(i + 1));
        }
    }

    @Override
    public String toString() {
        return "Row{" +
                "nodes=" + nodes +
                '}' + System.lineSeparator();
    }
}

class Node implements Comparable<Node> {
    private int number;
    private int currentMaxNumber;
    private Queue<Node> child = new PriorityQueue<>();

    public Node(int number) {
        this.number = number;
        currentMaxNumber = number;
    }

    public Node addChild(Node node) {
        child.add(node);

        currentMaxNumber = number + child.peek().number;

        return this;
    }

    public int getNumber() {
        return number;
    }

    public boolean hasChild() {
        return !child.isEmpty();
    }

    public Node getMaxChild() {
        return child.peek();
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", currentMaxNumber=" + currentMaxNumber +
                ", child=" + child.peek() +
                '}';
    }

    public int getMax() {
        return number + child.peek().getMax();
    }

    @Override
    public int compareTo(Node o) {
        int l;
        int r;

        return Integer.compare(currentMaxNumber, o.currentMaxNumber) * -1;
    }
}