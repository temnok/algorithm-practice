package quick_sort

// rangeInt should sort int array using *quicksort* algorithm
func quickSortInts(arr []int) {
	if len(arr) <= 1 {
		return
	}

	l, r := partition(arr)
	quickSortInts(arr[:l])
	quickSortInts(arr[r:])
}

func partition(arr []int) (int, int) {
	pivot := arr[len(arr)/2]

	l, r := 0, 0
	for i, val := range arr {
		if val < pivot {
			arr[i] = arr[r]
			arr[r] = arr[l]
			arr[l] = val

			l++
			r++
		} else if val == pivot {
			arr[i] = arr[r]
			arr[r] = val

			r++
		}
	}

	return l, r
}
