package trie

import (
	"math/rand/v2"
	"strings"
)

type randomTestData struct {
	strings   []string
	hasPrefix bool
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	td := &randomTestData{}

	n := 1 + random.IntN(26)
	m := 1 + random.IntN(20)

	td.strings = make([]string, 1+random.IntN(20))
	for i := range td.strings {
		buf := make([]byte, 1+random.IntN(m))
		for j := range buf {
			buf[j] = byte('a' + random.IntN(n))
		}
		td.strings[i] = string(buf)
	}

	for i := range td.strings {
		for j := range td.strings {
			if i != j && strings.HasPrefix(td.strings[i], td.strings[j]) {
				td.hasPrefix = true
				break
			}
		}
	}

	return td
}
