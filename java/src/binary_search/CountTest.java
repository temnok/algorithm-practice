package binary_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CountTest {
	@Test
	public void testCount() {
		for (var test = 0; test < 1_000; test++) {
			var tc = TestCase.generate();

			var expected = tc.expectedCount;
			var actual = Count.countInt(tc.array, tc.val);
			if (actual != expected) {
				Assert.fail(
					String.format("countInt(%s, %s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(tc.array),
						tc.val,
						actual,
						expected
					)
				);
			}
		}
	}
}
