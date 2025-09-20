package dijkstra;

import java.util.Arrays;

public class BellmanFordMinPath {
	public static int[] bellmanFordPath(int n, int [][] edges, int start) {
//		throw new UnsupportedOperationException();

		var dist = new int[n];
		Arrays.fill(dist, -1);

		dist[start] = 0;

		for (var i = n-1; i > 0; i--) {
			for (var e: edges) {
				int u = e[0], v = e[1], w = e[2], d = dist[u] + w;
				if (dist[u] >= 0 && (dist[v] < 0 || d < dist[v])) {
					dist[v] = d;
				}
			}
		}

		return dist;
	}
}
