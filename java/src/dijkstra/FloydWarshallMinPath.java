package dijkstra;

import java.util.Arrays;
import java.util.Map;

public class FloydWarshallMinPath {
	public static final int[][] floydWarshallMinPath(int n, int[][] edges) {
//		throw new UnsupportedOperationException("TODO");

		var dist = new int[n][n];
		for (var i = 0; i < n; i++) {
			Arrays.fill(dist[i], -1);
			dist[i][i] = 0;
		}

		for (var e: edges) {
			int i = e[0], j = e[1], d = e[2];
			if (dist[i][j] < 0) {
				dist[i][j] = d;
			} else {
				dist[i][j] = Math.min(dist[i][j], d);
			}
		}

		for (var k = 0; k < n; k++) {
			for (var i = 0; i < n; i++) {
				for (var j = 0; j < n; j++) {
					var d = dist[i][k] + dist[k][j];
					if (dist[i][k] >= 0 && dist[k][j] >= 0 && (dist[i][j] < 0 || d < dist[i][j])) {
						dist[i][j] = d;
					}
				}
			}
		}

		return dist;
	}
}

