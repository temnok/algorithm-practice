package graphs;

import java.util.Arrays;

public class DijkstraSolution {
	public static double[] distance(Edge[][] graph, int source) {
		int n = graph.length;
		double[] dist = new double[n];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[source] = 0;

		int[] tree = createSegmentTree(n);
//		System.out.printf("dist=%s, tree=%s\n", Arrays.toString(dist), Arrays.toString(tree));
		for (int u = source; u >= 0; u = tree[1]) {
			tree[tree.length/2 + u] = -1;
			updateSegmentTree(tree, u, dist);
//			System.out.printf("u=%s, tree=%s\n", u, Arrays.toString(tree));
			for (Edge e: graph[u]) {
				int v = e.v;
				double d = dist[u] + e.w;
				if (dist[v] > d) {
					dist[v] = d;
					updateSegmentTree(tree, v, dist);
//					System.out.printf("v=%s, dist=%s, tree=%s\n", v, Arrays.toString(dist), Arrays.toString(tree));
				}
			}
		}
		return dist;
	}

	private static int[] createSegmentTree(int n) {
		int p = Integer.highestOneBit(n);
		if (p < n) {
			p <<= 1;
		}
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
		//System.out.printf("i=%s, dist=%s, tree=%s\n", i, Arrays.toString(val), Arrays.toString(tree));
		for (i = (tree.length/2 + i)/2; i >= 1; i /= 2) {
			int l = tree[i*2], r = tree[i*2 + 1], p = l;
			if (l < 0 || (r >= 0 && val[l] > val[r])) {
				p = r;
			}
			//System.out.printf("i=%s, l=%s, r=%s, p=%s\n", i, l, r, p);
			tree[i] = p;
		}
		//System.out.printf("out: tree=%s\n", Arrays.toString(tree));
	}
}
