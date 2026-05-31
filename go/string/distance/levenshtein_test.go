package distance

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"testing"
)

func TestLevenshtein(t *testing.T) {
	assert.Equal(t, 1, Levenshtein("aaa", "aa"))
	assert.Equal(t, 3, Levenshtein("kitten", "sitting"))
}

func TestLevenshtein_RandomCases(t *testing.T) {
	random := rand.New(rand.NewPCG(0, 0))

	for range 10_000 {
		m, n, r := random.IntN(100), random.IntN(100), 1 + random.IntN(26)
		a1, b1 := make([]byte, m), make([]byte, n)
		for i := range a1 {
			a1[i] = byte('a' + random.IntN(r))
		}
		for i := range b1 {
			b1[i] = byte('a' + random.IntN(r))
		}

		mem := make([]int, m*n)
		var rec func(a, b string) int
		rec = func(a, b string) int {
			i, j := len(a)-1, len(b)-1
			if i < 0 { return j+1 }
			if j < 0 { return i+1 }

			p := i*n + j
			if mem[p] == 0 {
				d := 1
				if a[i] == b[j] {
					d = 0
				}

				mem[p] = 1+min(rec(a[:i], b)+1, rec(a, b[:j])+1, rec(a[:i], b[:j]) + d)
			}
			return mem[p]-1
		}

		a, b := string(a1), string(b1)
		if want, got := rec(a, b), Levenshtein(a, b); want != got {
			t.Fatalf("Levenshtein(%q, %q):\nwant %v\n got %v", a, b, want, got)
		}
	}
}