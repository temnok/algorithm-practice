package binary_search

// existsInt should return true if val exists in a sorted array arr, or false if not found
func existsInt(arr []int, val int) bool {
	l, r := 0, len(arr)

	for l < r {
		m := (l + r) / 2

		if arr[m] < val {
			l = m + 1
		} else {
			r = m
		}
	}

	return l < len(arr) && arr[l] == val
}
