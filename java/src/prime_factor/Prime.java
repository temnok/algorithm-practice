package prime_factor;

import java.util.ArrayList;
import java.util.List;

public class Prime {
	// listPrimes should return all prime numbers p, (p < n) *in linear time*.
	// For example, for n = 26, the answer should be
	// [2, 3, 5, 7, 11, 13, 17, 19, 23]
	public static List<Integer> listPrimes(int n) {
		var factors = new short[n];
		var primes = new ArrayList<Integer>();

		for (var i = 2; i < n; i++) {
			var f = (int)factors[i];
			if (f == 0) {
				primes.add(i);
				f = i;
			}

			for (var p: primes) {
				var j = i*p;
				if (j >= n || p > f) {
					break;
				}

				factors[j] = (short)(int)p;
			}
		}

		return primes;
	}
}