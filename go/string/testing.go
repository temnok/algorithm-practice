package string

import "math/rand/v2"

func RandomString(rand *rand.Rand, maxLen int) string {
	bytes := make([]byte, 1+rand.IntN(maxLen))
	maxChar := 1 + rand.IntN(26)

	for i := range bytes {
		bytes[i] = byte('a' + rand.IntN(maxChar))
	}

	return string(bytes)
}
