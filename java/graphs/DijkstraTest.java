package graphs;

import org.junit.*;

public class DijkstraTest {
	@Test(timeout = 1000)
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
}
