package binary_search

// ExistsInt should return true if val exists in a sorted array arr, or false if not found
func ExistsInt(arr []int, val int) bool {
	l, r := 0, len(arr)
	for l < r {
		if m := (l + r) / 2; arr[m] < val {
			l = m + 1
		} else {
			r = m
		}
	}

	return l < len(arr) && arr[l] == val
}
