
# find_last_int should return last index of val in a sorted array arr, or -1 if not found
def find_last_int(arr: list, val: int) -> bool:
	# raise NotImplementedError('TODO')

	l, r = 0, len(arr)
	while l < r:
		m = (l + r) // 2
		if arr[m] <= val:
			l = m+1
		else:
			r = m

	return r-1 if r > 0 and arr[r-1] == val else -1
