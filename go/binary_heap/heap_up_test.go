package binary_heap

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestHeapUp_RandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomTestData()

		assert.True(t, minHeapIsOK(td.heap))

		td.heap[td.randomIndex] -= td.randomValue
		actual := append([]int{}, td.heap...)
		HeapUp(actual, td.randomIndex)

		if !minHeapIsOK(actual) {
			assert.FailNowf(t, "",
				"HeapUp(%v, %v): not a valid heap after the call:\n%v",
				td.heap, td.randomIndex, actual,
			)
		}

		if !haveSameElements(actual, td.heap) {
			assert.FailNowf(t, "",
				"HeapUp(%v, %v): not same elements after the call:\n%v",
				td.heap, td.randomIndex, actual,
			)
		}
	}
}
