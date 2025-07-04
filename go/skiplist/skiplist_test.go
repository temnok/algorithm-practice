package skiplist

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"sort"
	"testing"
)

func TestRandomCases(t *testing.T) {
	var random = rand.New(rand.NewPCG(0, 0))

	for range 10_000 {
		skiplist := NewSkipList()

		maxVal := random.IntN(100)
		addProb := random.Float64()
		findProb := random.Float64()

		set := map[int]bool{}

		for range 1_000 {
			val := random.IntN(maxVal + 1)

			if p := random.Float64(); p < addProb {
				expected := !set[val]
				actual := skiplist.Add(val)
				if actual != expected {
					assert.FailNowf(t, "",
						"SkipList(%v)\n.Add(%v):\nwant %v\n got %v",
						setToArr(set), val, expected, actual,
					)
				}
				set[val] = true
			} else if p < findProb {
				expected := set[val]
				actual := skiplist.Contains(val)
				if actual != expected {
					assert.FailNowf(t, "",
						"SkipList(%v)\n.Contains(%v):\nwant %v\n got %v",
						setToArr(set), val, expected, actual,
					)
				}
			} else {
				expected := set[val]
				actual := skiplist.Remove(val)
				if actual != expected {
					assert.FailNowf(t, "",
						"SkipList(%v)\n.Remove(%v):\nwant %v\n got %v",
						setToArr(set), val, expected, actual,
					)
				}
				delete(set, val)
			}

		}
	}
}

func setToArr(set map[int]bool) []int {
	var arr []int

	for key := range set {
		arr = append(arr, key)
	}

	sort.Ints(arr)

	return arr
}
