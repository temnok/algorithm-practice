package bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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

		var actual = new ArrayList<Integer>();
		BreadthFirstSearch.bfs(graph, actual);
		if (!actual.equals(expected)) {
			Assert.fail(String.format("dfs(%s):\n got %s\nwant %s", graph, actual, expected));
		}
	}
}
