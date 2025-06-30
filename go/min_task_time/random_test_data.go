package min_task_time

import (
	"math/rand/v2"
)

type randomTestData struct {
	n, expected int
	time        []int
	before      [][]int
}

var random = rand.New(rand.NewPCG(0, 0))

func newRandomTestData() *randomTestData {
	var td randomTestData

	for !td.randomTestDataSuccess() {
	}

	return &td
}

func (td *randomTestData) randomTestDataSuccess() bool {
	td.n = 1 + random.IntN(100)
	td.time = make([]int, td.n)
	for u := range td.n {
		td.time[u] = 1 + random.IntN(100)
	}

	var edges [][]int
	mat := make([][]int, td.n+1)
	for i := range mat {
		mat[i] = make([]int, td.n+1)
	}

	for i, m := 0, random.IntN(td.n*2); i < m; i++ {
		u, v := random.IntN(td.n), random.IntN(td.n)
		if mat[u][v] > 0 {
			continue
		}

		edges = append(edges, []int{u, v})
		mat[u][v] = td.time[u]
	}

	for u := range td.n {
		edges = append(edges, []int{u, td.n})
		mat[u][td.n] = td.time[u]
	}

	for m := 0; m <= td.n; m++ {
		for u := 0; u <= td.n; u++ {
			if mat[u][m] == 0 {
				continue
			}

			for v := 0; v <= td.n; v++ {
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

	td.expected = 0
	for u := 0; u <= td.n; u++ {
		if mat[u][u] > 0 {
			return false
		}

		for v := 0; v <= td.n; v++ {
			if mat[u][v] > td.expected {
				td.expected = mat[u][v]
			}
		}
	}

	td.before = edges[:len(edges)-td.n]

	return true

}
