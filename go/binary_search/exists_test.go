package binary_search

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestExists_RandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.expectedFirst >= 0
		actual := ExistsInt(td.array, td.val)
		if actual != expected {
			assert.FailNowf(t, "",
				"ExistsInt(%v, %v):\n  Actual: %v\nExpected: %v",
				td.array, td.val, actual, expected,
			)
		}
	}
}
