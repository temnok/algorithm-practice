package counting_sort;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

public class HeightCheckerTest {
	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(2);

	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			var actual = HeightChecker.checkHeight(td.array.clone(), td.maxValue + 1);

			Assert.assertEquals(String.format("Initial array: %s\n  Expected array:%s\n  Expected unsorted: %s\n " +
					"Actual: %s\n",
				Arrays.toString(td.array),
				Arrays.toString(td.expectedArray),
				"" + td.getUnsortedCount(),
				"" + actual), td.getUnsortedCount(), actual);
		}
	}
}
