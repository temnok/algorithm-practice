package mst

import (
	"fmt"
	"math/rand/v2"
	"sort"
	"testing"
)

type randomTestData struct {
	n                 int
	edges             [][]int
	expectedWeightSum int
	par, size         []int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	this := &randomTestData{}

	this.n = 1 + random.IntN(100)
	this.edges = make([][]int, random.IntN(100))
	maxWeight := 1 + random.IntN(100)
	for i := range this.edges {
		u := random.IntN(this.n)
		v := random.IntN(this.n)
		w := 1 + random.IntN(maxWeight)
		this.edges[i] = []int{u, v, w}
	}

	orderedEdges := append([][]int{}, this.edges...)
	sort.Slice(orderedEdges, func(i, j int) bool {
		return orderedEdges[i][2] < orderedEdges[j][2]
	})

	this.par = make([]int, this.n)
	this.size = make([]int, this.n)
	for i := range this.n {
		this.par[i] = i
		this.size[i] = 1
	}

	for _, e := range orderedEdges {
		u, v, w := e[0], e[1], e[2]
		if this.union(u, v) {
			this.expectedWeightSum += w
		}
	}

	return this
}

func (td *randomTestData) assertAnswer(t *testing.T, funcName string, answer [][]int) {
	sum := 0
	edgeSet := map[[3]int]bool{}
	for _, e := range td.edges {
		u, v, w := e[0], e[1], e[2]
		edgeSet[[...]int{u, v, w}] = true
	}

	for _, e := range answer {
		u, v, w := e[0], e[1], e[2]
		if key := [...]int{u, v, w}; !edgeSet[key] {
			t.Fatalf(fmt.Sprintf("%v(%v, %v):\n"+
				"unexpected edge in response: %v\n"+
				"full response: %v",
				funcName, td.n, td.edges, e, answer),
			)
		}

		if td.find(td.par[u]) != td.find(td.par[v]) {
			t.Fatalf(fmt.Sprintf("%v(%v, %v):\n"+
				"want nodes %v and %v to remain connected\n"+
				" got %v",
				funcName, td.n, td.edges, u, v, answer),
			)
		}
		sum += w
	}

	if sum != td.expectedWeightSum {
		t.Fatalf(fmt.Sprintf("%v(%v, %v):\n"+
			"want weight sum %v\n"+
			" got weight sum %v",
			funcName, td.n, td.edges, td.expectedWeightSum, sum),
		)
	}

	for _, e := range answer {
		u, v := e[0], e[1]
		td.par[u] = -1
		td.par[v] = -1
	}

	for i := range td.n {
		if td.par[i] >= 0 && td.size[i] > 1 {
			t.Fatalf(fmt.Sprintf("%v(%v, %v):\n"+
				"want node %v to remain connected to other node(s)\n"+
				" got %v",
				funcName, td.n, td.edges, i, answer),
			)
		}
	}
}

func (td *randomTestData) find(i int) int {
	for i != td.par[i] {
		var p = td.par[i]
		td.par[i] = td.par[p]
		i = p
	}

	return i
}

func (td *randomTestData) union(i, j int) bool {
	i, j = td.find(i), td.find(j)
	if i == j {
		return false
	}

	if td.size[i] < td.size[j] {
		td.par[i] = j
		td.size[j] += td.size[i]
	} else {
		td.par[j] = i
		td.size[i] += td.size[j]
	}

	return true
}
