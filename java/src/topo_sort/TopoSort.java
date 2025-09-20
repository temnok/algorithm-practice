package topo_sort;

public class TopoSort {
	// topoSort should perform topological sorting of a directed graph represented by its adjacency list.
	// The provided graph can contain duplicate links or links from a node to itself.
	// If graph is not a DAG (i.e. contains cycles) then the empty list should be returned.
	// Otherwise, returned array should contain node numbers as in the following example:
	//
	// Input:
	//
	// Adjacency list: [[2, 1], [], [1], [0, 2]]
	//
	//         0
	//    -----*<----
	//   |     |    |
	//   v     v    |
	//   *<---*<----*
	//   1    2    3
	//
 	// Expected output: [3, 0, 2, 1]
	//
	public static int[] topoSort(int[][] adjacencyList) {
//		throw new UnsupportedOperationException("TODO");

		var n = adjacencyList.length;
		var visited = new int[n];
		var order = new int[n];

		var m = 0;
		for (var i = 0; i < n; i++) {
			m = recurse(adjacencyList, i, visited, order, m);
			if (m < 0) {
				return new int[] {};
			}
		}

		for (int l = 0, r = n-1; l < r; l++, r--) {
			var tmp = order[l]; order[l] = order[r]; order[r] = tmp;
		}
		return order;
	}

	private static int recurse(int[][] adj, int u, int[] visited, int[] order, int m) {
		if (visited[u] > 0) {
			return m;
		}

		if (visited[u] < 0) {
			return -1;
		}

		visited[u] = -1;

		for (var v: adj[u]) {
			m = recurse(adj, v, visited, order, m);
			if (m < 0) return m;
		}

		visited[u] = 1;

		order[m++] = u;
		return m;
	}
}
