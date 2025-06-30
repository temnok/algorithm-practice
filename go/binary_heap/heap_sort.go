package binary_heap

// heapSort should sort the provided array in non-decreasing fashion using heapSort algorithm
func heapSort(arr []int) {
	heapInit(arr)

	for i := len(arr) - 1; i > 0; i-- {
		arr[0], arr[i] = arr[i], arr[0]
		heapDown(arr[:i], 0)
	}

	for l, r := 0, len(arr)-1; l < r; l, r = l+1, r-1 {
		arr[l], arr[r] = arr[r], arr[l]
	}
}
