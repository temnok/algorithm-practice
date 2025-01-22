package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

class randomLabyrinthTestData {
	private static final Random rand = new Random(0);

	boolean[][] labyrinth;
	int minDist;

	randomLabyrinthTestData() {
		int m = 1 + rand.nextInt(50), n = 1 + rand.nextInt(50);
		labyrinth = new boolean[m][n];
		for (var i = 0; i < m; i++) {
			for (var j = 0; j < n; j++) {
				labyrinth[i][j] = rand.nextInt(2) == 1;
			}
		}
		labyrinth[0][0] = false;
		labyrinth[m-1][n-1] = false;

		findMinDist();
	}

	private void findMinDist() {
		var queue = new LinkedList<Integer>();

		int m = labyrinth.length, n = labyrinth[0].length;
		var dist = new int[m*n];
		Arrays.fill(dist, -1);

		queue.addLast(0);
		dist[0] = 0;

		var ds = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

		while (!queue.isEmpty()) {
			var u = queue.removeFirst();
			if (u == n*m-1) break;
			int i0 = u/n, j0 = u%n;

			for (var d: ds) {
				int i = i0+d[0], j = j0+d[1];

				if (i >= 0 && i < m && j >= 0 && j < n && !labyrinth[i][j]) {
					var v = i*n + j;
					if (dist[v] < 0) {
						queue.addLast(v);
						dist[v] = dist[u]+1;
					}
				}
			}
		}

		minDist = dist[m*n-1];
	}
}
