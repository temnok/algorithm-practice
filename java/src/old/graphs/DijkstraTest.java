package old.graphs;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DijkstraTest {
	@Test
	public void testSimple() {
		Edge[][] graph = {
			{new Edge(0, 1, 1), new Edge(0, 2, 3), new Edge(0, 3, 5)},
			{new Edge(1, 2, 1)},
			{new Edge(2, 3, 1)},
			{},
		};
		double[] dist = DijkstraSolution.distance(graph, 0);
		double[] expected = {0, 1, 2, 3};
		Assert.assertArrayEquals(expected, dist, 0);
	}

	@Test
	public void testRandom() {
		for (int caseNo = 0; caseNo < 1000; caseNo++) {
			var graph = randomGraph(20, 0.2, 1, 100);
			int source = rand.nextInt(graph.length);
			double[] expected = floydWarshall(graph, source);
			double[] actual = DijkstraSolution.distance(graph, source);
			if (!Arrays.equals(actual, expected)) {
				Assert.fail(String.format("Merge(%s, %s):\n  Actual: %s\nExpected: %s",
					Arrays.deepToString(graph), source, Arrays.toString(actual), Arrays.toString(expected)));
			}
		}
	}

	private static double[] floydWarshall(Edge[][] graph, int source) {
		int n = graph.length;
		double[][] matrix = new double[n][n];
		for (int u = 0; u < n; u++) {
			Arrays.fill(matrix[u], Double.POSITIVE_INFINITY);
			for (Edge e: graph[u]) {
				matrix[e.u][e.v] = e.w;
			}
			matrix[u][u] = 0;
		}
		for (int x = 0; x < n; x++) {
			for (int u = 0; u < n; u++) {
				for (int v = 0; v < n; v++) {
					matrix[u][v] = Math.min(matrix[u][v], matrix[u][x] + matrix[x][v]);
				}
			}
		}
		return matrix[source];
	}

	private static Edge[][] randomGraph(int maxN, double edgeProb, int minW, int maxW) {
		int n = 1 + rand.nextInt(maxN);
		Edge[][] graph = new Edge[n][];
		for (int u = 0; u < n; u++) {
			var edges = new ArrayList<Edge>();
			for (int v = 0; v < n; v++) {
				if (v != u && rand.nextDouble() < edgeProb) {
					edges.add(new Edge(u, v, minW + rand.nextInt(maxW-maxW + 1)));
				}
			}
			graph[u] = edges.toArray(new Edge[]{});
		}
		return graph;
	}



	private static Random rand = new Random(1);
}
