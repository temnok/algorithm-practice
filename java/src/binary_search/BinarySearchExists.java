package binary_search;

public class BinarySearchExists {
	// existsInt should return true if val exists in a sorted array arr, or false if not found
	public static boolean existsInt(int[] arr, int val) {
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

		return l < arr.length && arr[l] == val;
	}
}
