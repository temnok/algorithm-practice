package merge_sort

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestIntRandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomIntTestData()

		actual := append([]int{}, td.array...)
		mergeSortInts(actual)

		if !reflect.DeepEqual(td.expectedArray, actual) {
			assert.FailNowf(t, "",
				"mergeSortInts(%v):\n  Actual: %v\nExpected: %v",
				td.array, actual, td.expectedArray,
			)
		}
	}

}
