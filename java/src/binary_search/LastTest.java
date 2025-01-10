package binary_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LastTest {
	@Test
	public void testLastInt() {
		for (var test = 0; test < 1_000; test++) {
			var tc = TestCase.generate();

			var expected = tc.expectedLast;
			var actual = Last.lastInt(tc.array, tc.val);
			if (actual != expected) {
				Assert.fail(
					String.format("lastInt(%s, %s):\n  Actual: %s\nExpected: %s",
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
