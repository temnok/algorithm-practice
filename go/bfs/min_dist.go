package bfs

// MinDist should return the minimal distance between start and end nodes in an arbitrary graph
// represented as an adjacency list, or -1 if there is no path from start to end nodes
func MinDist(graph [][]int, start, end int) int {
	dist := make([]int, len(graph))
	for i := range dist {
		dist[i] = -1
	}

	dist[start] = 0

	for q := []int{start}; len(q) > 0 && dist[end] < 0; q = q[1:] {
		u := q[0]
		for _, v := range graph[u] {
			if dist[v] < 0 {
				q = append(q, v)
				dist[v] = dist[u] + 1
			}
		}
	}

	return dist[end]
}
