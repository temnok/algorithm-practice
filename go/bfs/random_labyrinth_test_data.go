package bfs

import "math/rand/v2"

type randomLabyrinthTestData struct {
	labyrinth [][]bool
	minDist   int
}

var labyrinthRandom = rand.New(rand.NewPCG(0, 0))

func newRandomLabyrinthTestData() *randomLabyrinthTestData {
	m, n := 1+labyrinthRandom.IntN(50), 1+labyrinthRandom.IntN(50)

	td := &randomLabyrinthTestData{
		labyrinth: make([][]bool, m),
	}

	for i := range m {
		td.labyrinth[i] = make([]bool, n)
		for j := range n {
			td.labyrinth[i][j] = labyrinthRandom.IntN(2) == 1
		}
	}
	td.labyrinth[0][0] = false
	td.labyrinth[m-1][n-1] = false

	td.findMinDist()

	return td
}

func (td *randomLabyrinthTestData) findMinDist() {
	var queue []int

	m, n := len(td.labyrinth), len(td.labyrinth[0])
	dist := make([]int, m*n)
	for i := range dist {
		dist[i] = -1
	}

	queue = append(queue, 0)
	dist[0] = 0

	ds := [][]int{{-1, 0}, {0, -1}, {0, 1}, {1, 0}}

	for len(queue) > 0 {
		var u = queue[0]
		queue = queue[1:]
		if u == n*m-1 {
			break
		}
		i0, j0 := u/n, u%n

		for _, d := range ds {
			i, j := i0+d[0], j0+d[1]

			if i >= 0 && i < m && j >= 0 && j < n && td.labyrinth[i][j] {
				v := i*n + j
				if dist[v] < 0 {
					queue = append(queue, v)
					dist[v] = dist[u] + 1
				}
			}
		}
	}

	td.minDist = dist[m*n-1]
}
