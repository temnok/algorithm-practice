package quick_sort;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

public class IthMinTest {
	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(10);

	@Test
	public void testRandomCases() {
		testRandomCases(1000, 100);
	}

	@Test
	public void testRandomCases_largeArrays() {
		testRandomCases(100, 100_000);
	}

	private void testRandomCases(int testCount, int maxN) {
		for (var test = 0; test < 100; test++) {
			var td = new randomTestData(100_000);

			var expected = td.expectedArray[td.randomIndex];
			var actual = IthMin.ithMin(td.array, td.randomIndex);

			if (actual != expected) {
				Assert.fail(
					String.format("ithMin(%s, %s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(td.array), td.randomIndex,
						actual, expected
					)
				);
			}
		}
	}
}
