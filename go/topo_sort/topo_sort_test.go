package topo_sort

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestExample(t *testing.T) {
	assert.Equal(t, []int{3, 0, 2, 1}, TopoSort([][]int{{2, 1}, {}, {1}, {0, 2}}))

	assert.Equal(t, 0, len(TopoSort([][]int{{2, 1}, {3}, {1}, {0, 2}})))
	assert.Equal(t, 0, len(TopoSort([][]int{{0}})))
	assert.Equal(t, 0, len(TopoSort([][]int{{1}, {0}})))
}

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		actual := TopoSort(td.adj)

		if td.hasCycle {
			assert.Equal(t, 0, len(actual))
			continue
		}

		n := len(td.graph)
		assert.Equal(t, n, len(actual))

		order := make([]int, n)
		for i := range n {
			order[actual[i]] = i
		}

		for u := range n {
			for _, v := range td.graph[u] {
				if !(order[u] < order[v]) {
					assert.FailNowf(t, "",
						"TopoSort(%v):\n  Node %v should come before node %v in the result!\n  Result: %v",
						td.graph, u, v, actual,
					)
				}
			}
		}
	}
}
