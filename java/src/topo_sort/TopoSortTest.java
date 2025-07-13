package topo_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TopoSortTest {
	@Test
	public void testExample() {
		Assert.assertArrayEquals(new int[] {3, 0, 2, 1}, TopoSort.topoSort(new int[][]{{2, 1}, {}, {1}, {0, 2}}));

		Assert.assertArrayEquals(new int[] {}, TopoSort.topoSort(new int[][]{{2, 1}, {3}, {1}, {0, 2}}));
		Assert.assertArrayEquals(new int[] {}, TopoSort.topoSort(new int[][]{{0}}));
		Assert.assertArrayEquals(new int[] {}, TopoSort.topoSort(new int[][]{{1}, {0}}));
	}

	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();

			var actual = TopoSort.topoSort(td.adj);

			if (td.hasCycle) {
				Assert.assertEquals(0, actual.length);
				continue;
			}

			var n = td.graph.size();
			Assert.assertEquals(n, actual.length);

			var order = new int[n];
			for (var i = 0; i < n; i++) {
				order[actual[i]] = i;
			}

			for (var u = 0; u < n; u++) {
				for (var v: td.graph.get(u)) {
					if (!(order[u] < order[v])) {
						Assert.fail(String.format("topoSort(%s):\n  Node %s should come before node %s in the result!\n  Result: %s",
							td.graph, u, v, Arrays.toString(actual)));
					}
				}
			}
		}
	}
}
