package arrays;

import org.junit.*;

import java.util.Arrays;
import java.util.Random;

public class MergeTest {
	private static final int caseCount = 1_000;
	private static final Random rand = new Random(0);

	@Test
	public void testMerge() {
		for (int c = 0; c < caseCount; c++) {
			int[] arr = generateRandomArray();
			int n = arr.length, m = n / 2;
			Arrays.sort(arr, 0, m);
			Arrays.sort(arr, m, n);
			int[] actual = arr.clone(), expected = arr.clone();
			Merge.merge(actual, 0, m, n);
			MergeSolution.merge(expected, 0, m, n);
			if (!Arrays.equals(actual, expected)) {
				Assert.fail(String.format("Merge(%s):\n  Actual: %s\nExpected: %s",
					Arrays.toString(arr), Arrays.toString(actual), Arrays.toString(expected)));
			}
		}
	}

	private static int[] generateRandomArray() {
		int n = rand.nextInt(50), max = rand.nextInt(50);
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = rand.nextInt(1 + max);
		}
		return arr;
	}
}
