package topo_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SafeNodesTest {
	@Test
	public void testExample() {
		Assert.assertArrayEquals(new int[]{0, 1, 2, 3}, SafeNodes.getSafeNodes(new int[][]{{2, 1}, {}, {1}, {0, 2}})
			.stream().mapToInt(Integer::intValue).toArray());

		Assert.assertArrayEquals(new int[]{}, SafeNodes.getSafeNodes(new int[][]{{2, 1}, {3}, {1}, {0, 2}})
			.stream().mapToInt(Integer::intValue).toArray());
		Assert.assertArrayEquals(new int[]{}, SafeNodes.getSafeNodes(new int[][]{{0}})
			.stream().mapToInt(Integer::intValue).toArray());
		Assert.assertArrayEquals(new int[]{}, SafeNodes.getSafeNodes(new int[][]{{1}, {0}})
			.stream().mapToInt(Integer::intValue).toArray());
	}

	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();

			List<Integer> safe = SafeNodes.getSafeNodes(td.adj);
			Set<Integer> all = IntStream.range(0, td.graph.size()).mapToObj(Integer::valueOf).collect(Collectors.toSet());
			Set<Integer> notSafe = new HashSet<>(all);
			notSafe.removeAll(safe);

			if (td.hasCycle) {
				for (int i : all) {
					if (notSafe.contains(i)) {
						Assert.assertFalse(String.format("For data - %s, actual - %s, i - %s, node is safe", td.graph,
								safe, i),
							isSafe(td, new HashSet<>(), i));
					} else {
						Assert.assertTrue(String.format("For data - %s, actual - %s, i - %s, node is not safe", td.graph,
								safe, i),
							isSafe(td, new HashSet<>(), i));
					}
				}
			} else {
				Assert.assertEquals(safe.size(), all.size());
				Assert.assertTrue(safe.containsAll(all));
			}
		}
	}

	private boolean isSafe(randomTestData td, Set<Integer> path, int n) {
		if (path.contains(n)) {
			return false;
		}

		for (int i : td.graph.get(n)) {
			Set<Integer> childPath = new HashSet<>(path);
			childPath.add(n);
			if (!isSafe(td, childPath, i)) {
				return false;
			}
		}

		return true;
	}
}
