package bfs

import "math/rand/v2"

type randomTestData struct {
	graph               [][]int
	start, end, minDist int
	minPath, order      []int
}

func newRandomTestData() *randomTestData {
	rand := rand.New(rand.NewPCG(0, 0))
	n := 1 + rand.IntN(50)
	maxAdj := rand.IntN(50)

	td := &randomTestData{}
	for range n {
		var adj []int
		m := rand.IntN(1 + maxAdj)
		for range m {
			v := rand.IntN(n)
			adj = append(adj, v)
		}

		td.graph = append(td.graph, adj)
	}

	td.start = rand.IntN(n)
	td.end = rand.IntN(n)
	td.walk()
	td.minDist = len(td.minPath) - 1

	return td
}

func (td *randomTestData) walk() {
	prev := make([]int, len(td.graph))
	for i := range prev {
		prev[i] = -1
	}

	td.order = append(td.order, td.start)
	prev[td.start] = td.start

	for i := 0; i < len(td.order); i++ {
		u := td.order[i]
		for _, v := range td.graph[u] {
			if prev[v] < 0 {
				td.order = append(td.order, v)
				prev[v] = u
			}
		}
	}

	if prev[td.end] < 0 {
		return
	}

	for v := td.end; ; v = prev[v] {
		td.minPath = append(td.minPath, v)
		if v == prev[v] {
			break
		}
	}

	for l, r := 0, len(td.minPath); l < r; l, r = l+1, r-1 {
		td.minPath[l], td.minPath[r] = td.minPath[r], td.minPath[l]
	}
}
