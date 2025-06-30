package binary_heap

// heapUp should move element up at index i in a binary min-heap
func heapUp(heap []int, i int) {
	val := heap[i]

	for j := (i - 1) / 2; i > 0; i, j = j, (j-1)/2 {
		if heap[j] <= val {
			break
		}

		heap[i] = heap[j]
	}

	heap[i] = val
}
