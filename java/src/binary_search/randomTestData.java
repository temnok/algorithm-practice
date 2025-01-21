package binary_search;

import java.util.Arrays;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	public int[] array;
	public int val;
	public int expectedFirst, expectedLast, expectedCount;

	randomTestData() {
		int n = rand.nextInt(50), max = rand.nextInt(50);
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(1 + max);
		}
		val = rand.nextInt(1 + max);
		expectedFirst = -1;
		expectedLast = -1;

		Arrays.sort(array);
		for (int i = 0; i < n; i++) {
			if (array[i] == val) {
				if (expectedFirst < 0) {
					expectedFirst = i;
				}
				expectedLast = i;
				expectedCount++;
			}
		}
	}
}
