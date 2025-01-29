package binary_heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HeapInitTest {
	@Test
	public void testRandomCases() {
		for (int test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			Assert.assertTrue(randomTestData.minHeapIsOK(td.heap));

			var actual = td.array.clone();
			HeapInit.heapInit(actual);

			if (!randomTestData.minHeapIsOK(actual)) {
				Assert.fail(
					String.format("heapInit(%s): not a valid heap after the call:\n%s",
						Arrays.toString(td.array), Arrays.toString(actual)
					)
				);
			}

			if (!randomTestData.haveSameElements(actual, td.array)) {
				Assert.fail(
					String.format("heapInit(%s): not same elements after the call:\n%s",
						Arrays.toString(td.array), Arrays.toString(actual)
					)
				);
			}
		}
	}
}
