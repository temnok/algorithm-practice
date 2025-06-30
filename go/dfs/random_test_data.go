package dfs

import (
	"math/rand/v2"
)

type randomTestData struct {
	graph          [][]int
	start          int
	order1, order2 []int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	var td randomTestData

	n, maxAdj := 1+random.IntN(50), random.IntN(50)
	for range n {
		var adj []int

		m := random.IntN(1 + maxAdj)
		for range m {
			v := random.IntN(n)
			adj = append(adj, v)
		}

		td.graph = append(td.graph, adj)
	}

	td.start = random.IntN(n)
	td.walk1()
	td.walk2()

	return &td
}

func (td *randomTestData) walk1() {
	var stack []int
	visited := make([]bool, len(td.graph))

	stack = append(stack, td.start)
	visited[td.start] = true

	for len(stack) > 0 {
		u := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		td.order1 = append(td.order1, u)

		for _, v := range td.graph[u] {
			if !visited[v] {
				stack = append(stack, v)
				visited[v] = true
			}
		}
	}
}

func (td *randomTestData) walk2() {
	var stack []int
	visited := make([]bool, len(td.graph))

	stack = append(stack, td.start)
	visited[td.start] = true

	for len(stack) > 0 {
		u := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		td.order2 = append(td.order2, u)

		row := td.graph[u]
		for i := len(row) - 1; i >= 0; i-- {
			v := row[i]
			if !visited[v] {
				stack = append(stack, v)
				visited[v] = true
			}
		}
	}
}
