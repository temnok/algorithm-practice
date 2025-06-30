package binary_heap

// heapSet should change element at index i in a binary min-heap
// to the provided value and update the heap properly
func heapSet(heap []int, i, val int) {
	heap[i] = val

	heapUp(heap, i)
	heapDown(heap, i)
}
