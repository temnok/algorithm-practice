package counting_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

class randomTestDataPalindrom {
	private static final Random rand = new Random(0);

	String palindrom, expected;

	randomTestDataPalindrom() {
		this(50);
	}

	randomTestDataPalindrom(int maxN) {
		int n = 1+rand.nextInt(maxN);
		int minValue = (int) 'a';
		int maxValueExclusive = (int) 'z' + 1;
		int [] left = new int[n];
		for (int i = 0; i < n; i++) {
			left[i] = rand.nextInt(minValue, maxValueExclusive);
		}
		int[] sortedLeft = left.clone();
		Arrays.sort(sortedLeft);

		char [] leftChar = new char[n], rightChar = new char[n];
		int l = 0, r = n;
		for (int i : left) {
			leftChar[l++] = (char)i;
			rightChar[--r] = (char)i;
		}

		char [] leftSortedChar = new char[n], rightSortedChar = new char[n];
		l = 0; r = n;
		for (int i : sortedLeft) {
			leftSortedChar[l++] = (char)i;
			rightSortedChar[--r] = (char)i;
		}
		int middle = rand.nextInt(minValue, maxValueExclusive);
		if (rand.nextBoolean()) {
			palindrom = new String(leftChar) + (char) middle + new String(rightChar);
			expected = new String(leftSortedChar) + (char) middle + new String(rightSortedChar);
		} else {
			palindrom = new String(leftChar) +  new String(rightChar);
			expected = new String(leftSortedChar) + new String(rightSortedChar);
		}
	}
}
