package topo_sort

// TopoSort should perform topological sorting of a directed graph represented by its adjacency list.
// The provided graph can contain duplicate links or links from a node to itself.
// If graph is not a DAG (i.e. contains cycles) then the empty list should be returned.
// Otherwise, returned array should contain node numbers as in the following example:
//
// Input:
//
// Adjacency list: [[2, 1], [], [1], [0, 2]]
//
//         0
//    -----*<----
//   |     |    |
//   v     v    |
//   *<---*<----*
//   1    2    3
//
// Expected output: [3, 0, 2, 1]

func TopoSort(graph [][]int) []int {
	n := len(graph)
	ins := make([]int, n)
	for _, vs := range graph {
		for _, v := range vs {
			ins[v]++
		}
	}

	order := make([]int, 0, n)
	for v, c := range ins {
		if c == 0 {
			order = append(order, v)
		}
	}

	for i := 0; i < len(order); i++ {
		u := order[i]
		for _, v := range graph[u] {
			if ins[v]--; ins[v] == 0 {
				order = append(order, v)
			}
		}
	}

	if len(order) < n {
		return nil
	}

	return order
}
