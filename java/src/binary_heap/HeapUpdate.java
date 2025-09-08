package binary_heap;

public class HeapUpdate {
	// heapSet should change element at index i in a binary min-heap
	// to the provided value and update the heap properly
	public static void heapUpdate(int[] heap, int i, int val) {
//		throw new UnsupportedOperationException("TODO");

		var oldVal = heap[i];
		heap[i] = val;
		if (val < oldVal) {
			HeapUp.heapUp(heap, i);
		} else {
			HeapDown.heapDown(heap, heap.length, i);
		}
	}
}
