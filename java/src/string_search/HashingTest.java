package string_search;

import org.junit.Assert;
import org.junit.Test;

public class HashingTest {
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
