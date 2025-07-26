package trie

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestRandomCases(t *testing.T) {
	for range 10_000 {
		td := newRandomTestData()

		actual := HasPrefix(td.strings)
		if actual != td.hasPrefix {
			assert.FailNowf(t, "", "HasPrefix(%v):\nwant %v\n got %v",
				td.strings, td.hasPrefix, actual)
		}
	}
}
