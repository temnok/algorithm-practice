package dijkstra

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	expected := []int{0, 30, 10, -1}
	actual := DijkstraMinDist(4, [][]int{{0, 2, 10}, {0, 1, 100}, {2, 1, 20}}, 0)
	assert.Equal(t, expected, actual)
}

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		actual := DijkstraMinDist(td.n, td.edges, td.start)
		if !reflect.DeepEqual(actual, td.expected) {
			assert.FailNowf(t, "",
				"DijkstraMinDist(%v, %v, %v):\nwant %v\n got %v",
				td.n, td.edges, td.start, td.expected, actual,
			)
		}
	}
}
