
# list_primes should return all prime numbers p, (p < n) *in linear time*.
# For example, for n = 26, the answer should be
# [2, 3, 5, 7, 11, 13, 17, 19, 23]
def list_primes(n: int) -> list[int]:
	# raise NotImplementedError('TODO')

	factors = [0]*n
	primes = []

	for i in range(2, n):
		f = factors[i]
		if f == 0:
			f = i
			primes.append(i)

		for p in primes:
			if p > f or p*i >= n:
				break

			factors[p*i] = p

	return primes
