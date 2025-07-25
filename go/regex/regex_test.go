package regex

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"regexp"
	"testing"
)

func TestRandomCases(t *testing.T) {
	var random = rand.New(rand.NewPCG(0, 0))

	for test := 0; test < 1_000_000; test++ {
		str := make([]byte, random.IntN(50))
		strFreq := 1 + random.IntN(20)
		for i := range str {
			str[i] = byte('a' + random.IntN(strFreq))
		}

		pat := make([]byte, 1+random.IntN(20))
		dotProb := 1 + random.IntN(20)
		patFreq := 1 + random.IntN(20)
		for j := 0; j < len(pat); j++ {
			pat[j] = byte('a' + random.IntN(patFreq))
			if random.IntN(100) < dotProb {
				pat[j] = '.'
			}

			if j+1 < len(pat) {
				switch prob := random.IntN(100); {
				case prob < 20:
					j++
					pat[j] = '?'
				case prob < 40:
					j++
					pat[j] = '+'
				case prob < 60:
					j++
					pat[j] = '*'
				}
			}
		}

		want, err := regexp.Match("^"+string(pat)+"$", str)
		assert.NoError(t, err)

		got := matchesFully(string(str), string(pat))
		if got != want {
			t.Fatalf("matchesFully(%q, %q):\nwant %v\n got %v", str, pat, want, got)
		}
	}
}
