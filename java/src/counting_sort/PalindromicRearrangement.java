package counting_sort;

public class PalindromicRearrangement {
	/**
	 * You are given a palindromic string s of characters c, 'a' <= c <= 'z'.
	 * Return the lexicographically smallest palindromic permutation of s
	 * Examples: Input: s = "daccad", Output: "acddca"
	 * Input: s = "babab", Output: "abbba"
	 *
	 */
	public static String getSmallestPalindrom(String palindrom) {
		//throw new UnsupportedOperationException("TODO");

		var p = palindrom.toCharArray();

		var counts = new int[26];
		for (var i = 0; i < p.length/2; i++) {
			counts[p[i] - 'a']++;
		}

		var i = 0;
		for (var j = 0; j < 26; j++) {
			for (var c = counts[j]; c > 0; c--) {
				p[i] = p[p.length-1 - i] = (char)(j + 'a');
				i++;
			}
		}

		return new String(p);
	}
}
