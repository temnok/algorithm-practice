
# merge_sort_ints should sort int array with merge sort
def merge_sort_ints(arr: list[int]):
	# raise NotImplementedError('TODO')

	merge_sort_ints_recurse(arr, arr.copy(), 0, len(arr))


def merge_sort_ints_recurse(arr, tmp, l, r):
	if r-l <= 1:
		return
	m = (l+r)//2
	merge_sort_ints_recurse(arr, tmp, l, m)
	merge_sort_ints_recurse(arr, tmp, m, r)

	merge(arr, tmp, l, m, r)


def merge(arr, tmp, l, m, r):
	i, j, k = l, m, 0
	while i < m or j < r:
		if i < m and (j == r or arr[i] <= arr[j]):
			tmp[k] = arr[i]
			i += 1
		else:
			tmp[k] = arr[j]
			j += 1
		k += 1
	arr[l:r] = tmp[:k]
