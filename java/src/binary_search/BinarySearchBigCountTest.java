package binary_search;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class BinarySearchBigCountTest {
	private static long count = 1_000_000_000_000_000_000L;

	private static class Array1 implements BinarySearchBigCount.Array {
		public long length() {
			return count;
		}

		public int get(long index) {
			if (index < 0 || index >= count) {
				throw new IndexOutOfBoundsException();
			}

			return 0;
		}
	}

	private static class Array1000 implements BinarySearchBigCount.Array {
		public long length() {
			return count;
		}

		public int get(long index) {
			if (index < 0 || index >= count) {
				throw new IndexOutOfBoundsException();
			}

			return (int)(index / (count / 1000));
		}
	}

	@ClassRule
	public static Timeout classTimeout = Timeout.seconds(1);

	@Test
	public void test1() {
		var arr = new Array1();
		Assert.assertEquals(0, BinarySearchBigCount.bigCount(arr, -1));
		Assert.assertEquals(count, BinarySearchBigCount.bigCount(arr, 0));
		Assert.assertEquals(0, BinarySearchBigCount.bigCount(arr, 1));
	}

	@Test
	public void test1000() {
		var arr = new Array1000();
		Assert.assertEquals(0, BinarySearchBigCount.bigCount(arr, -1));
		Assert.assertEquals(count/1000, BinarySearchBigCount.bigCount(arr, 0));
		Assert.assertEquals(count/1000, BinarySearchBigCount.bigCount(arr, 500));
		Assert.assertEquals(count/1000, BinarySearchBigCount.bigCount(arr, 999));
		Assert.assertEquals(0, BinarySearchBigCount.bigCount(arr, 1000));
	}
}
