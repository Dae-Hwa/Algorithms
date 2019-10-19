package baekjoon.q1260;

public class Test {
	public static void main(String[] args) {
		int n = 4;
		int m = 1;
		Graph graph = new Graph(n);

		graph.setAdjacent(1, 2);
		graph.setAdjacent(1, 3);
		graph.setAdjacent(1, 4);
		graph.setAdjacent(2, 4);
		graph.setAdjacent(3, 4);

		for (int i = 0; i < n; i++) {
			graph.sortGraph(i);
		}

		graph.dfs(m);

		graph.bfs(m);
		System.out.println(graph.getResult());
	}
}
