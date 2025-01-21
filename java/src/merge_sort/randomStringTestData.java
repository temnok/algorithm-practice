package merge_sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class randomStringTestData {
	private static final Random rand = new Random(0);

	public String[] array, expectedArray;

	randomStringTestData() {
		int n = rand.nextInt(50), max = rand.nextInt(1_000_000_000);
		array = new String[n];
		for (int i = 0; i < n; i++) {
			array[i] = String.valueOf(rand.nextInt(1 + max));
		}
		expectedArray = array.clone();
		Arrays.sort(expectedArray, Comparator.comparingInt(String::length));
	}
}
