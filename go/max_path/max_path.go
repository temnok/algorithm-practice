package max_path

// maxPathLength should return max path length within the graph with n nodes and provided edges,
// or -1 if the graph contains a cycle and thus the max path length is infinity.
// edges contain triples [u, v, d], each describing *directed* edge from node u to v with distance d (d > 0)
// between nodes u and v.
//
// There can be multiple edges between same one or two nodes with different distances.
// For example, if n = 5, edges = [[3, 1, 100], [1, 0, 20], [3, 2, 30], [2, 1, 10], [3, 4, 110]] then
// result should be 120.
//
//               100
//          -------------
//         |             |
//     20  v  10     30  |  110
//  *<-----*<-----*<-----*------>*
//  0      1      2      3       4

func maxPathLength(n int, edges [][]int) int {
	panic("TODO")
}
