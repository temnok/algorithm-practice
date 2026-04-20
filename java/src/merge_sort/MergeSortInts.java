package merge_sort;

public class MergeSortInts {
	// sortInts should sort int array with merge sort
	public static void mergeSortInts(int[] arr) {
		sort(arr, new int[arr.length], 0, arr.length);
	}

	private static void sort(int[] arr, int [] temp, int l, int r) {
		if (r - l < 2) {
			return;
		}

		int m = (l + r)/2;
		sort(arr, temp, l, m);
		sort(arr, temp, m, r);

		for (int i = l, j = m, k = l; k < r; k++) {
			if (j == r || (i < m && arr[i] <= arr[j])) {
				temp[k] = arr[i++];
			} else {
				temp[k] = arr[j++];
			}
		}

		System.arraycopy(temp, l, arr, l, r - l);

	}
}
