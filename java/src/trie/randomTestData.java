package trie;

import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	String[] strings;
	boolean hasPrefix;

	randomTestData() {
		var n = 1 + rand.nextInt(26);
		var m = 1 + rand.nextInt(20);

		strings = new String[1 + rand.nextInt(20)];
		for (var i = 0; i < strings.length; i++) {
			var buf = new char[1 + rand.nextInt(m)];
			for (var j = 0; j < buf.length; j++) {
				buf[j] = (char)('a' + rand.nextInt(n));
			}
			strings[i] = new String(buf);
		}

		for (var i = 0; i < strings.length; i++) {
			for (var j = 0; j < strings.length; j++) {
				if (i != j && strings[i].startsWith(strings[j])) {
					hasPrefix = true;
					break;
				}
			}
		}
	}
}
