package quick_search;

import java.util.Arrays;
import java.util.Random;

class TestCase {
	private static final Random rand = new Random(0);

	public int[] array, expectedArray;

	public static TestCase generate() {
		var tc = new TestCase();

		int n = rand.nextInt(50), max = rand.nextInt(50);
		tc.array = new int[n];
		for (int i = 0; i < n; i++) {
			tc.array[i] = rand.nextInt(1 + max);
		}
		tc.expectedArray = tc.array.clone();
		Arrays.sort(tc.expectedArray);

		return tc;
	}
}
