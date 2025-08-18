
# merge_sort_array should sort array with *stable* merge sort using provided comparator:
# comparator function should return negative value if arr[i]<arr[j], zero if arr[i]==arr[j]
# and positive value if arr[i]>arr[j]
def merge_sort_array(arr, cmp):
	# raise NotImplementedError('TODO')

	merge_sort_array_recurse(arr, arr.copy(), cmp, 0, len(arr))


def merge_sort_array_recurse(arr, tmp, cmp, l, r):
	if r-l <= 1:
		return
	m = (l+r)//2
	merge_sort_array_recurse(arr, tmp, cmp, l, m)
	merge_sort_array_recurse(arr, tmp, cmp, m, r)

	merge(arr, tmp, cmp, l, m, r)


def merge(arr, tmp, cmp, l, m, r):
	i, j, k = l, m, 0
	while i < m or j < r:
		if i < m and (j == r or cmp(arr[i], arr[j]) <= 0):
			tmp[k] = arr[i]
			i += 1
		else:
			tmp[k] = arr[j]
			j += 1
		k += 1
	arr[l:r] = tmp[:k]
