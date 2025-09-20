package bfs;

import java.util.ArrayDeque;
import java.util.Arrays;

public class MinDistLabyrinth {
	// minDistLabyrinth should return minimal length of a path from
	// top-left corner to bottom-right corner of the MxN labyrinth (M > 0, N > 0)
	// or -1 if no such path exists. Path should consists of empty cells (with "false" value) only.
	// Both start and end cells will be empty. Only horizontal and vertical moves are allowed.
	// Diagonal moves are NOT allowed.
	public static int minDistLabyrinth(boolean[][] lab) {
//		throw new UnsupportedOperationException("TODO");

		int m = lab.length, n = lab[0].length;

		var dist = new int[m*n];
		Arrays.fill(dist, -1);

		var q = new ArrayDeque<Integer>();
		q.add(0);
		dist[0] = 0;

		var ds = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
		while (!q.isEmpty()) {
			var u = q.removeFirst();
			int i0 = u/n, j0 = u%n;

			for (var d: ds) {
				int i = i0+d[0], j = j0+d[1], v = i*n + j;
				if (0 <= i && i < m && 0 <= j && j < n && !lab[i][j] && dist[v] < 0) {
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}

		return dist[m*n-1];
	}
}
