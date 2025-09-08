package bfs;

import java.util.*;

public class MinPath {
	// minPath should return list of nodes of the minimal path from start to end nodes in an arbitrary graph
	// represented as an adjacency list, or empty list if there is no such path
	public static List<Integer> minPath(List<List<Integer>> graph, int start, int end) {
//		throw new UnsupportedOperationException("TODO");

		var prev = new int[graph.size()];
		Arrays.fill(prev, -1);

		var q = new ArrayDeque<Integer>();
		q.add(start);
		prev[start] = start;

		while (!q.isEmpty()) {
			var u = q.removeFirst();
			for (var v: graph.get(u)) {
				if (prev[v] < 0) {
					prev[v] = u;
					q.add(v);
				}
			}
		}

		if (prev[end] < 0) {
			return Collections.emptyList();
		}

		var ans = new ArrayList<Integer>();
		for (var v = end;; v = prev[v]) {
			ans.add(v);
			if (v == start) break;
		}

		return ans.reversed();
	}
}
