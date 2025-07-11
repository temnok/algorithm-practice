package string_search;

import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	String prefix;

	String source;

	randomTestData() {
		var n = 1 + rand.nextInt(26);
		var m = 1 + rand.nextInt(20);

		var buf = new char[2 + rand.nextInt(m)];
		for (var j = 0; j < buf.length; j++) {
			buf[j] = (char) ('a' + rand.nextInt(n));
		}
		String originalString = new String(buf);
		var p = rand.nextInt(1, originalString.length());
		prefix = originalString.substring(0, p);
		source = buildResultString(prefix, originalString);
		if (rand.nextBoolean()) {
			var p1 = rand.nextInt(0, p);
			for (int i = 0; i < p1; i++) {
				prefix.toCharArray()[prefix.length() - 1 - i] = (char) ('a' + rand.nextInt(n));
			}
		}
	}

	private String buildResultString(String prefix, String string) {
		String s = string.substring(prefix.length());
		int random = rand.nextInt(0, s.length());

		return s.substring(0, random) + prefix + (random < s.length() ? s.substring(random) : "");
	}
}
