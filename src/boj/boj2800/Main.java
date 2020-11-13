package boj.boj2800;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        solution(input);

    }

    public static void solution(String input) {
        Nodes nodes = Nodes.create(input);
        for (Nodes removedParenthesisNodes : nodes.getRemovedParenthesis()) {
            System.out.println(removedParenthesisNodes);
        }
    }
}

class Nodes implements Comparable<Nodes> {
    private List<Node> nodes;
    private int maxIndex;

    private Nodes(List<Node> nodes, int maxIndex) {
        this.nodes = nodes;
        this.maxIndex = maxIndex;
    }

    public static Nodes create(String input) {
        List<Node> nodes = new ArrayList<>();
        int maxIndex = 1;
        int index = 1;
        Stack<Integer> stack = new Stack<>();

        for (String s : input.split("")) {
            if (s.equals("(")) {
                if (maxIndex < index) {
                    maxIndex = index;
                }
                stack.push(index);
                nodes.add(new Node(Node.Type.Parenthesis, String.valueOf(index++)));

                continue;
            }

            if (s.equals(")")) {
                nodes.add(new Node(Node.Type.Parenthesis, String.valueOf(stack.pop())).setIsRight());
                continue;
            }

            nodes.add(new Node(Node.Type.Formula, s));
        }

        return new Nodes(nodes, maxIndex);
    }

    public List<Nodes> getRemovedParenthesis() {
        List<Nodes> removedParenthesisNodes = new ArrayList<>();

        for (int i = 1; i <= maxIndex; i++) {
            Combinations combinations = Combinations.createBy(maxIndex, i);
            ListIterator<List<Integer>> combinationsIter = combinations.listIterator();

            while (combinationsIter.hasNext()) {
                List<Integer> combination = combinationsIter.next();
                List<Node> copiedNodes = copy();

                for (int item : combination) {
                    copiedNodes = copiedNodes.stream()
                            .filter(node -> !node.equals(new Node(Node.Type.Parenthesis, String.valueOf(item))))
                            .collect(Collectors.toList());
                }

                removedParenthesisNodes.add(new Nodes(copiedNodes, maxIndex));
            }
        }

        Collections.sort(removedParenthesisNodes);

        return removedParenthesisNodes.stream().distinct().collect(Collectors.toList());
    }

    public List<Node> copy() {
        List<Node> copy = new ArrayList<>();

        for (Node node : nodes) {
            copy.add(node.copy());
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Node node : nodes) {
            sb.append(node);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodes nodes = (Nodes) o;
        return toString().equals(nodes.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public int compareTo(Nodes o) {
        return toString().compareTo(o.toString());
    }
}

class Node {
    enum Type {
        Formula, Parenthesis
    }

    private Type type;
    private String value;
    private boolean isRight = false;

    public Node(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    private Node(Type type, String value, boolean isRight) {
        this.type = type;
        this.value = value;
        this.isRight = isRight;
    }

    public Node copy() {
        return new Node(type, value, isRight);
    }

    public Node setIsRight() {
        isRight = true;
        return this;
    }

    @Override
    public String toString() {
        return type == Type.Formula ? value : !isRight ? "(" : ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return type == node.type &&
                Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}

class Combinations {
    private List<List<Integer>> combinations = new ArrayList<>();

    public static Combinations createBy(int n, int r) {
        Combinations combination = new Combinations();

        combination.init(n, r);

        return combination;
    }

    private void init(int n, int r) {
        Stack<Integer> combinationElements = new Stack<>();
        pick(combinationElements, n, r + 1);
    }

    public ListIterator<List<Integer>> listIterator() {
        return combinations.listIterator();
    }

    private void pick(Stack<Integer> st, int n, int r) {
        if (r == 1) {
            printPick(st);

            return;
        }

        for (int current = st.isEmpty() ? 1 : st.lastElement() + 1; current <= n; current++) {
            st.push(current);
            pick(st, n, r - 1);
            st.pop();
        }

    }

    private void printPick(Stack<Integer> combinationElements) {
        List<Integer> combination = new ArrayList<>(combinationElements);

        combinations.add(Collections.unmodifiableList(combination));
    }

    @Override
    public String toString() {
        return "Combinations{" +
                "combinations=" + combinations +
                '}';
    }
}