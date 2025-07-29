package max_path

// MaxPathLength should return max path length within the graph with n nodes and provided edges,
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

func MaxPathLength(n int, edges [][]int) int {
	adj := make([][][2]int, n)
	ins := make([]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		adj[u] = append(adj[u], [2]int{v, w})
		ins[v]++
	}

	order := make([]int, 0, n)
	for v, c := range ins {
		if c == 0 {
			order = append(order, v)
		}
	}

	for i := 0; i < len(order); i++ {
		u := order[i]
		for _, vw := range adj[u] {
			v := vw[0]
			if ins[v]--; ins[v] == 0 {
				order = append(order, v)
			}
		}
	}

	if len(order) < n {
		return -1
	}

	dist := make([]int, n)
	maxDist := 0
	for _, u := range order {
		for _, vw := range adj[u] {
			v, w := vw[0], vw[1]
			if d := dist[u] + w; d > dist[v] {
				dist[v] = d
				maxDist = max(maxDist, d)
			}
		}
	}

	return maxDist
}
