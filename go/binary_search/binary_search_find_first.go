package binary_search

// firstInt should return first index of val in a sorted array arr, or -1 if not found
func findFirstInt(arr []int, val int) int {
	l, r := 0, len(arr)

	for l < r {
		m := (l + r) / 2

		if arr[m] < val {
			l = m + 1
		} else {
			r = m
		}
	}

	if l < len(arr) && arr[l] == val {
		return l
	}

	return -1
}
