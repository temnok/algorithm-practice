package prime_factor

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestPrimes26(t *testing.T) {
	assert.Equal(t, []int{2, 3, 5, 7, 11, 13, 17, 19, 23}, listPrimes(26))
}

func TestPrimes1M(t *testing.T) {
	primes := listPrimes(1_000_000)

	assert.Equal(t, 78_498, len(primes))
	assert.Equal(t, 999_983, primes[78_497])
}

func TestPrimes1B(t *testing.T) {
	primes := listPrimes(1_000_000_000)

	assert.Equal(t, 50_847_534, len(primes))
	assert.Equal(t, 999_999_937, primes[50_847_533])
}
