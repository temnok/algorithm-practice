package merge_sort

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestStringRandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomStringTestData()

		actual := append([]string{}, td.array...)
		mergeSortArray(actual, func(i, j int) int {
			return len(actual[i]) - len(actual[j])
		})

		if !reflect.DeepEqual(td.expectedArray, actual) {
			assert.FailNowf(t, "",
				"mergeSortArray(%v):\n  Actual: %v\nExpected: %v",
				td.array, actual, td.expectedArray,
			)
		}
	}

}
