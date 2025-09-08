package max_path;

import java.util.ArrayList;
import java.util.List;

public class MaxPathLength {
	// maxPathLength should return max path length within the graph with n nodes and provided edges,
	// or -1 if the graph contains a cycle and thus the max path length is infinity.
	// edges contain triples [u, v, d], each describing *directed* edge from node u to v with distance d (d > 0)
	// between nodes u and v.
	//
	// There can be multiple edges between same one or two nodes with different distances.
	// For example, if n = 5, edges = [[3, 1, 100], [1, 0, 20], [3, 2, 30], [2, 1, 10], [3, 4, 110]] then
	// result should be 120.
	//
 	//               100
  	//          -------------
	//         |             |
 	//     20  v  10     30  |  110
 	//  *<-----*<-----*<-----*------>*
	//  0      1      2      3       4
	//
	public static int maxPathLength(int n, int[][] edges) {
//		throw new UnsupportedOperationException("TODO");

		List<Edge>[] adj = new List[n];
		for (var u = 0; u < n; u++) {
			adj[u] = new ArrayList<>();
		}

		var ins = new int[n];

		for (var e: edges) {
			int u = e[0], v = e[1], d = e[2];
			adj[u].add(new Edge(v, d));

			ins[v]++;
		}

		// topo sort
		var order = new int[n];
		var m = 0;

		for (var u = 0; u < n; u++) {
			if (ins[u] == 0) {
				order[m++] = u;
			}
		}

		for (var i = 0; i < m; i++) {
			var u = order[i];
			for (var e: adj[u]) {
				if (--ins[e.v] == 0) {
					order[m++] = e.v;
				}
			}
		}

		if (m < n) {
			return -1;
		}

		// topo-order walk
		var dist = new int[n];
		var maxDist = 0;
		for (var u: order) {
			for (var e: adj[u]) {
				dist[e.v] = Math.max(dist[e.v], dist[u] + e.d);
				maxDist = Math.max(maxDist, dist[e.v]);
			}
		}

		return maxDist;
	}
}

class Edge {
	final int v, d;

	Edge(int v, int d) {
		this.v = v;
		this.d = d;
	}
}
