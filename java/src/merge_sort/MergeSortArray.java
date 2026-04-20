package merge_sort;

import java.util.Comparator;

public class MergeSortArray {
	// sortArray should sort array with *stable* merge sort using provided comparator
	public static <T> void mergeSortArray(T[] arr, Comparator<T> cmp) {
		sort(arr, arr.clone(), cmp, 0, arr.length);
	}

	private static <T> void sort(T[] arr, T [] temp, Comparator<T> cmp, int l, int r) {
		if (r - l < 2) {
			return;
		}

		int m = (l + r)/2;
		sort(arr, temp, cmp, l, m);
		sort(arr, temp, cmp, m, r);

		for (int i = l, j = m, k = l; k < r; k++) {
			if (j == r || (i < m && cmp.compare(arr[i], arr[j]) <= 0)) {
				temp[k] = arr[i++];
			} else {
				temp[k] = arr[j++];
			}
		}

		System.arraycopy(temp, l, arr, l, r - l);

	}

}
