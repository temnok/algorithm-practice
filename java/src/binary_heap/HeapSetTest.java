package binary_heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HeapSetTest {
	@Test
	public void testRandomCases() {
		for (int test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			Assert.assertTrue(randomTestData.minHeapIsOK(td.heap));

			var actual = td.heap.clone();
			HeapSet.heapSet(actual, td.randomIndex, td.randomValue);

			td.heap[td.randomIndex] = td.randomValue;

			if (!randomTestData.minHeapIsOK(actual)) {
				Assert.fail(
					String.format("heapSet(%s, %s, %s): not a valid heap after the call:\n%s",
						Arrays.toString(td.heap), td.randomIndex, td.randomValue, Arrays.toString(actual)
					)
				);
			}

			if (!randomTestData.haveSameElements(actual, td.heap)) {
				Assert.fail(
					String.format("heapSet(%s, %s, %s): not same elements after the call:\n%s",
						Arrays.toString(td.heap), td.randomIndex, td.randomValue, Arrays.toString(actual)
					)
				);
			}
		}
	}
}
