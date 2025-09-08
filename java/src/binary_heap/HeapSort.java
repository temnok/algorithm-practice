package binary_heap;

public class HeapSort {
	// heapSort should sort the provided array in non-decreasing fashion using heapSort algorithm
	public static void heapSort(int[] arr) {
//		throw new UnsupportedOperationException("TODO");

		HeapInit.heapInit(arr);
		for (var n = arr.length-1; n > 0; n--) {
			var tmp = arr[0]; arr[0] = arr[n]; arr[n] = tmp;

			HeapDown.heapDown(arr, n, 0);
		}

		for (int l = 0, r = arr.length-1; l < r; l++, r--) {
			var tmp = arr[l]; arr[l] = arr[r]; arr[r] = tmp;
		}
	}
}
