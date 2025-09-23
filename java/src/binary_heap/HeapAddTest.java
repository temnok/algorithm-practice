package binary_heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HeapAddTest {
	@Test
	public void testRandomCases() {
		Random rand = new Random(0);

		var heap = new ArrayList<Integer>();
		var counts = new HashMap<Integer, Integer>();

		for (int test = 0; test < 100_000; test++) {
			if (rand.nextInt(1000) == 0) {
				heap.clear();
				counts.clear();
			}

			var val = rand.nextInt(100);
			HeapAdd.heapAdd(heap, val);
			counts.put(val, counts.getOrDefault(val, 0) + 1);

			assertHeap(heap, counts);
		}
	}

	void assertHeap(List<Integer> heap, Map<Integer, Integer> counts) {
		var c = new HashMap<Integer, Integer>();

		for (var i = heap.size()-1; i >= 0; i--) {
			var val = heap.get(i);
			c.put(val, c.getOrDefault(val, 0) + 1);

			var j = (i-1)/2;
			var p = heap.get(j);
			if (val < heap.get(j)) {
				Assert.fail(String.format("Min heap is currently invalid:\n" +
					"Child element heap[%s]==%s is less than parent heap[%s]==%s\n\n" +
					"Heap: %s", i, val, j, p, heap));
			}
		}

		if (!c.equals(counts)) {
			Assert.fail(String.format("Heap elements is currently not as expected:\n" +
				"Want elem counts: %s\n" +
				" Got elem counts: %s\n", counts, c));
		}
	}
}
