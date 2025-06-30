package binary_heap

import (
	"github.com/stretchr/testify/assert"
	"reflect"
	"sort"
	"testing"
)

func TestHeapSort_RandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomTestData()

		expected := append([]int{}, td.array...)
		sort.Ints(expected)

		actual := append([]int{}, td.array...)
		heapSort(actual)

		if reflect.DeepEqual(actual, expected) {
			assert.FailNowf(t, "",
				"heapSort(%v):\nwant %v\n got %v", td.array, expected, actual,
			)
		}
	}
}
