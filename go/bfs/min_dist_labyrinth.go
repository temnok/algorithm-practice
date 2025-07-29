package bfs

// MinDistLabyrinth should return minimal length of a path from
// top-left corner to bottom-right corner of the MxN labyrinth (M > 0, N > 0)
// or -1 if no such path exists. Path should consists of empty cells (with "false" value) only.
// Both start and end cells will be empty. Only horizontal and vertical moves are allowed.
// Diagonal moves are NOT allowed.
func MinDistLabyrinth(lab [][]bool) int {
	m, n := len(lab), len(lab[0])
	dist := make([]int, m*n)
	for i := range dist {
		dist[i] = -1
	}

	dist[0] = 0
	ds := [][]int{{-1, 0}, {0, -1}, {0, 1}, {1, 0}}

	for q := []int{0}; len(q) > 0 && dist[m*n-1] < 0; q = q[1:] {
		u := q[0]
		ui, uj := u/n, u%n

		for _, d := range ds {
			i, j := ui+d[0], uj+d[1]
			v := i*n + j
			if i >= 0 && i < m && j >= 0 && j < n && !lab[i][j] && dist[v] < 0 {
				q = append(q, v)
				dist[v] = dist[u] + 1
			}
		}
	}

	return dist[m*n-1]
}
