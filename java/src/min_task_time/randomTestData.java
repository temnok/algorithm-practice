package min_task_time;

import java.util.ArrayList;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int n;
	int[] time;
	int[][] before;
	int expected;

	randomTestData() {
		while (!randomTestDataSuccess());
	}

	boolean randomTestDataSuccess() {
		n = 1 + rand.nextInt(100);
		time = new int[n];
		for (var u = 0; u < n; u++) {
			time[u] = 1 + rand.nextInt(100);
		}

		var edges = new ArrayList<int[]>();
		var mat = new int[n+1][n+1];
		for (int i = 0, m = rand.nextInt(n*2); i < m; i++) {
			int u = rand.nextInt(n), v = rand.nextInt(n);
			if (mat[u][v] > 0) continue;

			edges.add(new int[] { u, v });
			mat[u][v] = time[u];
		}
		for (int u = 0; u < n; u++) {
			edges.add(new int[] { u, n });
			mat[u][n] = time[u];
		}

		for (var m = 0; m <= n; m++) {
			for (var u = 0; u <= n; u++) {
				if (mat[u][m] == 0) continue;

				for (var v = 0; v <= n; v++) {
					if (mat[m][v] == 0) continue;

					var w = mat[u][m] + mat[m][v];
					if (-w < -mat[u][v]) {
						mat[u][v] = w;
					}
				}
			}
		}

		expected = 0;
		for (var u = 0; u <= n; u++) {
			if (mat[u][u] > 0) {
				return false;
			}

			for (var v = 0; v <= n; v++) {
				if (mat[u][v] > expected) {
					expected = mat[u][v];
				}
			}
		}

		before = edges.subList(0, edges.size() - n).toArray(new int[][]{});

		return true;
	}
}
