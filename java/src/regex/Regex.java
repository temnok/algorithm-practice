package regex;

import java.util.Arrays;

public class Regex {
	// matchesFully should return true if str *fully* matches given regular expression pattern.
	// The pattern can contain lowercase latin letters and symbols '.', '?', '+', '*' with
	// the standard regexp behavior, as for java.util.regex.Pattern.
	public static boolean matchesFully(String str, String pattern) {
		int n = str.length();
		int m = pattern.length();
		boolean[] state = new boolean[m + 1];
		boolean[] next = new boolean[m + 1];
		state[0] = true;

		for (int i = 0;; i++) {
			for (int j = 0; j < m; j++) {
				var p = pattern.charAt(j);
				if ((p == '?' || p == '*') && state[j - 1]) {
					state[j + 1] = true;
				}
			}
			if (i == n) {
				return state[m];
			}
			for (int j = 0; j < m; j++) {
				var p = pattern.charAt(j);
				if (!state[j] ||(p != str.charAt(i) && p != '.')){
					continue;
				}

				if (j + 1 < m) {
					p = pattern.charAt(j + 1);
					if (p == '?' || p == '*' || p == '+') {
						if (p != '?') {
							next[j] = true;
						}
						j++;
					}

				}
				next[j + 1] = true;
			}
			boolean[] tmp = state;
			state = next;
			next = tmp;
			Arrays.fill(next, false);
		}

	}

	public static void main(String[] args) {
		System.out.println(matchesFully("", "a?a*"));
	}
}
