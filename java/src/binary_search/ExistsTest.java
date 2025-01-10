package binary_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ExistsTest {
	@Test
	public void testExistsInt() {
		for (var test = 0; test < 1_000; test++) {
			var td = testData.generate();

			var expected = td.expectedFirst >= 0;
			var actual = Exists.existsInt(td.array, td.val);
			if (actual != expected) {
				Assert.fail(
					String.format("existsInt(%s, %s):\n  Actual: %s\nExpected: %s",
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
