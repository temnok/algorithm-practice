package prime_factor;

public class Factor {
	// listSmallestPrimeFactors should return *the smallest prime factor*
	// for each number i < n, or 0 if number i is a prime or numbers 0 or 1.
	// For example, for n = 26, the answer should be
	// [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5]
	public static short[] listSmallestPrimeFactors(int n) {
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

		return factors;
	}
}
