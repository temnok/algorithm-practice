package quick_sort;

import java.util.Arrays;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int[] array, expectedArray;
	int randomIndex;

	randomTestData() {
		this(50);
	}

	randomTestData(int maxN) {
		int n = 1+rand.nextInt(maxN), max = rand.nextInt(maxN+1);
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(1 + max);
		}
		expectedArray = array.clone();
		Arrays.sort(expectedArray);

		randomIndex = rand.nextInt(n);
	}
}
