package sliding_window

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	assert.Equal(t, []int{-1, 0, 0, -10}, SlidingWindowMin([]int{-1, 2, 1, 0, 5, -10}, 3))
}

func TestRandomCases(t *testing.T) {
	var random = rand.New(rand.NewPCG(0, 0))

	for range 100_000 {
		arr := make([]int, 1+random.IntN(100))
		n := 1 + random.IntN(len(arr))

		for i := range arr {
			arr[i] = random.Int()
		}

		got := SlidingWindowMin(arr, n)
		want := make([]int, len(arr)-n+1)

		for i := range want {
			val := arr[i]
			for j := range n - 1 {
				val = min(val, arr[i+1+j])
			}
			want[i] = val
		}

		if !reflect.DeepEqual(got, want) {
			t.Fatalf("SlidingWindowMin(%q, %q):\nwant %v\n got %v", arr, n, want, got)
		}
	}
}
