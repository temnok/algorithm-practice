package bfs;

import org.junit.Assert;
import org.junit.Test;

public class MinPathTest {
	@Test
	public void testRandomCases() {
		for (int i = 0; i < 10_000; i++) {
			var td = new graphTestData();

			var expected = td.minPath;
			var actual = MinPath.minPath(td.graph, td.start, td.end);

			if (!expected.equals(actual)) {
				Assert.fail(String.format(
					"minPath(%s, %s, %s):\nwant %s\n got %s",
					td.graph, td.start, td.end, expected, actual
				));
			}
		}
	}
}
