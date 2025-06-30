package binary_search

// findLastInt should return last index of val in a sorted array arr, or -1 if not found
func findLastInt(arr []int, val int) int {
	l, r := 0, len(arr)

	for l < r {
		m := (l + r) / 2

		if arr[m] <= val {
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
