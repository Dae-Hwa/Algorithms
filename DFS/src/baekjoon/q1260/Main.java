package baekjoon.q1260;

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
		int n = 4;
		Graph graph = new Graph(n);

		graph.setAdjacent(1, 2);
		graph.setAdjacent(1, 3);
		graph.setAdjacent(1, 4);
		graph.setAdjacent(2, 4);
		graph.setAdjacent(3, 4);

		System.out.println(Arrays.deepToString(graph.graph));
	}
}

class Graph {
	LinkedList<Vertax>[] graph;

	public Graph(int n) {
		graph = new LinkedList[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new LinkedList<Vertax>();
		}

	}

	public void setAdjacent(int vertax, int adjacent) {
		graph[vertax-1].add(new Vertax(adjacent, false));
	}

	@Override
	public String toString() {
		return "Graph [graph=" + Arrays.toString(graph) + "]";
	}

}

class Vertax {
	int vertax;
	boolean walked;

	public Vertax(int vertax, boolean walked) {
		this.vertax = vertax;
		this.walked = walked;
	}

	@Override
	public String toString() {
		return "Vertax [vertax=" + vertax + ", walked=" + walked + "]";
	}

}
