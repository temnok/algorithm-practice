
# find_first_int should return first index of val in a sorted array arr, or -1 if not found
def find_first_int(arr: list, val: int) -> bool:
	# raise NotImplementedError('TODO')

	l, r = 0, len(arr)
	while l < r:
		m = (l + r) // 2
		if arr[m] < val:
			l = m+1
		else:
			r = m

	return l if l < len(arr) and arr[l] == val else -1
