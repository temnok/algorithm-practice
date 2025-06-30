package max_path

import (
	"math/rand/v2"
)

type randomTestData struct {
	n, expected int
	edges       [][]int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	var td randomTestData

	td.n = 1 + random.IntN(100)
	td.edges = make([][]int, random.IntN(td.n*2))
	mat := make([][]int, td.n)
	for i := range mat {
		mat[i] = make([]int, td.n)
	}

	for i := range td.edges {
		u, v, w := random.IntN(td.n), random.IntN(td.n), 1+random.IntN(100)
		w = max(w, mat[u][v])
		td.edges[i] = []int{u, v, w}
		mat[u][v] = w
	}

	for m := range td.n {
		for u := range td.n {
			if mat[u][m] == 0 {
				continue
			}

			for v := 0; v < td.n; v++ {
				if mat[m][v] == 0 {
					continue
				}

				var w = mat[u][m] + mat[m][v]
				if -w < -mat[u][v] {
					mat[u][v] = w
				}
			}
		}
	}

	for u := 0; u < td.n; u++ {
		if mat[u][u] > 0 {
			td.expected = -1
			break
		}

		for v := 0; v < td.n; v++ {
			if mat[u][v] > td.expected {
				td.expected = mat[u][v]
			}
		}
	}

	return &td
}
