
# heap_up should move element up at index i in a binary min-heap
def heap_up(heap: list[int], i: int):
	# raise NotImplementedError('TODO')

	val = heap[i]

	j = (i-1)//2
	while i > 0 and val < heap[j]:
		heap[i] = heap[j]

		i, j = j, (j-1)//2

	heap[i] = val
