package binary_heap;

public class HeapDown {
	// heapDown should move element down at index i in a binary min-heap with length n
	// 0 < n <= heap.length
	public static void heapDown(int[] heap, int n, int i) {
//		throw new UnsupportedOperationException("TODO");

		var val = heap[i];

		for (var j = i*2+1; j < n; i = j, j = j*2+1) {
			if (j+1 < n && heap[j+1] < heap[j]) {
				j++;
			}

			if (val <= heap[j]) break;

			heap[i] = heap[j];
		}

		heap[i] = val;
	}
}
