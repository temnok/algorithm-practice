package binary_heap

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestHeapSet_RandomCases(t *testing.T) {
	for range 1_000 {
		td := newRandomTestData()

		assert.True(t, minHeapIsOK(td.heap))

		actual := append([]int{}, td.heap...)
		heapSet(actual, td.randomIndex, td.randomValue)

		td.heap[td.randomIndex] = td.randomValue

		if !minHeapIsOK(actual) {
			assert.FailNowf(t, "",
				"heapSet(%v, %v, %v): not a valid heap after the call:\n%v",
				td.heap, td.randomIndex, td.randomValue, actual,
			)
		}

		if !haveSameElements(actual, td.heap) {
			assert.FailNowf(t, "",
				"heapSet(%v, %v, %v): not same elements after the call:\n%v",
				td.heap, td.randomIndex, td.randomValue, actual,
			)
		}
	}
}
