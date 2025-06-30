package bfs

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestMinPath_RandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.minPath
		actual := minPath(td.graph, td.start, td.end)

		if !reflect.DeepEqual(expected, actual) {
			assert.FailNowf(t, "",
				"minPath(%v, %v, %v):\nwant %v\n got %v",
				td.graph, td.start, td.end, expected, actual,
			)
		}
	}
}
