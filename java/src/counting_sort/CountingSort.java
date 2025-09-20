package counting_sort;

public class CountingSort {
	// countingSort should sort nums array using *counting* sort.
	//
	// Restrictions:
	// - nums array is not empty
	// - num in nums can be any integer, including negative values
	// - difference between maximum and minimum value in arr won't exceed 1000
	public static void countingSort(int[] nums) {
//		throw new UnsupportedOperationException("TODO");

		int minNum = nums[0], maxNum = nums[0];
		for (var num: nums) {
			if (num < minNum) minNum = num;
			if (num > maxNum) maxNum = num;
		}

		var counts = new int[maxNum - minNum + 1];
		for (var num: nums) {
			counts[num - minNum]++;
		}

		var i = 0;
		for (var num = minNum; num <= maxNum; num++) {
			for (var c = counts[num - minNum]; c > 0; c--) {
				nums[i++] = num;
			}
		}
	}
}
