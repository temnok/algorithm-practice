package binary_heap;

import java.util.List;

public class HeapAdd {
	// heapAdd should add new element 'val' to a binary min-heap
	public static void heapAdd(List<Integer> heap, int val) {
//		throw new UnsupportedOperationException("TODO");
		heap.add(val);
		heapUp(heap, heap.size()-1);
	}

	private static void heapUp(List<Integer> heap, int i) {
		var val = heap.get(i);

		for (var j = (i-1)/2; i > 0 && val < heap.get(j); i = j, j = (j-1)/2) {
			heap.set(i, heap.get(j));
		}

		heap.set(i, val);
	}
}
