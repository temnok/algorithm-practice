package bfs

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestMinDist_RandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.minDist
		actual := MinDist(td.graph, td.start, td.end)

		if expected != actual {
			assert.FailNowf(t, "",
				"MinDist(%v, %v, %v):\nwant %v\n got %v",
				td.graph, td.start, td.end, expected, actual,
			)
		}
	}
}
