
# exists_int should return true if val exists in a sorted array arr, or false if not found
def exists_int(arr: list, val: int) -> bool:
	# raise NotImplementedError('TODO')

	l, r = 0, len(arr)
	while l < r:
		m = (l + r) // 2
		if arr[m] < val:
			l = m+1
		else:
			r = m

	return l < len(arr) and arr[l] == val
