package binary_search;

public class BinarySearchFindLast {
	// findLastInt should return last index of val in a sorted array arr, or -1 if not found
	public static int findLastInt(int[] arr, int val) {
		int l = 0, r = arr.length;

		while (l < r) {
			int m = (l + r)/2;
			if (arr[m] > val) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		return l > 0 && arr[l - 1] ==val ? l - 1 : -1;
	}
}
