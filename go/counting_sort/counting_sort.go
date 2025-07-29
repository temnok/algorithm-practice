package counting_sort

// CountingSort should sort nums array using *counting* sort.
//
// Restrictions:
// - nums array is not empty
// - num in nums can be any integer, including negative values
// - difference between maximum and minimum value in arr won't exceed 1000
func CountingSort(nums []int) {
	mi, ma := nums[0], nums[0]
	for _, num := range nums {
		mi = min(mi, num)
		ma = max(ma, num)
	}

	counts := make([]int, ma-mi+1)
	for _, num := range nums {
		counts[num-mi]++
	}

	i := 0
	for num := mi; num <= ma; num++ {
		for c := counts[num-mi]; c > 0; c-- {
			nums[i] = num
			i++
		}
	}
}
