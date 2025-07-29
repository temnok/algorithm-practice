package binary_heap

// HeapInit should convert provided array to binary min-heap in linear time
func HeapInit(heap []int) {
	for i := len(heap) / 2; i >= 0; i-- {
		HeapDown(heap, i)
	}
}
