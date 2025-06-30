package max_path

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestExample(t *testing.T) {
	assert.Equal(t, 120, maxPathLength(5,
		[][]int{{3, 1, 100}, {1, 0, 20}, {3, 2, 30}, {2, 1, 10}, {3, 4, 110}}))
}

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()
		actual := maxPathLength(td.n, td.edges)
		if td.expected != actual {
			assert.FailNowf(t, "", "maxPathLength(%v, %v):\nwant %v\n got %v",
				td.n, td.edges, td.expected, actual)
		}
	}
}
