package topo_sort

// topoSort should perform topological sorting of a directed graph represented by its adjacency list.
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

func topoSort(adjacentyList [][]int) []int {
	return nil // TODO
}
