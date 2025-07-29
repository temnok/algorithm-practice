package disjoint_set

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"testing"
)

func TestLargeArray(t *testing.T) {
	var n = 1 << 24
	var set = NewDisjointSet(n)
	for step := 1; step < n; step *= 2 {
		for i := 0; i < n; i += step * 2 {
			j := i + step
			assert.False(t, set.Find(i) == set.Find(j))
			assert.True(t, set.Union(i, j))
			assert.True(t, set.Find(i) == set.Find(j))
		}
	}
}

func TestRandomCases(t *testing.T) {
	var random = rand.New(rand.NewPCG(0, 0))

	for range 1_000 {
		n := 1 + random.IntN(1_000)
		ref := make([]int, n)
		for i := range n {
			ref[i] = i
		}

		set := NewDisjointSet(n)

		for range 1_000 {
			i, j := random.IntN(n), random.IntN(n)
			if random.IntN(2) == 0 {
				want := ref[i] == ref[j]
				got := set.Find(i) == set.Find(j)
				if want != got {
					t.Fatalf("DisjoinSet(%v):\nFind(%v)==Find(%v):\nwant %v\n got %v",
						ref, i, j, want, got)
				}
			} else {
				want := ref[i] != ref[j]
				if want {
					old, new := ref[i], ref[j]
					for k := range n {
						if ref[k] == old {
							ref[k] = new
						}
					}
				}

				got := set.Union(i, j)
				if want != got {
					t.Fatalf("DisjoinSet(%v):\nUnion(%v,%v):\nwant %v\n got %v",
						ref, i, j, want, got)
				}
			}
		}
	}
}
