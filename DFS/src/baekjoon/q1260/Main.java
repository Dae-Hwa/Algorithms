package baekjoon.q1260;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int n = 5;
		int m = 3;
		Graph graph = new Graph(n);

		graph.setAdjacent(5, 4);
		graph.setAdjacent(5, 2);
		graph.setAdjacent(1, 2);
		graph.setAdjacent(3, 4);
		graph.setAdjacent(3, 1);

		for (int i = 0; i < n; i++) {
			graph.sortGraph(i);
		}

//		graph.dfs(m);

		graph.bfs(m);
		System.out.println(graph.getResult());
	}
}

class Graph {
	private ArrayList<Integer>[] graph;
	private int[][] arrGraph;
	private ArrayDeque<Integer> stack;
	private ArrayDeque<Integer> queue;
	private boolean[] walked;
	private ArrayList<Integer> result;

	public Graph(int n) {
		graph = (ArrayList<Integer>[]) Array.newInstance(ArrayList.class, n);

		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}

	}

	public void setAdjacent(int vertax, int adjacent) {
		graph[vertax - 1].add(adjacent);
		graph[adjacent - 1].add(vertax);
	}


	public void sortGraph(int i) {
		graph[i].sort(null);
	}

	public ArrayList<Integer> dfs(int index) {
		stack = new ArrayDeque<Integer>();
		result = new ArrayList<Integer>();
		walked = new boolean[graph.length];

		executeDfs(index);
		return result;
	}

	public ArrayList<Integer> bfs(Integer index) {
		queue = new ArrayDeque<Integer>();
		result = new ArrayList<Integer>();
		walked = new boolean[graph.length];

		while (index != null) {
			index = executeBfs(index);
		}

		return result;
	}

	public ArrayList<Integer> getResult() {
		return result;
	}

	private void executeDfs(int index) {
		if (!walked[index - 1]) {
			if (graph[index - 1].isEmpty()) {
				return;
			}

			pushAdjacent(stack, graph[index - 1]);
			walked[index - 1] = true;
			result.add(index);
		}

		if (!stack.isEmpty()) {
			int vertax = stack.pop();
			executeDfs(vertax);
		}
	}

	private Integer executeBfs(int index) {
		if (!walked[index - 1] && !graph[index - 1].isEmpty()) {
			enqueueAdjacent(queue, graph[index - 1]);

			walked[index - 1] = true;
			result.add(index);
		}

		return queue.poll();
	}

	private void pushAdjacent(ArrayDeque<Integer> stack, ArrayList<Integer> adjacent) {
		for (int i = adjacent.size() - 1; i >= 0; i--) {
			stack.push(adjacent.get(i));
		}
	}

	private void enqueueAdjacent(ArrayDeque<Integer> queue, ArrayList<Integer> adjacent) {
		for (int i = 0; i < adjacent.size(); i++) {
			queue.offer(adjacent.get(i));
		}
	}

	@Override
	public String toString() {
		return "Graph [graph=" + Arrays.toString(graph) + "]";
	}

}
