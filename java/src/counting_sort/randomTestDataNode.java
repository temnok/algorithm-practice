package counting_sort;

import java.util.Arrays;
import java.util.Random;

class randomTestDataNode {
	private static final Random rand = new Random(0);

	Node[] array, expectedArray;
	int maxValue;

	randomTestDataNode() {
		this(50);
	}

	randomTestDataNode(int maxN) {
		int n = 1+rand.nextInt(maxN);
		maxValue = rand.nextInt(maxN+1);
		array = new Node[n];
		for (int i = 0; i < n; i++) {
			array[i] = new Node(rand.nextInt(1 + maxValue), i);
		}
		expectedArray = array.clone();
		Arrays.sort(expectedArray);
	}

	int [] asInt() {
		return Arrays.stream(array).mapToInt(value -> value.i).toArray();
	}

	int [] getSmallerNumbers() {
		int [] result = new int[expectedArray.length];

		for (int i = 0; i < expectedArray.length; i++) {
			result[expectedArray[i].index] = getSmaller(i);
		}

		return result;
	}

	private int getSmaller(int i) {
		for (int j = 0; j < i; j++) {
			if (expectedArray[j].compareTo(expectedArray[i]) == 0) {
				return j;
			}
		}
		return i;
	}

	private static class Node implements Comparable<Node> {
		int i;
		int index;

		public Node(int i, int index) {
			this.i = i;
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(i, o.i);
		}
	}
}
