package counting_sort;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;
import quick_sort.QuickSort;

import java.util.Arrays;

public class CountingSortTest {
	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(2);

	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			var actual = CountingSort.sort(td.array.clone(), td.maxValue + 1);

			if (!Arrays.equals(td.expectedArray, actual)) {
				Assert.fail(
					String.format("sortInts(%s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(td.array),
						Arrays.toString(actual),
						Arrays.toString(td.expectedArray)
					)
				);
			}
		}
	}
}
