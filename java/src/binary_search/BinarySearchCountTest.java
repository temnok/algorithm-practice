package binary_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchCountTest {
	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestData();

			var expected = td.expectedCount;
			var actual = BinarySearchCount.countInt(td.array, td.val);
			if (actual != expected) {
				Assert.fail(
					String.format("countInt(%s, %s):\n  Actual: %s\nExpected: %s",
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
