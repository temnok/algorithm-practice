package regex;

public class Regex {
	// matchesFully should return true if str *fully* matches given regular expression pattern.
	// The pattern can contain lowercase latin letters and symbols '.', '?', '+', '*' with
	// the standard regexp behavior, as for java.util.regex.Pattern.
	public static boolean matchesFully(String str, String pattern) {
		//throw new UnsupportedOperationException("TODO");

		int m = str.length(), n = pattern.length();

		var state = new boolean[n+1];
		state[0] = true;

		for (var i = 0; i <= m; i++) {
			for (var j = 1; j < n; j++) {
				var p = pattern.charAt(j);
				if ((p == '?' || p == '*') && state[j-1]) {
					state[j+1] = true;
				}
			}

			if (i == m) break;

			var nextState = new boolean[n+1];

			for (var j = 0; j < n; j++) {
				var p = pattern.charAt(j);
				if ((p != str.charAt(i) && p != '.') || !state[j]) {
					continue;
				}

				if (j+1 < n) {
					p = pattern.charAt(j+1);
					if (p == '?' || p == '+' || p == '*') {
						if (p != '?') {
							nextState[j] = true;
						}

						j++;
					}
				}

				nextState[j+1] = true;
			}

			state = nextState;
		}

		return state[n];
	}
}
