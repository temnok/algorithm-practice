package merge_sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class mergeSortStringTestData {
	private static final Random rand = new Random(0);

	public String[] array, expectedArray;

	public static mergeSortStringTestData generate() {
		var td = new mergeSortStringTestData();

		int n = rand.nextInt(50), max = rand.nextInt(1_000_000_000);
		td.array = new String[n];
		for (int i = 0; i < n; i++) {
			td.array[i] = String.valueOf(rand.nextInt(1 + max));
		}
		td.expectedArray = td.array.clone();
		Arrays.sort(td.expectedArray, Comparator.comparingInt(String::length));

		return td;
	}
}
