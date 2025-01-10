package quick_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RangeTest {
	@Test
	public void testRangeInt() {
		for (var test = 0; test < 1_000; test++) {
			var td = testData.generate();

			var actual = td.array.clone();
			Range.rangeInt(actual, 0, actual.length);

			if (!Arrays.equals(td.expectedArray, actual)) {
				Assert.fail(
					String.format("rangeInt(%s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(td.array),
						Arrays.toString(actual),
						Arrays.toString(td.expectedArray)
					)
				);
			}
		}
	}
}
