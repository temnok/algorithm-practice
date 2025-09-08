package quick_sort;

public class IthMin {
	// ithMin should return i-th minimal element in the array for average-linear time
	// Constraints:
	// - 0 <= i < arr.length
	// - it's ok to change array in place
	public static int ithMin(int[] arr, int i) {
//		throw new UnsupportedOperationException("TODO");

		for (int l = 0, r = arr.length;;) {
			var lr = partition(arr, l, r);
			if (i < lr[0]) {
				r = lr[0];
			} else if (lr[1] <= i) {
				l = lr[1];
			} else {
				return arr[i];
			}
		}
	}

	private static int[] partition(int[] arr, int l, int r) {
		int pivot = arr[(l + r) / 2], i = l, j = l;

		for (var k = l; k < r; k++) {
			var val = arr[k];
			if (val < pivot) {
				arr[k] = arr[j];
				arr[j] = arr[i];
				arr[i] = val;
				i++; j++;
			} else if (val == pivot) {
				arr[k] = arr[j];
				arr[j] = val;
				j++;
			}
		}

		return new int[] { i, j };
	}
}
