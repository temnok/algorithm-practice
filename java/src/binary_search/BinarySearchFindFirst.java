package binary_search;

public class BinarySearchFindFirst {
	// findFirstInt should return first index of val in a sorted array arr, or -1 if not found
	public static int findFirstInt(int[] arr, int val) {
//		throw new UnsupportedOperationException("TODO");

		int l = 0, r = arr.length;
		while (l < r) {
			var m = (l + r)/2;
			if (arr[m] < val) {
				l = m+1;
			} else {
				r = m;
			}
		}

		return l < arr.length && arr[l] == val? l : -1;
	}
}
