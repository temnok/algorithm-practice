package binary_search;

public class BinarySearchCount {
	// countInt should return number of values in a sorted array
	public static int countInt(int[] arr, int val) {
		int l = 0, r = arr.length;

		while (l < r) {
			int m = (l + r)/2;
			if (arr[m] >= val) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		int l1 = l;
		r = arr.length;

		while (l < r) {
			int m = (l + r)/2;
			if (arr[m] > val) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		return l - l1;
	}
}
