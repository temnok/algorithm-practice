package merge_sort

// MergeSortArray should sort array with *stable* merge sort using provided comparator:
// comparator function should return negative value if arr[i]<arr[j], zero if arr[i]==arr[j]
// and positive value if arr[i]>arr[j]
func MergeSortArray[T any](arr []T, cmp func(T, T) int) {
	tmp := make([]T, len(arr))
	mergeSortArray(arr, tmp, cmp)
}

func mergeSortArray[T any](arr, tmp []T, cmp func(T, T) int) {
	n := len(arr)
	m := n / 2
	if m == 0 {
		return
	}

	mergeSortArray(arr[:m], tmp, cmp)
	mergeSortArray(arr[m:], tmp, cmp)

	i, j, k := 0, m, 0
	for ; i < m || j < n; k++ {
		if j == n || i < m && cmp(arr[i], arr[j]) <= 0 {
			tmp[k] = arr[i]
			i++
		} else {
			tmp[k] = arr[j]
			j++
		}
	}

	copy(arr, tmp)
}
