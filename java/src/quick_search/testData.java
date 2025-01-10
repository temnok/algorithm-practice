package quick_search;

import java.util.Arrays;
import java.util.Random;

class testData {
	private static final Random rand = new Random(0);

	public int[] array, expectedArray;

	public static testData generate() {
		var td = new testData();

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
