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
		MergeSortInts(actual)

		if !reflect.DeepEqual(td.expectedArray, actual) {
			assert.FailNowf(t, "",
				"MergeSortInts(%v):\n  Actual: %v\nExpected: %v",
				td.array, actual, td.expectedArray,
			)
		}
	}

}
