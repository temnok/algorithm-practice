package disjoint_set

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestLargeArray(t *testing.T) {
	var n = 1 << 24
	var set = newDisjointSet(n)
	for step := 1; step < n; step *= 2 {
		for i := 0; i < n; i += step * 2 {
			j := i + step
			assert.False(t, set.find(i) == set.find(j))
			assert.True(t, set.union(i, j))
			assert.True(t, set.find(i) == set.find(j))
		}
	}
}

func TestRandomCases(t *testing.T) {
	for range 1_000 {
		n := 1 + random.IntN(8*1024)
		td := newRandomTestData(n)

		set := newDisjointSet(n)
		for op := range td.ops {
			i, j, k := td.indexes[op*3], td.indexes[op*3+1], td.indexes[op*3+2]
			assert.Equal(t, td.expectedUnions[op], set.union(i, j))
			assert.Equal(t, td.expectedFinds[op], set.find(k))
		}
	}
}
