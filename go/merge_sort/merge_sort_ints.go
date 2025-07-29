package merge_sort

// MergeSortInts should sort int array with merge sort
func MergeSortInts(arr []int) {
	mergeSortInts(arr, make([]int, len(arr)))
}

func mergeSortInts(arr, tmp []int) {
	n := len(arr)
	m := n / 2
	if m == 0 {
		return
	}

	mergeSortInts(arr[:m], tmp)
	mergeSortInts(arr[m:], tmp)

	i, j, k := 0, m, 0
	for ; i < m || j < n; k++ {
		if j == n || i < m && arr[i] <= arr[j] {
			tmp[k] = arr[i]
			i++
		} else {
			tmp[k] = arr[j]
			j++
		}
	}

	copy(arr, tmp)
}
