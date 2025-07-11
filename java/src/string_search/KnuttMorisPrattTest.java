package string_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class KnuttMorisPrattTest {
	@Test
	public void testGetPrefixes() {
		int [] expected = {0, 1, 0, 1, 2, 2, 3};
		int [] actual = KnuttMorisPratt.getPrefixFunction("aabaaab");

		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Prefix function should be: %s but is: %s", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	@Test
	public void testGetPrefixes2() {
		int [] expected = {0, 1, 0, 1, 2, 2, 3};
		int [] actual = KnuttMorisPratt.getPrefixFunction("aabaaab");

		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Prefix function should be: %s but is: %s", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	@Test
	public void testSearchPrefix() {
		String substring = "babeb";

		int [] expected = {0, 0, 1, 0, 1};
		int [] actual = KnuttMorisPratt.getPrefixFunction(substring);
		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Prefix function should be: %s but is: %s", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	@Test
	public void testSearch() {
		String source = "bababababababebcde";
		String substring = "babeb";
		int actual = KnuttMorisPratt.search(source, substring);
		Assert.assertEquals(String.format("Substring should be at position %s but was at position %s", 10, actual), 10, actual);
	}

	@Test
	public void testSearchSimple() {
		String source = "knof";
		String substring = "kno";
		int actual = KnuttMorisPratt.search(source, substring);
		Assert.assertEquals(String.format("Substring should be at position %s but was at position %s", 10, actual), 0, actual);
	}

	@Test
	public void testSearchRandom() {
 		for (int i = 0 ; i < 10000; i++) {
			randomTestData td = new randomTestData();

			String substring = td.prefix;
			String source = td.source;
			int expected = getSubstringIndex(source, substring);
			int actual = KnuttMorisPratt.search(source, substring);

			Assert.assertEquals(String.format("For string: %s,\n substring: %s,\n start index should be %s but was %s",
				source, substring, expected, actual), expected, actual);
		}
	}

	public static int getSubstringIndex(String source, String substring) {
		for (int i = 0; i < source.length() - substring.length(); i++) {
			if (source.substring(i).startsWith(substring)) {
				return i;
			}
		}
		return -1;
	}
}
