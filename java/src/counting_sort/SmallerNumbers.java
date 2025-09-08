package counting_sort;

import java.util.HashMap;

public class SmallerNumbers {
	/*
	given the array of ints, e.g [8,1,2,2,3], return an array containing count of numbers that are smaller than i-th
	number. For given example result is [4,0,1,1,3]
	 */
	public static int[] getSmallerNumbers(int[] nums, int max) {
//		throw new UnsupportedOperationException("TODO");

		var counts = new int[max+1];
		for (var num: nums) {
			counts[num]++;
		}

		for (var num = 1; num <= max; num++) {
			counts[num] += counts[num-1];
		}

		for (var i = 0; i < nums.length; i++) {
			var num = nums[i];
			nums[i] = num == 0? 0 : counts[num-1];
		}

		return nums;
	}
}
