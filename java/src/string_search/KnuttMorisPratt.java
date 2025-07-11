package string_search;

public class KnuttMorisPratt {
	public static int[] getPrefixFunction(String source) {
		int [] result = new int[source.length()];
		char s[] = source.toCharArray();

		for (int i = 1; i < s.length; i++) {
			int p = result[i - 1];

			while (p > 0 && s[i] != s[p]) {
				p = result[p - 1];
			}
			if (s[i] == s[p]) {
				p++;
			}
			result[i] = p;
		}

		return result;
	}

	// 0, 1, 2, 3   < 2
	// 1, 2        < 1 = i - s1.length

	public static int search(String source, String substring) {
		char[] sourceChars = source.toCharArray();
		char[] substringChars = substring.toCharArray();
		int [] prefix = getPrefixFunction(substring);

		int sI = 0;

		for (int i = 0; i < sourceChars.length; ) {
			if (sourceChars[i] == substringChars[sI]) {
				if (sI == substringChars.length - 1) {
					return i - substringChars.length + 1;
				}
				i++;
				sI++;
			} else {
				if (sI == 0) {
					if (i + substringChars.length >= sourceChars.length) {
						return -1;
					}
					i++;
				} else {
					sI = prefix[sI - 1];
				}
			}
		}
		return -1;
	}
}
