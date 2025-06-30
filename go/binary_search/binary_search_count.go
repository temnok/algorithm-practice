package binary_search

// countInt should return number of values in a sorted array
func countInt(arr []int, val int) int {
	l, r := 0, len(arr)

	for l < r {
		m := (l + r) / 2

		if arr[m] < val {
			l = m + 1
		} else {
			r = m
		}
	}

	start := l

	l, r = 0, len(arr)

	for l < r {
		m := (l + r) / 2

		if arr[m] <= val {
			l = m + 1
		} else {
			r = m
		}
	}

	return r - start
}
