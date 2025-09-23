package binary_heap;

import java.util.List;

public class HeapRemove {
	// heapRemove should remove a minimal element from binary min-heap and return its value.
	// This method won't be called on an empty heap.
	public static int heapRemove(List<Integer> heap) {
//		throw new UnsupportedOperationException("TODO");

		var val = heap.get(0);

		if (heap.size() > 1) {
			heap.set(0, heap.removeLast());
			heapDown(heap, 0);
		} else {
			heap.clear();
		}

		return val;
	}

	public static void heapDown(List<Integer> heap, int i) {
		var val = heap.get(i);
		var n = heap.size();

		for (var j = i*2+1; j < n; i = j, j = j*2+1) {
			if (j+1 < n && heap.get(j+1) < heap.get(j)) {
				j++;
			}

			if (val <= heap.get(j)) break;

			heap.set(i, heap.get(j));
		}

		heap.set(i, val);
	}
}
