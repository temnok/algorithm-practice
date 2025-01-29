package binary_heap;

import java.util.Arrays;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int[] array, heap;
	int randomIndex, randomValue;

	randomTestData() {
		int n = 1 + rand.nextInt(50), max = rand.nextInt(50);
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(1 + max);
		}
		heap = array.clone();
		for (var i0 = heap.length/2; i0 >= 0; i0--) {
			var i = i0;
			var val = heap[i];
			for (int j = i*2+1; j < heap.length; i = j, j = j*2+1) {
				if (j+1 < heap.length && heap[j+1] < heap[j]) j++;
				if (val <= heap[j]) break;
				heap[i] = heap[j];
			}
			heap[i] = val;
		}

		randomIndex = rand.nextInt(n);
		randomValue = rand.nextInt(1 + max);
	}

	static boolean minHeapIsOK(int[] heap) {
		for (int i = heap.length-1; i > 0; i--) {
			if (heap[i] < heap[(i-1)/2]) {
				return false;
			}
		}

		return true;
	}

	static boolean haveSameElements(int[] arr1, int[] arr2) {
		int[] sorted1 = arr1.clone(), sorted2 = arr2.clone();
		Arrays.sort(sorted1);
		Arrays.sort(sorted2);
		return Arrays.equals(sorted1, sorted2);
	}
}
