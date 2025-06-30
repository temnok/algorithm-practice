package bfs

// dfs should visit graph nodes starting from node start
// using provided adjacency list and add the nodes in Breadth-First order to the output list.
// For example, for the given adjacency list
//
// { 0:{1, 3}, 1:{2,4}, 2:{5}, 3:{4,6}, 4:{5,7}, 5:{8}, 6:{7}, 7:{8}, 8:{2} }
//
// 0->1->2<-
// v  v  v |
// 3->4->5 |
// v  v  v |
// 6->7->8-
//
// and start node 0, the result should be { 0, 1, 3, 2, 4, 6, 5, 7, 8 }
func bfs(graph [][]int, start int) []int {
	n := len(graph)
	visited := make([]bool, n)
	visited[start] = true

	var ans []int

	for q := []int{start}; len(q) > 0; {
		p := q[0]
		q = q[1:]

		ans = append(ans, p)

		for _, c := range graph[p] {
			if !visited[c] {
				q = append(q, c)
				visited[c] = true
			}
		}
	}

	return ans
}
