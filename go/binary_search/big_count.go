package binary_search

// BigCount should return number of value occurrences in a sorted array
func BigCount(arr interface {
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

	l0 := l
	r = arr.len()
	for l < r {
		m := (l + r) / 2
		if arr.get(m) == val {
			l = m + 1
		} else {
			r = m
		}
	}

	return r - l0
}
