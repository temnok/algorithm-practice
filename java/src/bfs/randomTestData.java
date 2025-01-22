package bfs;

import java.util.*;

class randomTestData {
	private static final Random rand = new Random(0);

	List<List<Integer>> graph;
	int start, end;
	int minDist;
	List<Integer> minPath = new LinkedList<>();
	List<Integer> order = new ArrayList<>();

	randomTestData() {
		int n = 1 + rand.nextInt(50), maxAdj = rand.nextInt(50);
		graph = new ArrayList<>();
		for (var u = 0; u < n; u++) {
			var adj = new ArrayList<Integer>();

			var m = rand.nextInt(1 + maxAdj);
			for (var i = 0; i < m; i++) {
				int v = rand.nextInt(n);
				adj.add(v);
			}

			graph.add(adj);
		}

		start = rand.nextInt(n);
		end = rand.nextInt(n);
		walk();
		minDist = minPath.size() - 1;
	}

	private void walk() {
		var prev = new int[graph.size()];
		Arrays.fill(prev, -1);

		order.addLast(start);
		prev[start] = start;

		for (int i = 0; i < order.size(); i++) {
			var u = order.get(i);
			for (var v: graph.get(u)) {
				if (prev[v] < 0) {
					order.addLast(v);
					prev[v] = u;
				}
			}
		}

		if (prev[end] < 0) {
			return;
		}

		for (var v = end;; v = prev[v]) {
			minPath.addFirst(v);
			if (v == prev[v]) break;
		}
	}
}