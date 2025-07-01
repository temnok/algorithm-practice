package counting_sort;

public class PalindromicRearrangement {
	/**
	 * You are given a palindromic string s.
	 * Return the lexicographically smallest palindromic permutation of s
	 * Examples: Input: s = "daccad", Output: "acddca"
	 * Input: s = "babab", Output: "abbba"
	 *
	 */
	public static String getSmallestPalindrom(String palindrom) {
		int base = (int) 'a';
		int count[] = new int[(int) 'z' - base + 1];

		for (char c : palindrom.toCharArray()) {
			count[(int) c - base]++;
		}

		char[] result = new char[palindrom.length()];
		int l = 0, r = result.length;
		int middle = -1;

		for (int i = 0; i < count.length; i++) {
			int c = count[i];
			if (c % 2 != 0) {
				middle = i;
			}
			for (int j = 0; j < c / 2; j++) {
				result[l++] = (char) (i + base);
				result[--r] = (char) (i + base);
			}
		}

		if (middle != -1) {
			result[l++] = (char) (middle + base);
		}


		return new String(result);
	}
}
