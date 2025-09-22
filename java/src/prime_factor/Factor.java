package prime_factor;

import java.util.ArrayList;
import java.util.List;

public class Factor {
	// listSmallestPrimeFactors should return *the smallest prime factor*
	// for each number i < n, or 0 if number i is a prime or numbers 0 or 1.
	// For example, for n = 26, the answer should be
	// [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5]
	public static short[] listSmallestPrimeFactors(int n) {
		short[] factor = new short[n];
		List<Integer> simple = new ArrayList<>();

		for (int i = 2; i < n; i++) {
			int f = factor[i];
			if (f == 0) {
				simple.addLast(i);
				f = i;
			}
			for (int s : simple) {
				if (s > f || s * i >= n) {
					break;
				}

				factor[s*i] = (short) s;
			}
		}

		return factor;
	}
}
