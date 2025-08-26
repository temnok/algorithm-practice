package mst

import (
	"testing"
)

func TestKruskalRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()
		got := KruskalMinimumSpanningForest(td.n, append([][]int{}, td.edges...))
		td.assertAnswer(t, "KruskalMinimumSpanningForest", got)
	}
}
