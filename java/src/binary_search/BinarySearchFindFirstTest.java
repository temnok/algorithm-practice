package binary_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchFindFirstTest {
	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = binarySearchTestData.generate();

			var expected = td.expectedFirst;
			var actual = BinarySearchFindFirst.findFirstInt(td.array, td.val);
			if (actual != expected) {
				Assert.fail(
					String.format("findFirstInt(%s, %s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(td.array),
						td.val,
						actual,
						expected
					)
				);
			}
		}
	}
}
