package quick_sort

// rangeInt should sort int array using *quicksort* algorithm
func QuickSortInts(arr []int) {
	if len(arr) < 2 {
		return
	}

	l, r := partition(arr)
	QuickSortInts(arr[:l])
	QuickSortInts(arr[r:])
}

func partition(arr []int) (int, int) {
	l, r := 0, 0

	p := arr[len(arr)/2]
	for i, val := range arr {
		if val < p {
			arr[i] = arr[r]
			arr[r] = arr[l]
			arr[l] = val
			l++
			r++
		} else if val == p {
			arr[i] = arr[r]
			arr[r] = val
			r++
		}
	}

	return l, r
}
