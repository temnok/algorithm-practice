package quick_sort

// IthMin should return i-th minimal element in the array for average-linear time
// Constraints:
// - 0 <= i < arr.length
// - it's ok to change array in place
func IthMin(arr []int, i int) int {
	for {
		l, r := partition(arr)
		if l <= i && i < r {
			return arr[i]
		}

		if i < l {
			arr = arr[:l]
		} else {
			i -= r
			arr = arr[r:]
		}
	}
}
