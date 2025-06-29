package merge_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MergeSortIntsTest {
	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomIntTestData();

			var actual = td.array.clone();
			MergeSortInts.mergeSortInts(actual);

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
