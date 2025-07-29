package quick_sort

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"testing"
)

func TestQuickSort_RandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomTestData()

		actual := append([]int{}, td.array...)
		QuickSortInts(actual)

		if !reflect.DeepEqual(td.expectedArray, actual) {
			assert.FailNowf(t, "",
				"QuickSortInts(%v):\n  Actual: %v\nExpected: %v",
				td.array, actual, td.expectedArray,
			)
		}
	}
}

func TestPerformanceForLargeZeroArray(t *testing.T) {
	arr := make([]int, 100_000_000)
	QuickSortInts(arr)
}

func TestPerformanceForLargeSparseArray(t *testing.T) {
	arr := make([]int, 100_000_000)

	for i := range arr {
		if i%1000 == 0 {
			arr[i] = len(arr) - i
		}
	}

	QuickSortInts(arr)
}
