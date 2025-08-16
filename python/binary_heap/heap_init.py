from heap_down import heap_down


# heap_init should convert provided array to binary min-heap in linear time
def heap_init(heap: list[int]):
	# raise NotImplementedError('TODO')

	for i in reversed(range(len(heap)//2)):
		heap_down(heap, len(heap), i)
