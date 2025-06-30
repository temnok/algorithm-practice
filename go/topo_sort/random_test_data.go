package topo_sort

import (
	"math/rand/v2"
)

type randomTestData struct {
	graph, adj [][]int
	hasCycle   bool
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	var td randomTestData

	n := 1 + random.IntN(100)
	td.graph = make([][]int, n)

	m := 1 + random.IntN(n*2)
	ins := make([]int, n)
	for range m {
		u := random.IntN(n)
		v := random.IntN(n)
		td.graph[u] = append(td.graph[u], v)
		ins[v]++
	}

	td.adj = make([][]int, n)
	for u := range n {
		td.adj[u] = make([]int, len(td.graph[u]))
		for i := 0; i < len(td.adj[u]); i++ {
			td.adj[u][i] = td.graph[u][i]
		}
	}

	q := make([]int, n)
	r := 0
	for v := range n {
		if ins[v] == 0 {
			q[r] = v
			r++
		}
	}

	for l := 0; l < r; l++ {
		for _, v := range td.adj[q[l]] {
			if ins[v]--; ins[v] == 0 {
				q[r] = v
				r++
			}
		}
	}

	td.hasCycle = r < n

	return &td
}
