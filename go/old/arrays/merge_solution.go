package arrays

func MergeSolution(arr []int, m int) {
	n := len(arr)
	buf := make([]int, n)
	for i, j, k := 0, m, 0; k < n; k++ {
		if j == n || (i < m && arr[i] <= arr[j]) {
			buf[k] = arr[i]
			i++
		} else {
			buf[k] = arr[j]
			j++
		}
	}
	copy(arr, buf)
}
