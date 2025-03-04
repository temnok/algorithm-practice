package max_path;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MaxPathLengthTest {
	@Test
	public void testExample() {
		Assert.assertEquals(120, MaxPathLength.maxPathLength(5,
			new int[][]{{3, 1, 100}, {1, 0, 20}, {3, 2, 30}, {2, 1, 10}, {3, 4, 110}}));
	}

	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();
			var actual = MaxPathLength.maxPathLength(td.n, td.edges);
			if (td.expected != actual) {
				Assert.fail(String.format("maxPathLength(%s, %s):\nwant %s\n got %s",
					td.n, Arrays.deepToString(td.edges), td.expected, actual));
			}
		}
	}
}
