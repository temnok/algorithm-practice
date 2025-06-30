package merge_sort

import (
	"fmt"
	"sort"
)

type randomStringTestData struct {
	array, expectedArray []string
}

func newRandomStringTestData() *randomStringTestData {
	var td randomStringTestData

	n, maxVal := random.IntN(50), random.IntN(1_000_000_000)
	td.array = make([]string, n)
	for i := range n {
		td.array[i] = fmt.Sprint(random.IntN(1 + maxVal))
	}
	td.expectedArray = append([]string{}, td.array...)
	sort.Slice(td.expectedArray, func(i, j int) bool {
		return len(td.expectedArray[i]) < len(td.expectedArray[j])
	})

	return &td
}
