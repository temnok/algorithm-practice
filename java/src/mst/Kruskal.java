package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
		Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);

		DS ds = new DS(n);
		List<Edge> forest = new ArrayList<>();

		for (int [] e : edges) {
			if (ds.union(e[0], e[1])) {
				forest.add(new Edge(e[0], e[1], e[2]));
			}
		}

		int [][] result = new int[forest.size()][3];
		for (int i = 0; i < forest.size(); i++) {
			result[i] = new int[] {forest.get(i).i, forest.get(i).j, forest.get(i).d};
		}

		return result;
	}

	private static class Edge {
		int i;
		int j;
		int d;

		public Edge(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}

	private static class DS {
		int[] par, size;

		DS(int n) {
			par = IntStream.range(0, n).toArray();
			size = new int[n];
			Arrays.fill(size, 1);
		}

		int find(int i) {
			while (par[i] != i) {
				int j = i;
				i = par[i];
				par[j] = par[i];
			}

			return i;
		}

		boolean union(int i, int j) {
			int parI = find(i);
			int parJ = find(j);

			if (parI == parJ) {
				return false;
			}

			if (size[parI] > size[parJ]) {
				par[parJ] = parI;
				size[parI] += size[parJ];
			} else {
				par[parI] = parJ;
				size[parJ] += size[parI];
			}

			return true;
		}
	}
}

