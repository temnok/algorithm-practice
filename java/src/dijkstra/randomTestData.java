package dijkstra;

import java.util.Arrays;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int n, start;
	int[][] edges;
	int[] expected;

	randomTestData() {
		n = 1 + rand.nextInt(100);
		start = rand.nextInt(n);
		edges = new int[rand.nextInt(n*2)][];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new int[] { rand.nextInt(n), rand.nextInt(n), 1 + rand.nextInt(100) };
		}

		expected = new int[n];
		Arrays.fill(expected, -1);
		expected[start] = 0;

		for (int count = n-1; count > 0; count--) {
			for (var edge: edges) {
				int u = edge[0], v = edge[1], dist = edge[2];
				if (expected[u] >= 0 && (expected[v] < 0 || expected[u] + dist < expected[v])) {
					expected[v] = expected[u] + dist;
				}
			}
		}
	}
}