package baekjoon.q1260;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
 * 1{2 3 4}
2 {4}
3 {4}
++dfs
stack<1>
	++pushAdjacent
정점안에있는건 역순으로 넣어야함 4->3->2
stack.push(1.pop) // stack<2,3,4>
1=true
stack.push(2.pop) // stack<4,3,4>
2=true
stack.push(4.pop)
 */
public class Main {

	public static void main(String[] args) {
		int n = 5;
		Graph graph = new Graph(n);

		graph.setAdjacent(5, 4);
		graph.setAdjacent(5, 2);
		graph.setAdjacent(1, 2);
		graph.setAdjacent(3, 4);
		graph.setAdjacent(3, 1);

		System.out.println(graph);

		graph.dfs(3);

		System.out.println(graph.getResult());
	}
}

class Graph {
	private LinkedList<Integer>[] graph;
	private LinkedList<Integer> stack = new LinkedList<>();
	private boolean[] walked;
	private ArrayList<Integer> result = new ArrayList<>();

	public Graph(int n) {
		graph = new LinkedList[n];
		walked = new boolean[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new LinkedList<Integer>();
		}

	}

	public void setAdjacent(int vertax, int adjacent) {
		graph[vertax - 1].add(adjacent);
		graph[adjacent - 1].add(vertax);

		graph[vertax - 1].sort(null);
		graph[adjacent - 1].sort(null);
	}

	public ArrayList<Integer> dfs(int index) {
		result.add(index);
		executeDfs(index);
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

	private void executeBfs(int index) {
		if (!walked[index - 1]) {
			if (graph[index - 1].isEmpty()) {
				return;
			}

			pushAdjacent(queue, graph[index - 1]);
			walked[index - 1] = true;
			result.add(index);
		}
	}

	private void pushAdjacent(ArrayDeque<Integer> stack, ArrayList<Integer> adjacent) {
		adjacent.sort(null);
		for (int i = adjacent.size() - 1; i >= 0; i--) {
			stack.push(adjacent.get(i));
		}
	}

	@Override
	public String toString() {
		return "Graph [graph=" + Arrays.toString(graph) + "]";
	}

}
