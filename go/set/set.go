package set

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"sort"
	"testing"
)

type Set interface {
	Add(val int) bool
	Contains(val int) bool
	Remove(val int) bool
}

func GenericRandomCases(t *testing.T, setTypeName string, newFunc func() Set) {
	var random = rand.New(rand.NewPCG(0, 0))

	for range 10_000 {
		set := newFunc()

		maxVal := random.IntN(100)
		addProb := random.Float64()
		findProb := random.Float64()

		expectedSet := map[int]bool{}

		for range 1_000 {
			val := random.IntN(maxVal + 1)

			if p := random.Float64(); p < addProb {
				expected := !expectedSet[val]
				actual := set.Add(val)
				if actual != expected {
					assert.FailNowf(t, "",
						"%v(%v)\n.Add(%v):\nwant %v\n got %v",
						setTypeName, setToArr(expectedSet), val, expected, actual,
					)
				}
				expectedSet[val] = true
			} else if p < findProb {
				expected := expectedSet[val]
				actual := set.Contains(val)
				if actual != expected {
					assert.FailNowf(t, "",
						"%v(%v)\n.Contains(%v):\nwant %v\n got %v",
						setTypeName, setToArr(expectedSet), val, expected, actual,
					)
				}
			} else {
				expected := expectedSet[val]
				actual := set.Remove(val)
				if actual != expected {
					assert.FailNowf(t, "",
						"%v(%v)\n.Remove(%v):\nwant %v\n got %v",
						setTypeName, setToArr(expectedSet), val, expected, actual,
					)
				}
				delete(expectedSet, val)
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
