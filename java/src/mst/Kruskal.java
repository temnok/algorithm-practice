package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {
	// minimumSpanningForest should return subset of edges that form
	// "Minimum Spanning Forest" of the input *undirected* graph represented
	// by n nodes and provided list of edges. Each edge is represented by three
	// integers (u, v, w), where u and v are node indexes, and w is edge weight.
	//
	// Restrictions:
	// * Kruskal's algorithm should be used for implementation
	// * input graph may be unconnected
	// * edge (u, v, w) is equivalent to edge (v, u, w)
	// * n > 0, 0 <= u < n, 0 <= v < n, w > 0
	// * there can be multiple edges between same pair of nodes with same or different weights
	// * order of returned edges is ignored
	// * no duplicates are allowed in the returned list
	//
	public static int[][] minimumSpanningForest(int n, int[][] edges) {
//		throw new UnsupportedOperationException("TODO");

		Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));

		var par = new int[n];
		var size = new int[n];

		for (var i = 0; i < n; i++) {
			par[i] = i;
			size[i] = 1;
		}

		var ans = new ArrayList<int[]>();
		for (var e: edges) {
			int u = e[0], v = e[1], w = e[2];
			if (union(par, size, u, v)) {
				ans.add(e);
			}
		}

		return ans.toArray(new int[][]{});
	}


	private static int find(int[] par, int i) {
		while (i != par[i]) {
			var p = par[i];
			par[i] = par[p];
			i = p;
		}

		return i;
	}

	private static boolean union(int[] par, int[] size, int i, int j) {
		i = find(par, i);
		j = find(par, j);
		if (i == j) return false;

		if (size[i] < size[j]) {
			par[i] = j;
			size[j] += size[i];
		} else {
			par[j] = i;
			size[i] += size[j];
		}

		return true;
	}
}

