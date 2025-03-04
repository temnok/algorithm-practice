package topo_sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	List<List<Integer>> graph;
	int[][] adj;
	boolean hasCycle;

	randomTestData() {
		var n = 1 + rand.nextInt(100);
		graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}

		var m = 1 + rand.nextInt(n*2);
		var ins = new int[n];
		for (int i = 0; i < m; i++) {
			var u = rand.nextInt(n);
			var v = rand.nextInt(n);
			graph.get(u).add(v);
			ins[v]++;
		}

		adj = new int[n][];
		for (int u = 0; u < n; u++) {
			adj[u] = new int[graph.get(u).size()];
			for (int i = 0; i < adj[u].length; i++) {
				adj[u][i] = graph.get(u).get(i);
			}
		}

		var q = new int[n];
		var r = 0;
		for (int v = 0; v < n; v++) {
			if (ins[v] == 0) {
				q[r++] = v;
			}
		}

		for (int l = 0; l < r; l++) {
			for (var v: adj[q[l]]) {
				if (--ins[v] == 0) {
					q[r++] = v;
				}
			}
		}

		hasCycle = r < n;
	}
}