package regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class RegexTest {
	@Test
	public void testRandomCases() {
		var random = new Random(0);

		for (var test = 0; test < 1_000_000; test++) {
			var str = new char[random.nextInt(50)];
			var strFreq = 1 + random.nextInt(20);
			for (var i = 0; i < str.length; i++) {
				str[i] = (char) ('a' + random.nextInt(strFreq));
			}

			var pat = new char[1 + random.nextInt(20)];
			var dotProb = 1 + random.nextInt(20);
			var patFreq = 1 + random.nextInt(20);
			for (var j = 0; j < pat.length; j++) {
				pat[j] = random.nextInt(100) < dotProb ? '.' : (char) ('a' + random.nextInt(patFreq));

				if (j+1 < pat.length) {
					int prob = random.nextInt(100);
					if (prob < 20) {
						pat[++j] = '*';
					} else if (prob < 40) {
						pat[++j] = '+';
					} else if (prob < 60) {
						pat[++j] = '?';
					}
				}
			}

			var s = new String(str);
			var p = new String(pat);
			var want = s.matches("^" + p + "$");
			var got = Regex.matchesFully(s, p);
			if (got != want) {
				Assert.assertEquals(String.format("matchesFully(\"%s\", \"%s\")", s, p), want, got);
			}
		}
	}
}