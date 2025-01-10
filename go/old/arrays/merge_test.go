package arrays

import (
	"fmt"
	"math/rand"
	"sort"
	"testing"
)

const caseCount = 1_000

var random = rand.New(rand.NewSource(0))

func TestMerge(t *testing.T) {
	for c := 0; c < caseCount; c++ {
		arr := generateRandomArray()
		m := len(arr) / 2
		sort.Ints(arr[:m])
		sort.Ints(arr[m:])
		actual, expected := append([]int{}, arr...), append([]int{}, arr...)
		Merge(actual, m)
		MergeSolution(expected, m)
		if a, e := fmt.Sprintf("%#v", actual), fmt.Sprintf("%#v", expected); a != e {
			t.Fatalf("Merge(%#v):\n  Actual: %v\nExpected: %v", arr, a, e)
		}
	}
}

func generateRandomArray() []int {
	n, max := random.Intn(50), random.Intn(50)
	arr := make([]int, n)
	for i := range arr {
		arr[i] = random.Intn(max + 1)
	}
	return arr
}
