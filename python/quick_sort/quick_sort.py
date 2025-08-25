
# quick_sort_ints should sort int array using *quicksort* algorithm
def quick_sort_ints(arr: list[int]):
	# raise NotImplementedError('TODO')

	quick_sort_ints_recurse(arr, 0, len(arr))


def quick_sort_ints_recurse(arr, l, r):
	if r - l <= 1:
		return

	i, j = partition(arr, l, r)
	quick_sort_ints_recurse(arr, l, i)
	quick_sort_ints_recurse(arr, j, r)


def partition(arr, l, r):
	pivot = arr[(l + r)//2]

	i, j, k = l, l, l
	while k < r:
		val = arr[k]
		if val < pivot:
			arr[k] = arr[j]
			arr[j] = arr[i]
			arr[i] = val
			i += 1
			j += 1
		elif val == pivot:
			arr[k] = arr[j]
			arr[j] = val
			j += 1
		k += 1

	return i, j
