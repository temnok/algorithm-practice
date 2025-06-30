package binary_search

import (
	"math/rand/v2"
	"sort"
)

type randomTestData struct {
	array                                           []int
	val, expectedFirst, expectedLast, expectedCount int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	var td randomTestData

	n, maxVal := random.IntN(50), random.IntN(50)
	td.array = make([]int, n)
	for i := range n {
		td.array[i] = random.IntN(1 + maxVal)
	}

	td.val = random.IntN(1 + maxVal)
	td.expectedFirst = -1
	td.expectedLast = -1

	sort.Ints(td.array)
	for i := range n {
		if td.array[i] == td.val {
			if td.expectedFirst < 0 {
				td.expectedFirst = i
			}
			td.expectedLast = i
			td.expectedCount++
		}
	}

	return &td
}
