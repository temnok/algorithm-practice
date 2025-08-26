package mst

import (
	"testing"
)

func TestPrimRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()
		got := PrimMinimumSpanningForest(td.n, append([][]int{}, td.edges...))
		td.assertAnswer(t, "PrimMinimumSpanningForest", got)
	}
}
