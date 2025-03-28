package trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FindPrefixTest {
	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();

			var actual = FindPrefix.hasPrefix(td.strings);
			if (actual != td.hasPrefix) {
				Assert.fail(String.format("hasPrefix(%s):\nwant %s\n got %s",
					Arrays.toString(td.strings), td.hasPrefix, actual));
			}
		}
	}
}
