package old.graphs;

import java.util.Arrays;

public class DijkstraSolution {
	public static double[] distance(Edge[][] graph, int source) {
		double[] distance = new double[graph.length];
		Arrays.fill(distance, Double.POSITIVE_INFINITY);
		distance[source] = 0;

		int[] tree = createSegmentTree(graph.length); // with index of min-distance node on the top
		for (int u = source; u >= 0; u = tree[1]) {   // tree top has index 1
			tree[tree.length/2 + u] = -1;             // remove current node index
			updateSegmentTree(tree, u, distance);
			for (Edge e: graph[u]) {
				double d = distance[u] + e.w;
				int v = e.v;
				if (distance[v] > d) {
					distance[v] = d;
					updateSegmentTree(tree, v, distance);
				}
			}
		}
		return distance;
	}

	private static int[] createSegmentTree(int n) {
		int p = Integer.highestOneBit(n);
		if (p < n) p *= 2;
		int[] tree = new int[p*2];
		for (int i = 0; i < n; i++) {
			tree[p + i] = i;
		}
		Arrays.fill(tree, p + n, tree.length, -1);
		for (int i = p-1; i > 0; i--) {
			tree[i] = Math.max(tree[i*2], tree[i*2 + 1]);
		}
		return tree;
	}

	private static void updateSegmentTree(int[] tree, int i, double[] val) {
		for (i = (tree.length/2 + i)/2; i >= 1; i /= 2) {
			int l = tree[i*2], r = tree[i*2 + 1], p = l;
			if (l < 0 || (r >= 0 && val[l] > val[r])) {
				p = r;
			}
			tree[i] = p;
		}
	}
}
