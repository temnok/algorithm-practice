package bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BreadthFirstSearchTest {
	@Test
	public void testBfs() {
		var graph = List.of(
			List.of(1, 3),
			List.of(2,4),
			List.of(5),
			List.of(4,6),
			List.of(5,7),
			List.of(8),
			List.of(7),
			List.of(8),
			List.of(2)
		);
		var expected = List.of(0, 1, 3, 2, 4, 6, 5, 7, 8);

		var actual = BreadthFirstSearch.bfs(graph, 0);
		if (!actual.equals(expected)) {
			Assert.fail(String.format("dfs(%s, 0):\nwant %s\n got %s", graph, expected, actual));
		}
	}

	@Test
	public void testRandomCases() {
		for (int i = 0; i < 10_000; i++) {
			var td = new randomTestData();

			var expected = td.order;
			var actual = BreadthFirstSearch.bfs(td.graph, td.start);

			if (!expected.equals(actual)) {
				Assert.fail(String.format(
					"bfs(%s, %s):\nwant %s\n got %s",
					td.graph, td.start, expected, actual
				));
			}
		}
	}
}
