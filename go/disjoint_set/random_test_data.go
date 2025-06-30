package disjoint_set

import (
	"math/rand/v2"
)

type randomTestData struct {
	ops            int
	indexes        []int
	expectedUnions []bool
	expectedFinds  []int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData(n int) *randomTestData {
	var td randomTestData

	find := func(par []int, i int) int {
		for i != par[i] {
			j := i
			i = par[i]
			par[j] = par[i]
		}
		return i
	}

	par, size := make([]int, n), make([]int, n)
	for i := range n {
		par[i] = i
		size[i] = 1
	}

	td.ops = n + random.IntN(n*2)
	td.indexes = make([]int, td.ops*3)
	td.expectedUnions = make([]bool, td.ops)
	td.expectedFinds = make([]int, td.ops)

	for op := range td.ops {
		i, j, k := random.IntN(n), random.IntN(n), random.IntN(n)
		td.indexes[op*3] = i
		td.indexes[op*3+1] = j
		td.indexes[op*3+2] = k

		i, j = find(par, i), find(par, j)
		if i != j {
			td.expectedUnions[op] = true

			if size[i] > size[j] {
				par[j] = i
				size[i] += size[j]
			} else {
				par[i] = j
				size[j] += size[i]
			}
		}
		td.expectedFinds[op] = find(par, k)
	}

	return &td
}
