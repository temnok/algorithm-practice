package dijkstra;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DijkstraMinPathTest {
	@Test
	public void testExample() {
		var expected = new int[]{0, 30, 10, -1};
		var actual = DijkstraMinPath.dijkstraMinPath(4, new int[][]{{0, 2, 10}, {0, 1, 100}, {2, 1, 20}}, 0);
		Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
	}

	@Test
	public void testRandomCases() {
		for (int i = 0; i < 10_000; i++) {
			var td = new randomTestData();

			var actual = DijkstraMinPath.dijkstraMinPath(td.n, td.edges, td.start);
			if (!Arrays.equals(actual, td.expected)) {
				Assert.fail(String.format(
					"dijkstraMinPath(%s, %s, %s):\nwant %s\n got %s",
					td.n, Arrays.deepToString(td.edges), td.start, Arrays.toString(td.expected), Arrays.toString(actual)
				));
			}
		}
	}
}
