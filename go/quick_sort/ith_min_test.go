package quick_sort

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestIthMin_RandomCases(t *testing.T) {
	testIthMin_RandomCases(t, 1000, 100)
}

func TestIthMin_RandomCases_LargeArrays(t *testing.T) {
	testIthMin_RandomCases(t, 100, 100_000)
}

func testIthMin_RandomCases(t *testing.T, testCount, maxN int) {
	for range testCount {
		td := newRandomTestDataN(maxN)

		expected := td.expectedArray[td.randomIndex]
		actual := ithMin(td.array, td.randomIndex)

		if actual != expected {
			assert.FailNowf(t, "",
				"ithMin(%v, %v):\n  Actual: %v\nExpected: %v",
				td.array, td.randomIndex, actual, expected,
			)
		}
	}
}
