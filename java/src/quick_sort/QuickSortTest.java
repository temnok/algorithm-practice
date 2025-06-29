package quick_sort;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

public class QuickSortTest {
	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(2);

	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			var actual = td.array.clone();
			QuickSort.quickSortInts(actual);

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

	@Test
	public void testPerformanceForLargeZeroArray() {
		var arr = new int[100_000_000];
		QuickSort.quickSortInts(arr);
	}


	@Test
	public void testPerformanceForLargeSparceArray() {
		var arr = new int[100_000_000];
		for (int i = 0; i < arr.length; i++) {
			if (i%1000 == 0) {
				arr[i] = arr.length-i;
			}
		}

		QuickSort.quickSortInts(arr);
	}
}
