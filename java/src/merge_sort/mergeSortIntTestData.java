package merge_sort;

import java.util.Arrays;
import java.util.Random;

class mergeSortIntTestData {
	private static final Random rand = new Random(0);

	public int[] array, expectedArray;

	public static mergeSortIntTestData generate() {
		var td = new mergeSortIntTestData();

		int n = rand.nextInt(50), max = rand.nextInt(50);
		td.array = new int[n];
		for (int i = 0; i < n; i++) {
			td.array[i] = rand.nextInt(1 + max);
		}
		td.expectedArray = td.array.clone();
		Arrays.sort(td.expectedArray);

		return td;
	}
}
