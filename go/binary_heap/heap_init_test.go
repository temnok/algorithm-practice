package binary_heap

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestHeapInit_RandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomTestData()

		assert.True(t, minHeapIsOK(td.heap))

		actual := append([]int{}, td.array...)
		HeapInit(actual)

		if !minHeapIsOK(actual) {
			assert.FailNowf(t, "",
				"HeapInit(%v): not a valid heap after the call:\n%v",
				td.array, actual,
			)
		}

		if !haveSameElements(actual, td.array) {
			assert.FailNowf(t, "",
				"HeapInit(%v): not same elements after the call:\n%v",
				td.array, actual,
			)
		}
	}
}
