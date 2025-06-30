package merge_sort

// sortArray should sort array with *stable* merge sort using provided comparator:
// comparator function should return negative value if arr[i]<arr[j], zero if arr[i]==arr[j]
// and positive value if arr[i]>arr[j]
func mergeSortArray[T any](arr []T, cmp func(T, T) int) {
	tmp := make([]T, len(arr))
	recursiveMergeSort(arr, tmp, cmp)
}

func recursiveMergeSort[T any](arr, tmp []T, cmp func(T, T) int) {
	n := len(arr)
	if n <= 1 {
		return
	}

	m := n / 2
	a, b := arr[:m], arr[m:]
	recursiveMergeSort(a, tmp, cmp)
	recursiveMergeSort(b, tmp, cmp)
	merge(a, b, tmp, cmp)
	copy(arr, tmp)
}

func merge[T any](a, b, c []T, cmp func(T, T) int) {
	for i, j := 0, 0; i < len(a) || j < len(b); {
		if i < len(a) && (j == len(b) || cmp(a[i], b[j]) <= 0) {
			c[i+j] = a[i]
			i++
		} else {
			c[i+j] = b[j]
			j++
		}
	}
}
