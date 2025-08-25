
# count_ints should return number of value occurrences in a sorted array
def count_ints(arr, val: int) -> int:
	# raise NotImplementedError('TODO')

	l, r = 0, len(arr)
	while l < r:
		m = (l + r) // 2
		if arr[m] < val:
			l = m+1
		else:
			r = m

	l0 = l
	l, r = 0, len(arr)
	while l < r:
		m = (l + r) // 2
		if arr[m] <= val:
			l = m+1
		else:
			r = m

	return r - l0
