package binary_search

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestCount_RandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.expectedCount
		actual := countInt(td.array, td.val)
		if actual != expected {
			assert.FailNowf(t, "",
				"countInt(%v, %v):\n  Actual: %v\nExpected: %v",
				td.array, td.val, actual, expected,
			)
		}
	}
}
