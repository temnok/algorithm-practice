package binary_heap

// heapInit should convert provided array to binary min-heap in linear time
func heapInit(heap []int) {
	for i := len(heap)/2 - 1; i >= 0; i-- {
		heapDown(heap, i)
	}
}
