package quick_sort;

import java.util.Arrays;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	public int[] array, expectedArray;

	randomTestData() {
		int n = rand.nextInt(50), max = rand.nextInt(50);
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(1 + max);
		}
		expectedArray = array.clone();
		Arrays.sort(expectedArray);
	}
}
