package binary_heap;

public class HeapUp {
	// heapUp should move element up at index i in a binary min-heap
	public static void heapUp(int[] heap, int i) {
//		throw new UnsupportedOperationException("TODO");

		var val = heap[i];

		for (var j = (i-1)/2; i > 0 && heap[j] > val; i = j, j = (j-1)/2) {
			heap[i] = heap[j];
		}

		heap[i] = val;
	}
}
