package sliding_window;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SlidingWindowMinTest {
	@Test
	public void testExample() {
		var want = new int[] {-1, 0, 0, -10};
		var got = SlidingWindowMin.slidingWindowMin(new int[] {-1, 2, 1, 0, 5, -10}, 3);
		Assert.assertArrayEquals(want, got);
	}

	@Test
	public void testRandomCases() {
		var random = new Random(0);

		for (var test = 0; test < 100_000; test++) {
			var arr = new int[1+random.nextInt(100)];
			var n = 1 + random.nextInt(arr.length);

			for (var i = 0; i < arr.length; i++) {
				arr[i] = random.nextInt();
			}

			var got = SlidingWindowMin.slidingWindowMin(arr, n);
			var want = new int[arr.length-n+1];

			for (var i = 0; i < want.length; i++) {
				var val = arr[i];
				for (var j = 0; j < n - 1; j++) {
					val = Math.min(val, arr[i+1+j]);
				}
				want[i] = val;
			}

			if (!Arrays.equals(want, got)) {
				Assert.assertEquals(String.format("SlidingWindowMin(%s, %s)",
					Arrays.toString(arr), n), want, got);
			}
		}
	}
}
