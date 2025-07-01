package counting_sort;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class PalindromicRearrangementTest {
	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(2);

	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestDataPalindrom();

			var actual = PalindromicRearrangement.getSmallestPalindrom(td.palindrom);
			Assert.assertEquals(String.format("Initial string: %s\n  Expected:%s\n  Actual: %s\n ",
					td.palindrom, td.expected, actual),
				td.expected, actual);
		}
	}
}
