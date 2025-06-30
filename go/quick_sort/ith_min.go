package quick_sort

// ithMin should return i-th minimal element in the array for average-linear time
// Constraints:
// - 0 <= i < arr.length
// - it's ok to change array in place
func ithMin(arr []int, i int) int {
	l, r := partition(arr)
	if i < l {
		return ithMin(arr[:l], i)
	}

	if i < r {
		return arr[i]
	}

	return ithMin(arr[r:], i-r)
}
