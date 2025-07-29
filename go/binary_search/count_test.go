package binary_search

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestCount_RandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.expectedCount
		actual := CountInts(td.array, td.val)
		if actual != expected {
			assert.FailNowf(t, "",
				"CountInts(%v, %v):\n  Actual: %v\nExpected: %v",
				td.array, td.val, actual, expected,
			)
		}
	}
}
