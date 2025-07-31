package prime_factor

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestFactors26(t *testing.T) {
	assert.Equal(t,
		[]int16{0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5},
		listSmallestPrimeFactors(26),
	)
}

func TestFactors1M(t *testing.T) {
	factors := listSmallestPrimeFactors(1_000_000)
	primes := 1
	for i := 3; i < len(factors); i += 2 {
		if factors[i] == 0 {
			primes++
		}
	}

	assert.Equal(t, 78_498, primes)
}

func TestFactors1B(t *testing.T) {
	factors := listSmallestPrimeFactors(1_000_000_000)
	primes := 1
	for i := 3; i < len(factors); i += 2 {
		if factors[i] == 0 {
			primes++
		}
	}

	assert.Equal(t, 50_847_534, primes)
}
