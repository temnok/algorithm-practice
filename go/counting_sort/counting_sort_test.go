package counting_sort

import (
	"math/rand/v2"
	"reflect"
	"sort"
	"testing"
)

func TestRandomCases(t *testing.T) {
	var random = rand.New(rand.NewPCG(0, 0))

	for range 10_000 {
		mi := random.IntN(2_000_000_000) - 1_000_000_000
		ma := mi + random.IntN(1000)

		arr := make([]int, 1+random.IntN(1000))
		for i := range arr {
			arr[i] = mi + random.IntN(ma-mi+1)
		}

		want := append([]int{}, arr...)
		sort.Ints(want)

		got := append([]int{}, arr...)
		CountingSort(got)

		if !reflect.DeepEqual(want, got) {
			t.Fatalf("CountingSort(%v):\nwant %v\n got %v", arr, want, got)
		}
	}
}
