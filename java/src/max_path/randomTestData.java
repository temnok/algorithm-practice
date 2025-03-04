package max_path;

import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int n;
	int[][] edges;
	int expected;

	randomTestData() {
		n = 1 + rand.nextInt(100);
		edges = new int[rand.nextInt(n*2)][3];
		var mat = new int[n][n];
		for (int i = 0; i < edges.length; i++) {
			int u = rand.nextInt(n), v = rand.nextInt(n), w = 1 + rand.nextInt(100);
			w = Math.max(w, mat[u][v]);
			edges[i] = new int[] { u, v, w };
			mat[u][v] = w;
		}

		for (var m = 0; m < n; m++) {
			for (var u = 0; u < n; u++) {
				if (mat[u][m] == 0) continue;

				for (var v = 0; v < n; v++) {
					if (mat[m][v] == 0) continue;

					var w = mat[u][m] + mat[m][v];
					if (-w < -mat[u][v]) {
						mat[u][v] = w;
					}
				}
			}
		}

		for (var u = 0; u < n; u++) {
			if (mat[u][u] > 0) {
				expected = -1;
				break;
			}

			for (var v = 0; v < n; v++) {
				if (mat[u][v] > expected) {
					expected = mat[u][v];
				}
			}
		}
	}
}
