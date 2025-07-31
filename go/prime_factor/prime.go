package prime_factor

// listPrimes should return all prime numbers p, (p < n) *in linear time*.
// For example, for n = 26, the answer should be
// [2, 3, 5, 7, 11, 13, 17, 19, 23]
func listPrimes(n int) []int {
	factors := make([]int16, n)

	var primes []int
	for i := 2; i < n; i++ {
		f := int(factors[i])
		if f == 0 {
			f = i
			primes = append(primes, i)
		}

		for _, p := range primes {
			if p > f || p*i >= n {
				break
			}

			factors[p*i] = int16(p)
		}
	}

	return primes
}
