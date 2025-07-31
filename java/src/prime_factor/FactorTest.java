package prime_factor;

import org.junit.Assert;
import org.junit.Test;

public class FactorTest {
	@Test
	public void test25() {
		Assert.assertArrayEquals(
			new short[]{0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5},
			Factor.listSmallestPrimeFactors(26));
	}

	@Test
	public void test1M() {
		var factors = Factor.listSmallestPrimeFactors(1_000_000);
		var primes = 1;
		for (var i = 3; i < factors.length; i += 2) {
			if (factors[i] == 0) {
				primes++;
			}
		}

		Assert.assertEquals(78_498, primes);
	}

	@Test
	public void test1B() {
		var factors = Factor.listSmallestPrimeFactors(1_000_000_000);
		var primeCount = 1;
		for (var i = 3; i < factors.length; i += 2) {
			if (factors[i] == 0) {
				primeCount++;
			}
		}

		Assert.assertEquals(50_847_534, primeCount);
	}
}
