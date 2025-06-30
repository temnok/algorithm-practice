package merge_sort

import (
	"math/rand/v2"
	"sort"
)

type randomIntTestData struct {
	array, expectedArray []int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomIntTestData() *randomIntTestData {
	var td randomIntTestData

	n, maxVal := random.IntN(50), random.IntN(50)
	td.array = make([]int, n)
	for i := range n {
		td.array[i] = random.IntN(1 + maxVal)
	}
	td.expectedArray = append([]int{}, td.array...)
	sort.Ints(td.expectedArray)

	return &td
}
