package dijkstra

import (
	"math/rand/v2"
)

type randomTestData struct {
	n, start int
	edges    [][]int
	expected []int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	var td randomTestData

	td.n = 1 + random.IntN(100)
	td.start = random.IntN(td.n)
	td.edges = make([][]int, random.IntN(td.n*2))
	for i := range td.edges {
		td.edges[i] = []int{random.IntN(td.n), random.IntN(td.n), 1 + random.IntN(100)}
	}

	td.expected = make([]int, td.n)
	for i := range td.expected {
		td.expected[i] = -1
	}
	td.expected[td.start] = 0

	for count := td.n - 1; count > 0; count-- {
		for _, edge := range td.edges {
			u, v, dist := edge[0], edge[1], edge[2]
			if td.expected[u] >= 0 && (td.expected[v] < 0 || td.expected[u]+dist < td.expected[v]) {
				td.expected[v] = td.expected[u] + dist
			}
		}
	}

	return &td
}
