package bfs

// minDist should return the minimal distance between start and end nodes in an arbitrary graph
// represented as an adjacency list, or -1 if there is no path from start to end nodes
func minDist(graph [][]int, start, end int) int {
	n := len(graph)
	dist := make([]int, n)
	for i := range dist {
		dist[i] = -1
	}

	dist[start] = 0

	for q := []int{start}; len(q) > 0; {
		p := q[0]
		q = q[1:]

		if p == end {
			break
		}

		for _, c := range graph[p] {
			if dist[c] < 0 {
				q = append(q, c)
				dist[c] = dist[p] + 1
			}
		}
	}

	return dist[end]
}
