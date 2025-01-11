package binary_search;

import java.util.Arrays;
import java.util.Random;

class binarySearchTestData {
	private static final Random rand = new Random(0);

	public int[] array;
	public int val;
	public int expectedFirst, expectedLast, expectedCount;

	public static binarySearchTestData generate() {
		var td = new binarySearchTestData();

		int n = rand.nextInt(50), max = rand.nextInt(50);
		td.array = new int[n];
		for (int i = 0; i < n; i++) {
			td.array[i] = rand.nextInt(1 + max);
		}
		td.val = rand.nextInt(1 + max);
		td.expectedFirst = -1;
		td.expectedLast = -1;

		Arrays.sort(td.array);
		for (int i = 0; i < n; i++) {
			if (td.array[i] == td.val) {
				if (td.expectedFirst < 0) {
					td.expectedFirst = i;
				}
				td.expectedLast = i;
				td.expectedCount++;
			}
		}

		return td;
	}
}
