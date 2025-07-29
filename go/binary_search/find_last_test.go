package binary_search

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestFindLast_RandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		expected := td.expectedLast
		actual := FindLastInt(td.array, td.val)
		if actual != expected {
			assert.FailNowf(t, "",
				"FindLastInt(%v, %v):\n  Actual: %v\nExpected: %v",
				td.array, td.val, actual, expected,
			)
		}
	}
}
