package binary_heap;

import static binary_heap.HeapDown.heapDown;

public class HeapInit {
	// heapInit should convert provided array to binary min-heap in linear time
	public static void heapInit(int[] arr) {
		for (int i = arr.length/2; i >=0; i--) {
			heapDown(arr, arr.length, i);
		}
	}
}
