package binary_search;

public class BinarySearchBigCount {
	public interface Array {
		long length();
		int get(long index);
	}

	// bigCount should return number of value occurences in a sorted array
	public static long bigCount(Array arr, int val) {
		long l = 0, r = arr.length();

		while (l < r) {
			long m = (l + r)/2;
			if (arr.get(m) >= val) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		long l1 = l;
		r = arr.length();

		while (l < r) {
			long m = (l + r)/2;
			if (arr.get(m) > val) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		return l - l1;
	}
}
