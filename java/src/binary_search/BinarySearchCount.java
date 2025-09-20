package binary_search;

public class BinarySearchCount {
	// countInt should return number of values in a sorted array
	public static int countInt(int[] arr, int val) {
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

		var l0 = l;
		l = 0; r = arr.length;
		while (l < r) {
			var m = (l + r)/2;
			if (arr[m] <= val) {
				l = m+1;
			} else {
				r = m;
			}
		}

		return r - l0;
	}
}
