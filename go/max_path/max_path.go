package max_path

// maxPathLength should return max path length within the graph with n nodes and provided edges,
// or -1 if the graph contains a cycle and thus the max path length is infinity.
// edges contain triples [u, v, d], each describing *directed* edge from node u to v with distance d (d > 0)
// between nodes u and v.
//
// There can be multiple edges between same one or two nodes with different distances.
// For example, if n = 5, edges = [[3, 1, 100], [1, 0, 20], [3, 2, 30], [2, 1, 10], [3, 4, 110]] then
// result should be 120.
//
//               100
//          -------------
//         |             |
//     20  v  10     30  |  110
//  *<-----*<-----*<-----*------>*
//  0      1      2      3       4

func maxPathLength(n int, edges [][]int) int {
	graph := make([][]Edge, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		graph[u] = append(graph[u], Edge{v: v, w: w})
	}

	order := topoSort(graph)

	if len(order) < n {
		return -1
	}

	dist := make([]int, n)
	maxDist := 0
	for _, u := range order {
		for _, e := range graph[u] {
			dist[e.v] = max(dist[e.v], dist[u]+e.w)
			maxDist = max(maxDist, dist[e.v])
		}
	}

	return maxDist
}

type Edge struct {
	v, w int
}

func topoSort(graph [][]Edge) []int {
	n := len(graph)
	inCounts := make([]int, n)

	for _, vs := range graph {
		for _, v := range vs {
			inCounts[v.v]++
		}
	}

	var order []int
	for v, inCount := range inCounts {
		if inCount == 0 {
			order = append(order, v)
		}
	}

	for i := 0; i < len(order); i++ {
		u := order[i]
		for _, v := range graph[u] {
			if inCounts[v.v]--; inCounts[v.v] == 0 {
				order = append(order, v.v)
			}
		}
	}

	return order
}
