package binary_heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HeapRemoveTest {
	@Test
	public void testRandomCases() {
		Random rand = new Random(0);

		for (var test = 0; test < 100_000; test++) {
			var heap = new ArrayList<Integer>();
			var counts = new TreeMap<Integer, Integer>();

			for (var n = 1 + rand.nextInt(100); heap.size() < n;) {
				var val = rand.nextInt(20);
				counts.put(val, counts.getOrDefault(val, 0) + 1);

				heapAdd(heap, val);
			}

			while (!heap.isEmpty()) {
				var got = HeapRemove.heapRemove(heap);
				var want = counts.firstKey();
				var c = counts.get(want);
				if (c > 1) {
					counts.put(want, c-1);
				} else {
					counts.remove(want);
				}

				if (got != want) {
					Assert.fail(String.format("heapRemove returned unexpected value!\n" +
						"Want %s\n Got: %s\n\nHeap: %s\nExpected elem counts:%v",
						want, got, heap, counts));
				}
			}
		}
	}

	private static void heapAdd(List<Integer> heap, int val) {
		var i = heap.size();
		heap.add(val);

		for (var j = (i-1)/2; i > 0 && val < heap.get(j); i = j, j = (j-1)/2) {
			heap.set(i, heap.get(j));
		}

		heap.set(i, val);
	}
}
