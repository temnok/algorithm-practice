package mst

import "sort"

// KruskalMinimumSpanningForest should return subset of edges that form
// "Minimum Spanning Forest" of the input *undirected* graph represented
// by n nodes and provided list of edges. Each edge is represented by three
// integers (u, v, w), where u and v are node indexes, and w is edge weight.
//
// Restrictions:
// * Kruskal's algorithm should be used for implementation
// * input graph may be unconnected
// * edge (u, v, w) is equivalent to edge (v, u, w)
// * n > 0, 0 <= u < n, 0 <= v < n, w > 0
// * there can be multiple edges between same pair of nodes with same or different weights
// * order of returned edges is ignored
// * no duplicates are allowed in the returned list
func KruskalMinimumSpanningForest(n int, edges [][]int) [][]int {
	//panic("TODO")

	sort.Slice(edges, func(i, j int) bool {
		return edges[i][2] < edges[j][2]
	})

	par, size := make([]int, n), make([]int, n)
	for i := range n {
		par[i], size[i] = i, 1
	}

	ans := [][]int{}

	for _, e := range edges {
		u, v := e[0], e[1]
		if union(par, size, u, v) {
			ans = append(ans, e)
		}
	}

	return ans
}

func union(par, size []int, i, j int) bool {
	i, j = find(par, i), find(par, j)
	if i == j {
		return false
	}

	if size[i] < size[j] {
		par[i] = j
		size[j] += size[i]
	} else {
		par[j] = i
		size[i] += size[j]
	}

	return true
}

func find(par []int, i int) int {
	for par[i] != i {
		p := par[i]
		par[i] = par[p]
		i = p
	}

	return i
}
