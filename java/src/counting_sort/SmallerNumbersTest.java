package counting_sort;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

public class SmallerNumbersTest {
	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(2);

	@Test
	public void testRandomCases() {
		for (var test = 0; test < 1_000; test++) {
			var td = new randomTestDataNode();

			var actual = SmallerNumbers.getSmallerNumbers(td.asInt().clone(), td.maxValue + 1);

			if (!Arrays.equals(td.getSmallerNumbers(), actual)) {
				Assert.fail(
					String.format("sortInts(%s):\n  Actual: %s\nExpected: %s",
						Arrays.toString(td.asInt()),
						Arrays.toString(actual),
						Arrays.toString(td.getSmallerNumbers())
					)
				);
			}
		}
	}
}
