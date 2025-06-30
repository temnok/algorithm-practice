package binary_heap

// heapDown should move element down at index i in a binary min-heap
func heapDown(heap []int, i int) {
	val := heap[i]

	for j := i*2 + 1; j < len(heap); i, j = j, j*2+1 {
		if j+1 < len(heap) && heap[j+1] < heap[j] {
			j++
		}

		if val <= heap[j] {
			break
		}

		heap[i] = heap[j]
	}

	heap[i] = val
}
