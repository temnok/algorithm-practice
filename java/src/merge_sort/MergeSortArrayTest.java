package merge_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSortArrayTest {
	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomStringTestData();

			var actual = td.array.clone();
			MergeSortArray.mergeSortArray(actual, Comparator.comparingInt(String::length));

			if (!Arrays.equals(td.expectedArray, actual)) {
				Assert.fail(
					String.format("sortArray(%s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(td.array),
						Arrays.toString(actual),
						Arrays.toString(td.expectedArray)
					)
				);
			}
		}
	}
}
