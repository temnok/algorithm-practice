package bfs

// MinPath should return list of nodes of the minimal path from start to end nodes in an arbitrary graph
// represented as an adjacency list, or empty list if there is no such path
func MinPath(graph [][]int, start, end int) []int {
	prev := make([]int, len(graph))
	for i := range prev {
		prev[i] = -1
	}

	prev[start] = start

	for q := []int{start}; len(q) > 0 && prev[end] < 0; q = q[1:] {
		u := q[0]
		for _, v := range graph[u] {
			if prev[v] < 0 {
				q = append(q, v)
				prev[v] = u
			}
		}
	}

	if prev[end] < 0 {
		return nil
	}

	var path []int
	for v := end; v != start; v = prev[v] {
		path = append(path, v)
	}
	path = append(path, start)

	for l, r := 0, len(path)-1; l < r; l, r = l+1, r-1 {
		path[l], path[r] = path[r], path[l]
	}

	return path
}
