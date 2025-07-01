package counting_sort;

import java.util.Arrays;
import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int[] array, expectedArray;
	int maxValue;

	randomTestData() {
		this(50);
	}

	randomTestData(int maxN) {
		int n = 1+rand.nextInt(maxN);
		maxValue = rand.nextInt(maxN+1);
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = rand.nextInt(1 + maxValue);
		}
		expectedArray = array.clone();
		Arrays.sort(expectedArray);
	}

	int getUnsortedCount() {
		int result = 0;

		for (int i = 0; i < expectedArray.length; i++) {
			if (expectedArray[i] != array[i]) {
				result++;
			}
		}

		return result;
	}

	private static class Node implements Comparable<Node> {
		int i;

		@Override
		public int compareTo(Node o) {
			return 0;
		}
	}
}
