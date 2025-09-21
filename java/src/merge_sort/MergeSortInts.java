package merge_sort;

public class MergeSortInts {
	// sortInts should sort int array with merge sort
	public static void mergeSortInts(int[] arr) {
//		throw new UnsupportedOperationException("TODO");

		sort(arr, 0, arr.length, new int[arr.length]);
	}

	private static void sort(int[] arr, int l, int r, int[] tmp) {
		if (r-l < 2) {
			return;
		}

		var m = (l + r)/2;
		sort(arr, l, m, tmp);
		sort(arr, m, r, tmp);
		merge(arr, l, m, r, tmp);
	}

	private static void merge(int[] arr, int l, int m, int r, int[] tmp) {
		for (int i = l, j = m, k = 0; i < m || j < r; k++) {
			if (i < m && (j == r || arr[i] <= arr[j])) {
				tmp[k] = arr[i++];
			} else {
				tmp[k] = arr[j++];
			}
		}

		System.arraycopy(tmp, 0, arr, l, r-l);
	}
}
