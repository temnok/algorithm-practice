package binary_heap;

public class HeapInit {
	// heapInit should convert provided array to binary min-heap in linear time
	public static void heapInit(int[] arr) {
//		throw new UnsupportedOperationException("TODO");

		for (var i = arr.length/2; i >= 0; i--) {
			HeapDown.heapDown(arr, arr.length, i);
		}
	}
}
