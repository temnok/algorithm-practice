from heap_init import heap_init
from heap_down import heap_down


# heap_sort should sort the provided array in non-decreasing fashion using HeapSort algorithm
def heap_sort(arr: list[int]):
	# raise NotImplementedError('TODO')
	heap_init(arr)
	for i in range(len(arr)-1, 0, -1):
		arr[0], arr[i] = arr[i], arr[0]
		heap_down(arr, i, 0)

	arr.reverse()