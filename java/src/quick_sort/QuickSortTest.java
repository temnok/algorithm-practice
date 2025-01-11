package quick_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {
	@Test
	public void testSortInts() {
		for (var test = 0; test < 1_000; test++) {
			var td = quickSortTestData.generate();

			var actual = td.array.clone();
			QuckSort.sortInts(actual);

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
