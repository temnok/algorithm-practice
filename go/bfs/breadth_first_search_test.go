package bfs

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestBfs(t *testing.T) {
	graph := [][]int{
		{1, 3},
		{2, 4},
		{5},
		{4, 6},
		{5, 7},
		{8},
		{7},
		{8},
		{2},
	}

	expected := []int{0, 1, 3, 2, 4, 6, 5, 7, 8}

	var actual = Bfs(graph, 0)
	if !reflect.DeepEqual(actual, expected) {
		assert.FailNowf(t, "", "dfs(%v, 0):\nwant %v\n got %v", graph, expected, actual)
	}
}

func TestBfsRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.order
		actual := Bfs(td.graph, td.start)

		if !reflect.DeepEqual(actual, expected) {
			assert.FailNowf(t, "",
				"Bfs(%s, %s):\nwant %s\n got %s",
				td.graph, td.start, expected, actual,
			)
		}
	}
}
