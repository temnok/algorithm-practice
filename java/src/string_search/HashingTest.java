package string_search;

import org.junit.Assert;
import org.junit.Test;

public class HashingTest {
	@Test
	public void getHashesTest() {
		String s = "dasfasf;jadfja;dfjasdfj";
		int size = 5;
		long[] hashes = Hashing.getHashes(s, size);
		Assert.assertEquals(s.length() - size + 1, hashes.length);

		for (int i = 0; i < s.length() - size + 1; i++) {
			String str = s.substring(i, i + size);
			long h = 0;
			for (int j = 0; j < str.length(); j++) {
				h = h*Hashing.BASE + Hashing.toInt(str.toCharArray()[j]);
			}

			Assert.assertEquals(h, hashes[i]);

		}
	}

	@Test
	public void getHashTest() {
		String s = "iwcwiuig";

		long[] hashes = Hashing.getHashes(s, s.length());

		long h = 0;
		for (int j = 0; j < s.length(); j++) {
			h = h*Hashing.BASE + Hashing.toInt(s.toCharArray()[j]);
		}

		Assert.assertEquals(h, hashes[0]);
	}

	@Test
	public void testSearchRandomHashing() {
		for (int i = 0 ; i < 10000; i++) {
			randomTestData td = new randomTestData();

			String substring = td.prefix;
			String source = td.source;
			int expected = KnuttMorisPrattTest.getSubstringIndex(source, substring);
			int actual = Hashing.search(source, substring);

			Assert.assertEquals(String.format("For string: %s,\n substring: %s,\n start index should be %s but was %s",
				source, substring, expected, actual), expected, actual);
		}
	}
}
