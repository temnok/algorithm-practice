from factor import list_smallest_prime_factors


def test_factors_26():
	want = [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5]
	assert list_smallest_prime_factors(26) == want


def test_factors_1m():
	factors = list_smallest_prime_factors(1_000_000)
	primes = 1
	for i in range(3, len(factors), 2):
		if factors[i] == 0:
			primes += 1

	assert primes == 78_498


def test_factors_10m():
	factors = list_smallest_prime_factors(10_000_000)
	primes = 1
	for i in range(3, len(factors), 2):
		if factors[i] == 0:
			primes += 1

	assert primes == 664_579
