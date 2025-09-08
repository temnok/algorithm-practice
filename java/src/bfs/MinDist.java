package bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class MinDist {
	// minDist should return the minimal distance between start and end nodes in an arbitrary graph
	// represented as an adjacency list, or -1 if there is no path from start to end nodes
	public static int minDist(List<List<Integer>> graph, int start, int end) {
//		throw new UnsupportedOperationException("TODO");

		var dist = new int[graph.size()];
		Arrays.fill(dist, -1);

		var q = new ArrayDeque<Integer>();
		q.add(start);
		dist[start] = 0;

		while (!q.isEmpty()) {
			var u = q.removeFirst();
			for (var v: graph.get(u)) {
				if (dist[v] < 0) {
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}

		return dist[end];
	}
}
