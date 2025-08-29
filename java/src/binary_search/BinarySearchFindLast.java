package binary_search;

public class BinarySearchFindLast {
	// findLastInt should return last index of val in a sorted array arr, or -1 if not found
	public static int findLastInt(int[] arr, int val) {
//		throw new UnsupportedOperationException("TODO");

		int l = 0, r = arr.length;
		while (l < r) {
			var m = (l + r)/2;
			if (arr[m] <= val) {
				l = m+1;
			} else {
				r = m;
			}
		}

		return r > 0 && arr[r-1] == val? r-1: -1;
	}
}
