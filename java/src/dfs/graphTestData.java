package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class graphTestData {
	private static final Random rand = new Random(0);

	List<List<Integer>> graph;
	int start;
	List<Integer> order1 = new ArrayList<>(), order2 = new ArrayList<>();

	graphTestData() {
		int n = 1 + rand.nextInt(50), maxAdj = rand.nextInt(50);
		graph = new ArrayList<>();
		for (int u = 0; u < n; u++) {
			var adj = new ArrayList<Integer>();

			var m = rand.nextInt(1 + maxAdj);
			for (int i = 0; i < m; i++) {
				int v = rand.nextInt(n);
				adj.add(v);
			}

			graph.add(adj);
		}

		start = rand.nextInt(n);
		walk1();
		walk2();
	}

	private void walk1() {
		var stack = new LinkedList<Integer>();
		var visited = new boolean[graph.size()];

		stack.push(this.start);
		visited[this.start] = true;

		while (!stack.isEmpty()) {
			var u = stack.pop();
			order1.add(u);

			for (var v: graph.get(u)) {
				if (!visited[v]) {
					stack.push(v);
					visited[v] = true;
				}
			}
		}
	}

	private void walk2() {
		var stack = new LinkedList<Integer>();
		var visited = new boolean[graph.size()];

		stack.push(this.start);
		visited[this.start] = true;

		while (!stack.isEmpty()) {
			var u = stack.pop();
			order2.add(u);

			for (var v: graph.get(u).reversed()) {
				if (!visited[v]) {
					stack.push(v);
					visited[v] = true;
				}
			}
		}
	}
}