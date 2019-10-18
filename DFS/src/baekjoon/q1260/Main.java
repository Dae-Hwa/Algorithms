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
		int n = 4;
		Graph graph = new Graph(n);

		graph.setAdjacent(1, 2);
		graph.setAdjacent(1, 3);
		graph.setAdjacent(1, 4);
		graph.setAdjacent(2, 4);
		graph.setAdjacent(3, 4);
		graph.dfs(1);
		
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
	}

	@Override
	public String toString() {
		return "Graph [graph=" + Arrays.toString(graph) + "]";
	}
	public ArrayList<Integer> dfs(int index) {
		result.add(index);
		executeDfs(index);
		return result;
	}
	public void executeDfs(int index) {
		if (!graph[index-1].isEmpty()) {
			pushAdjacent(stack, graph[index-1]);
		}
		System.out.println(stack);
		
		if (!stack.isEmpty()) {
			int vertax = stack.pop();
			if (!walked[vertax-1]) {
				walked[vertax-1]=true;
				result.add(vertax);
				executeDfs(vertax);
			}
		}
	}

	private void pushAdjacent(LinkedList<Integer> stack, LinkedList<Integer> adjacent) {
		
		for (int i = adjacent.size()-1; i >= 0; i--) {
			stack.push(adjacent.get(i));
		}
	}
	
	public ArrayList<Integer> getResult(){
		return result;
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
