package binary_heap

// HeapUp should move element up at index i in a binary min-heap
func HeapUp(heap []int, i int) {
	val := heap[i]

	for j := (i - 1) / 2; i > 0 && val < heap[j]; i, j = j, (j-1)/2 {
		heap[i] = heap[j]
	}

	heap[i] = val
}
