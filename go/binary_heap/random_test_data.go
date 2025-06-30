package binary_heap

import (
	"math/rand/v2"
	"reflect"
	"sort"
)

type randomTestData struct {
	array, heap              []int
	randomIndex, randomValue int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	n, maxVal := 1+random.IntN(5), random.IntN(5)
	td := &randomTestData{
		array: make([]int, n),
	}
	for i := range n {
		td.array[i] = random.IntN(1 + maxVal)
	}
	td.heap = append([]int{}, td.array...)
	for i0 := len(td.heap) / 2; i0 >= 0; i0-- {
		i := i0
		val := td.heap[i]
		for j := i*2 + 1; j < len(td.heap); i, j = j, j*2+1 {
			if j+1 < len(td.heap) && td.heap[j+1] < td.heap[j] {
				j++
			}
			if val <= td.heap[j] {
				break
			}
			td.heap[i] = td.heap[j]
		}
		td.heap[i] = val
	}

	td.randomIndex = random.IntN(n)
	td.randomValue = random.IntN(1 + maxVal)

	return td
}

func minHeapIsOK(heap []int) bool {
	for i := 1; i < len(heap); i++ {
		if heap[(i-1)/2] > heap[i] {
			return false
		}
	}

	return true
}

func haveSameElements(arr1, arr2 []int) bool {
	sorted1 := append([]int{}, arr1...)
	sort.Ints(sorted1)

	sorted2 := append([]int{}, arr2...)
	sort.Ints(sorted2)

	return reflect.DeepEqual(sorted1, sorted2)
}
