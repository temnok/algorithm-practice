package min_task_time;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MinTaskTimeTest {
	@Test
	public void testExample() {
		Assert.assertEquals(120, MinTaskTime.minTaskTime(5,
			new int[]{20, 10, 30, 50, 40},
			new int[][]{{3, 4}, {2, 3}, {1, 0}, {2, 1}, {3, 1}, {3, 0}}));
	}

	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();
			var actual = MinTaskTime.minTaskTime(td.n, td.time, td.before);
			if (td.expected != actual) {
				Assert.fail(String.format("minTaskTime(%s, %s, %s):\nwant %s\n got %s",
					td.n, Arrays.toString(td.time), Arrays.deepToString(td.before), td.expected, actual));
			}
		}
	}
}
