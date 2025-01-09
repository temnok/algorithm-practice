package arrays;

public class MergeSolution {
	public static void merge(int[] arr, int l, int m, int r) {
		int n = r - l;
		int[] buf = new int[n];
		for (int i = l, j = m, k = 0; k < n; k++) {
			if (j == r || (i < m && arr[i] <= arr[j])) {
				buf[k] = arr[i];
				i++;
			} else {
				buf[k] = arr[j];
				j++;
			}
		}
		System.arraycopy(buf, 0, arr, l, n);
	}
}
