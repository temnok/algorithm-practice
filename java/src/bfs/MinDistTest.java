package bfs;

import org.junit.Assert;
import org.junit.Test;

public class MinDistTest {
	@Test
	public void testRandomCases() {
		for (int i = 0; i < 10_000; i++) {
			var td = new graphTestData();

			var expected = td.minDist;
			var actual = MinDist.minDist(td.graph, td.start, td.end);

			if (expected != actual) {
				Assert.fail(String.format(
					"minDist(%s, %s, %s):\nwant %s\n got %s",
					td.graph, td.start, td.end, expected, actual
				));
			}
		}
	}
}
