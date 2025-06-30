package binary_search

// bigCount should return number of value occurences in a sorted array
func bigCount(arr interface {
	len() int
	get(int) int
}, val int) int {
	l, r := 0, arr.len()

	for l < r {
		m := (l + r) / 2

		if arr.get(m) < val {
			l = m + 1
		} else {
			r = m
		}
	}

	start := l

	l, r = 0, arr.len()

	for l < r {
		m := (l + r) / 2

		if arr.get(m) <= val {
			l = m + 1
		} else {
			r = m
		}
	}

	return r - start
}
