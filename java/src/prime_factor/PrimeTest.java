package prime_factor;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PrimeTest {
	@Test
	public void test25() {
		Assert.assertEquals(
			Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23),
			Prime.listPrimes(26));
	}

	@Test
	public void test1M() {
		var primes = Prime.listPrimes(1_000_000);

		Assert.assertEquals(78_498, primes.size());
		Assert.assertEquals(999_983, (int)primes.get(78_497));
	}

	@Test
	public void test1B() {
		var primes = Prime.listPrimes(1_000_000_000);

		Assert.assertEquals(50_847_534, primes.size());
		Assert.assertEquals(999_999_937, (int)primes.get(50_847_533));
	}
}
