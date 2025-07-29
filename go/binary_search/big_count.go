package binary_search

// BigCount should return number of value occurrences in a sorted array
func BigCount(arr interface {
	len() int
	get(int) int
}, val int) int {
	l, r := 0, arr.len()
	for l < r {
		if m := (l + r) / 2; arr.get(m) < val {
			l = m + 1
		} else {
			r = m
		}
	}

	l0 := l
	l, r = 0, arr.len()
	for l < r {
		if m := (l + r) / 2; arr.get(m) <= val {
			l = m + 1
		} else {
			r = m
		}
	}

	return r - l0
}
