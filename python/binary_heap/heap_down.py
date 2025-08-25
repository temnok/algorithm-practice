
# heap_down should move element down at index i in a binary min-heap
def heap_down(heap: list[int], n: int, i: int):
	# raise NotImplementedError('TODO')

	val = heap[i]

	j = i*2+1
	while j < n:
		if j+1 < n and heap[j+1] < heap[j]:
			j += 1

		if val <= heap[j]:
			break

		heap[i] = heap[j]

		i, j = j, j*2+1

	heap[i] = val
