package binary_search

// CountInts should return number of values in a sorted array
func CountInts(arr []int, val int) int {
	l, r := 0, len(arr)
	for l < r {
		m := (l + r) / 2
		if arr[m] < val {
			l = m + 1
		} else {
			r = m
		}
	}

	l0 := l
	r = len(arr)
	for l < r {
		m := (l + r) / 2
		if arr[m] == val {
			l = m + 1
		} else {
			r = m
		}
	}

	return r - l0
}
