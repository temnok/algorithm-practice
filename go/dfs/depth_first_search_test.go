package dfs

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestDfs(t *testing.T) {
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
	expected1 := []int{0, 1, 2, 5, 8, 4, 7, 3, 6}
	expected2 := []int{0, 3, 6, 7, 8, 2, 5, 4, 1}

	actual := Dfs(graph, 0)
	if !reflect.DeepEqual(actual, expected1) && !reflect.DeepEqual(actual, expected2) {
		assert.FailNowf(t, "", "Dfs(%v, 0):\n got %v\nwant %v or %v", graph, actual, expected1, expected2)
	}
}

// Example from https://en.wikipedia.org/wiki/Depth-first_search
func TestDfsWiki(t *testing.T) {
	graph := [][]int{
		0:  {},
		1:  {2, 7, 8},
		2:  {3, 6},
		3:  {4, 5},
		4:  {},
		5:  {},
		6:  {},
		7:  {},
		8:  {9, 12},
		9:  {10, 11},
		10: {},
		11: {},
		12: {},
	}
	expected1 := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}
	expected2 := []int{1, 8, 12, 9, 11, 10, 7, 2, 6, 3, 5, 4}

	actual := Dfs(graph, 1)
	if !reflect.DeepEqual(actual, expected1) && !reflect.DeepEqual(actual, expected2) {
		assert.FailNowf(t, "", "Dfs(%v, 0):\n got %v\nwant %v or %v", graph, actual, expected1, expected2)
	}
}

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected1 := td.order1
		expected2 := td.order2
		actual := Dfs(td.graph, td.start)

		if !reflect.DeepEqual(expected1, actual) && !reflect.DeepEqual(expected2, actual) {
			assert.FailNowf(t, "",
				"Dfs(%v, %v):\nwant %v or %v\ngot %v",
				td.graph, td.start, expected1, expected2, actual,
			)
		}
	}
}
