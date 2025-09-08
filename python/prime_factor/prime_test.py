
from prime import list_primes


def test_primes_26():
	assert list_primes(26) == [2, 3, 5, 7, 11, 13, 17, 19, 23]


def test_primes_1m():
	primes = list_primes(1_000_000)

	assert len(primes) == 78_498
	assert primes[78_497] == 999_983


def test_primes_10m():
	primes = list_primes(10_000_000)

	assert len(primes) == 664_579
	assert primes[664_578] == 9_999_991
