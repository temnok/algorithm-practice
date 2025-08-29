package binary_heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HeapDownTest {
	@Test
	public void testRandomCases() {
		for (int test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			Assert.assertTrue(randomTestData.minHeapIsOK(td.heap));

			td.heap[td.randomIndex] += td.randomValue;
			var actual = td.heap.clone();
			HeapDown.heapDown(actual, actual.length, td.randomIndex);

			if (!randomTestData.minHeapIsOK(actual)) {
				Assert.fail(
					String.format("heapDown(%s, %s, %s): not a valid heap after the call:\n%s",
						Arrays.toString(td.heap), td.randomIndex, td.heap.length, Arrays.toString(actual)
					)
				);
			}

			if (!randomTestData.haveSameElements(actual, td.heap)) {
				Assert.fail(
					String.format("heapDown(%s, %s, %s): not same elements after the call:\n%s",
						Arrays.toString(td.heap), td.randomIndex, td.heap.length, Arrays.toString(actual)
					)
				);
			}
		}
	}
}
