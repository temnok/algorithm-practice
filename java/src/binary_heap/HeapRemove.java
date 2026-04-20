package binary_heap;

import java.util.List;

public class HeapRemove {
	// heapRemove should remove a minimal element from binary min-heap and return its value.
	// This method won't be called on an empty heap.
	public static int heapRemove(List<Integer> heap) {
		if (heap.size() == 1) {
			return heap.removeFirst();
		}

		int val = heap.set(0, heap.removeLast());
		heapDown(heap, heap.size(), 0);

		return val;
	}

	private static void heapDown(List<Integer> heap, int n, int i) {

	}
}
