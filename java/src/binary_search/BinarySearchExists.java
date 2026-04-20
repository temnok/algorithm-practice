package binary_search;

public class BinarySearchExists {
	// existsInt should return true if val exists in a sorted array arr, or false if not found
	public static boolean existsInt(int[] arr, int val) {
		int l = 0, r = arr.length;

		while (l < r) {
			int m = (l + r)/2;
			if (arr[m] >= val) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		return l < arr.length && arr[l] == val;
	}
}
