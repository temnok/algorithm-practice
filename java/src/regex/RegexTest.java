package regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.regex.Pattern;

public class RegexTest {
	@Test
	public void testRandomCases() {
		Random r = new Random(0);
		for (int test = 0; test < 1_000_000; test++) {
			int n = r.nextInt(50), m = 1 + r.nextInt(20);
			int s = 1 + r.nextInt(20), p = 1 + r.nextInt(20);
			int dotProb = 1 + r.nextInt(20);
			char[] str = new char[n], pat = new char[m];
			for (int i = 0; i < n; i++) {
				str[i] = (char) ('a' + r.nextInt(s));
			}
			for (int j = 0; j < m; j++) {
				pat[j] = r.nextInt(100) < dotProb ? '.' : (char) ('a' + r.nextInt(p));
				if (j < m - 1) {
					int per = r.nextInt(100);
					if (per < 20) {
						pat[++j] = '*';
					} else if (per < 40) {
						pat[++j] = '+';
					} else if (per < 60) {
						pat[++j] = '?';
					}
				}
			}
			String ss = new String(str), pp = new String(pat);
			var want = ss.matches("^" + pp + "$");
			var got = Regex.matchesFully(ss, pp);
			if (got != want) {
				Assert.assertEquals(String.format("matchesFully(\"%s\", \"%s\")", ss, pp), want, got);
			}
		}
	}
}