package binary_heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HeapSortTest {
	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			var expected = td.array.clone();
			Arrays.sort(expected);

			var actual = td.array.clone();
			HeapSort.heapSort(actual);

			if (!Arrays.equals(actual, expected)) {
				Assert.fail(
					String.format("heapSort(%s):\nwant %s\n got %s",
						Arrays.toString(td.array),
						Arrays.toString(expected),
						Arrays.toString(actual)
					)
				);
			}
		}
	}
}
