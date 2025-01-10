package binary_search;

import java.util.Arrays;
import java.util.Random;

class TestCase {
	private static final Random rand = new Random(0);

	public int[] array;
	public int val;
	public int expectedFirst, expectedLast, expectedCount;

	public static TestCase generate() {
		var tc = new TestCase();

		int n = rand.nextInt(50), max = rand.nextInt(50);
		tc.array = new int[n];
		for (int i = 0; i < n; i++) {
			tc.array[i] = rand.nextInt(1 + max);
		}
		tc.val = rand.nextInt(1 + max);
		tc.expectedFirst = -1;
		tc.expectedLast = -1;

		Arrays.sort(tc.array);
		for (int i = 0; i < n; i++) {
			if (tc.array[i] == tc.val) {
				if (tc.expectedFirst < 0) {
					tc.expectedFirst = i;
				}
				tc.expectedLast = i;
				tc.expectedCount++;
			}
		}

		return tc;
	}
}
