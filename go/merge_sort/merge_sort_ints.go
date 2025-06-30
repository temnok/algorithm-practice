package merge_sort

// sortInts should sort int array with merge sort
func mergeSortInts(arr []int) {
	tmp := make([]int, len(arr))
	recursiveMergeSortInts(arr, tmp)
}

func recursiveMergeSortInts(arr, tmp []int) {
	n := len(arr)
	if n <= 1 {
		return
	}

	m := n / 2
	a, b := arr[:m], arr[m:]
	recursiveMergeSortInts(a, tmp)
	recursiveMergeSortInts(b, tmp)
	mergeInts(a, b, tmp)
	copy(arr, tmp)
}

func mergeInts(a, b, c []int) {
	for i, j := 0, 0; i < len(a) || j < len(b); {
		if i < len(a) && (j == len(b) || a[i] <= b[j]) {
			c[i+j] = a[i]
			i++
		} else {
			c[i+j] = b[j]
			j++
		}
	}
}
