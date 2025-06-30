package bfs

// minDistLabyrinth should return minimal length of a path from
// top-left corner to bottom-right corner of the MxN labyrinth (M > 0, N > 0)
// or -1 if no such path exists. Path should consists of empty cells (with "false" value) only.
// Both start and end cells will be empty. Only horizontal and vertical moves are allowed.
// Diagonal moves are NOT allowed.
func minDistLabyrinth(labyrinth [][]bool) int {
	m, n := len(labyrinth), len(labyrinth[0])
	dist := make([]int, m*n)
	for i := range dist {
		dist[i] = -1
	}

	dist[0] = 0

	ds := [][]int{{-1, 0}, {0, -1}, {0, 1}, {1, 0}}
	for q := []int{0}; len(q) > 0; {
		p := q[0]
		q = q[1:]

		if p == m*n-1 {
			return dist[p]
		}

		i0, j0 := p/n, p%n
		for _, d := range ds {
			i, j := i0+d[0], j0+d[1]
			if c := i*n + j; i >= 0 && i < m && j >= 0 && j < n && !labyrinth[i][j] && dist[c] < 0 {
				q = append(q, c)
				dist[c] = dist[p] + 1
			}
		}
	}

	return -1
}
