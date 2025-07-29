package binary_search

// FindLastInt should return last index of val in a sorted array arr, or -1 if not found
func FindLastInt(arr []int, val int) int {
	l, r := 0, len(arr)
	for l < r {
		if m := (l + r) / 2; arr[m] <= val {
			l = m + 1
		} else {
			r = m
		}
	}

	if r > 0 && arr[r-1] == val {
		return r - 1
	}
	return -1
}
