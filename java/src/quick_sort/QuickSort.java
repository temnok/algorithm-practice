package quick_sort;

public class QuickSort {
	// rangeInt should sort int array using *quicksort* algorithm
	public static void quickSortInts(int[] arr) {
//		throw new UnsupportedOperationException("TODO");

		recurse(arr, 0, arr.length);
	}

	private static void recurse(int[] arr, int l, int r) {
		if (r-l < 2) {
			return;
		}

		var lr = partition(arr, l, r);
		recurse(arr, l, lr[0]);
		recurse(arr, lr[1], r);
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
