package counting_sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class CountingSortTest {
	@Test
	public void testRandomCases() {
		var random = new Random(0);

		for (var test = 0; test < 10_000; test++) {
			var mi = random.nextInt(2_000_000_000) - 1_000_000_000;
			var ma = mi + random.nextInt(1000);

			var arr = new int[1 + random.nextInt(1000)];
			for (var i = 0; i < arr.length; i++) {
				arr[i] = mi + random.nextInt(ma-mi+1);
			}

			var want = arr.clone();
			Arrays.sort(want);

			var got = arr.clone();
			CountingSort.countingSort(got);

			if (!Arrays.equals(got, want)) {
				Assert.fail(
					String.format("countingSort(%s):\nwant %s\n got %s",
						Arrays.toString(arr),
						Arrays.toString(want),
						Arrays.toString(got)
					)
				);
			}
		}
	}
}
