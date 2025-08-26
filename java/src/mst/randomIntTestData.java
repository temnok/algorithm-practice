package mst;

import org.junit.Assert;

import java.util.*;

class randomTestData {
	private static final Random rand = new Random(0);

	int n;
	int[][] edges;
	int expectedWeightSum;
	int[] par, size;

	randomTestData() {
		this.n = 1 + rand.nextInt(100);
		this.edges = new int[rand.nextInt(100)][];
		var maxWeight = 1 + rand.nextInt(100);
		for (var i = 0; i < edges.length; i++) {
			var u = rand.nextInt(n);
			var v = rand.nextInt(n);
			var w = 1 + rand.nextInt(maxWeight);
			edges[i] = new int[]{ u, v, w };
		}

		var orderedEdges = edges.clone();
		Arrays.sort(orderedEdges, Comparator.comparingInt(e -> e[2]));

		this.par = new int[n];
		this.size = new int[n];
		for (var i = 0; i < n; i++) {
			par[i] = i;
			size[i] = 1;
		}

		for (var e: orderedEdges) {
			int u = e[0], v = e[1], w = e[2];
			if (union(u, v)) {
				expectedWeightSum += w;
			}
		}
	}

	void assertAnswer(int[][] answer) {
		var sum = 0;
		var edgeSet = new HashSet<List<Integer>>();
		for (var e: edges) {
			edgeSet.add(Arrays.asList(e[0], e[1], e[2]));
		}

		for (var e: answer) {
			int u = e[0], v = e[1], w = e[2];
			if (!edgeSet.remove(Arrays.asList(e[0], e[1], e[2]))) {
				Assert.fail(String.format("minimumSpanningForest(%s, %s):\n" +
						"unexpected edge in response: %s\n" +
						"full response: %s",
					n, Arrays.deepToString(edges), Arrays.toString(e), Arrays.deepToString(answer))
				);
			}

			if (find(par[u]) != find(par[v])) {
				Assert.fail(String.format("minimumSpanningForest(%s, %s):\n" +
					"want nodes %s and %s to remain connected\n" +
					" got %s",
					n, Arrays.deepToString(edges), u, v, Arrays.deepToString(answer))
				);
			}
			sum += w;
		}

		if (sum != expectedWeightSum) {
			Assert.fail(String.format("minimumSpanningForest(%s, %s):\n" +
				"want weight sum %s\n" +
				" got weight sum %s",
				n, Arrays.deepToString(edges), expectedWeightSum, sum)
			);
		}

		for (var e: answer) {
			int u = e[0], v = e[1];
			par[u] = -1;
			par[v] = -1;
		}

		for (var i = 0; i < n; i++) {
			if (par[i] >= 0 && size[i] > 1) {
				Assert.fail(String.format("minimumSpanningForest(%s, %s):\n" +
						"want node %s to remain connected to other node(s)\n" +
						" got %s",
					n, Arrays.deepToString(edges), i, Arrays.deepToString(answer))
				);
			}
		}
	}

	private int find(int i) {
		while (i != par[i]) {
			var p = par[i];
			par[i] = par[p];
			i = p;
		}

		return i;
	}

	private boolean union(int i, int j) {
		i = find(i);
		j = find(j);
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
