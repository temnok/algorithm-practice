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
		MergeSortArray(actual, func(a, b string) int {
			return len(a) - len(b)
		})

		if !reflect.DeepEqual(td.expectedArray, actual) {
			assert.FailNowf(t, "",
				"MergeSortArray(%v):\n  Actual: %v\nExpected: %v",
				td.array, actual, td.expectedArray,
			)
		}
	}
}
