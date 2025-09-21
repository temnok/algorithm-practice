package merge_sort;

import java.util.Comparator;

public class MergeSortArray {
	// sortArray should sort array with *stable* merge sort using provided comparator
	public static <T> void mergeSortArray(T[] arr, Comparator<T> cmp) {
//		throw new UnsupportedOperationException("TODO");

		sort(arr, cmp, 0, arr.length, arr.clone());
	}

	private static <T> void sort(T[] arr, Comparator<T> cmp, int l, int r, T[] tmp) {
		if (r-l < 2) {
			return;
		}

		var m = (l + r)/2;
		sort(arr, cmp, l, m, tmp);
		sort(arr, cmp, m, r, tmp);
		merge(arr, cmp, l, m, r, tmp);
	}

	private static <T> void merge(T[] arr, Comparator<T> cmp, int l, int m, int r, T[] tmp) {
		for (int i = l, j = m, k = 0; i < m || j < r; k++) {
			if (i < m && (j == r || cmp.compare(arr[i], arr[j]) <= 0)) {
				tmp[k] = arr[i++];
			} else {
				tmp[k] = arr[j++];
			}
		}

		System.arraycopy(tmp, 0, arr, l, r-l);
	}
}
