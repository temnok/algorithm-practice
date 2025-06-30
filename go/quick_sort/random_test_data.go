package quick_sort

import (
	"math/rand/v2"
	"sort"
)

type randomTestData struct {
	array, expectedArray []int
	randomIndex          int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	return newRandomTestDataN(50)
}

func newRandomTestDataN(maxN int) *randomTestData {
	var td randomTestData

	n, max := 1+random.IntN(maxN), random.IntN(maxN+1)
	td.array = make([]int, n)
	for i := range n {
		td.array[i] = random.IntN(1 + max)
	}
	td.expectedArray = append([]int{}, td.array...)
	sort.Ints(td.expectedArray)

	td.randomIndex = random.IntN(n)

	return &td
}
