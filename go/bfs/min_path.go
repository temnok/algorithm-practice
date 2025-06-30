package bfs

// minPath should return list of nodes of the minimal path from start to end nodes in an arbitrary graph
// represented as an adjacency list, or empty list if there is no such path
func minPath(graph [][]int, start, end int) []int {
	n := len(graph)
	prev := make([]int, n)
	for i := range prev {
		prev[i] = -1
	}

	prev[start] = start

	for q := []int{start}; len(q) > 0; {
		p := q[0]
		q = q[1:]

		for _, c := range graph[p] {
			if prev[c] < 0 {
				q = append(q, c)
				prev[c] = p
			}
		}
	}

	if prev[end] < 0 {
		return nil
	}

	var path []int
	for c := end; ; c = prev[c] {
		path = append(path, c)
		if c == start {
			break
		}
	}

	for l, r := 0, len(path)-1; l < r; l, r = l+1, r-1 {
		path[l], path[r] = path[r], path[l]
	}

	return path
}
