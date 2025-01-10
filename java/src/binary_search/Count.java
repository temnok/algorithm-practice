package binary_search;

public class Count {
	// countInt should return number of values in a sorted array
	public static int countInt(int[] arr, int val) {
		var l = 0;
		var r = arr.length;

		while (l < r) {
			var m = (l + r) / 2;
			if (arr[m] < val) {
				l = m + 1;
			} else {
				r = m;
			}
		}

		var r0 = r;

		for (l = 0, r = arr.length; l < r;) {
			var m = (l + r) / 2;
			if (arr[m] <= val) {
				l = m + 1;
			} else {
				r = m;
			}
		}

		return l-r0;
	}
}
