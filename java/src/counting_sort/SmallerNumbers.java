package counting_sort;

import java.util.Arrays;

public class SmallerNumbers {
	/*
	given the array of ints, e.g [8,1,2,2,3], return an array containing count of numbers that are smaller than i-th
	number. For given example result is [4,0,1,1,3]
	 */
	public static int[] getSmallerNumbers(int [] num, int max) {
		int count[] = new int[max];
		int result[] = new int[num.length];

		for (int n : num) {
			count[n]++;
		}

		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = 0; i < num.length; i++) {
			result[i] = num[i] == 0 ? 0 : count[num[i] - 1];
		}

		return result;
	}
}
