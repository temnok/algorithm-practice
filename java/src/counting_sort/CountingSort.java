package counting_sort;

public class CountingSort {
	/*
	sort array with values less than number in 'max' variable
	 */
	public static int [] sort(int [] num, int max) {
		int count[] = new int[max];

		for (int n : num) {
			count[n]++;
		}

		for (int i = 1; i < max; i++) {
			count[i] += count[i - 1];
		}

		int [] result = new int[num.length];
		for (int n : num) {
			result[--count[n]] = n;
		}

		return result;
	}
}
