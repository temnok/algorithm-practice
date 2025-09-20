package binary_search;

public class BinarySearchBigCount {
	public interface Array {
		long length();
		int get(long index);
	}

	// bigCount should return number of value occurences in a sorted array
	public static long bigCount(Array arr, int val) {
//		throw new UnsupportedOperationException("TODO");

		long l = 0, r = arr.length();
		while (l < r) {
			var m = (l + r)/2;
			if (arr.get(m) < val) {
				l = m+1;
			} else {
				r = m;
			}
		}

		var l0 = l;
		l = 0; r = arr.length();
		while (l < r) {
			var m = (l + r)/2;
			if (arr.get(m) <= val) {
				l = m+1;
			} else {
				r = m;
			}
		}

		return r - l0;
	}
}
