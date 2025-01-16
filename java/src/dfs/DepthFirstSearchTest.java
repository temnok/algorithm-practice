package dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchTest {
	@Test
	public void testDfs() {
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
		var expected1 = List.of(0, 1, 2, 5, 8, 4, 7, 3, 6);
		var expected2 = List.of(0, 3, 6, 7, 8, 2, 5, 4, 1);

		var actual = new ArrayList<Integer>();
		DepthFirstSearch.dfs(graph, actual);
		if (!actual.equals(expected1) && !actual.equals(expected2)) {
			Assert.fail(String.format("dfs(%s):\n got %s\nwant %s or %s", graph, actual, expected1, expected2));
		}
	}
}
