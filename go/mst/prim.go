package mst

// PrimMinimumSpanningForest should return subset of edges that form
// "Minimum Spanning Forest" of the input *undirected* graph represented
// by n nodes and provided list of edges. Each edge is represented by three
// integers (u, v, w), where u and v are node indexes, and w is edge weight.
//
// Restrictions:
// * Prim's algorithm should be used for implementation
// * input graph may be unconnected
// * edge (u, v, w) is equivalent to edge (v, u, w)
// * n > 0, 0 <= u < n, 0 <= v < n, w > 0
// * there can be multiple edges between same pair of nodes with same or different weights
// * order of returned edges is ignored
// * no duplicates are allowed in the returned list
func PrimMinimumSpanningForest(n int, edges [][]int) [][]int {
	//panic("TODO")

	graph := make([][][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		graph[u] = append(graph[u], e)
		graph[v] = append(graph[v], e)
	}

	visited := make([]bool, n)
	var heap, ans [][]int

	for u := range n {
		if visited[u] {
			continue
		}

		visited[u] = true
		for _, e := range graph[u] {
			heapAdd(&heap, e)
		}

		for len(heap) > 0 {
			e := heapRemove(&heap)
			for _, v := range e[:2] {
				if visited[v] {
					continue
				}

				visited[v] = true
				ans = append(ans, e)

				for _, ve := range graph[v] {
					heapAdd(&heap, ve)
				}
			}
		}
	}

	return ans
}

func heapAdd(heap *[][]int, edge []int) {
	*heap = append(*heap, edge)
	heapUp(*heap, len(*heap)-1)
}

func heapRemove(heap *[][]int) []int {
	n := len(*heap)
	f, l := (*heap)[0], (*heap)[n-1]
	(*heap)[n-1] = nil
	*heap = (*heap)[:n-1]

	if len(*heap) > 0 {
		(*heap)[0] = l
		heapDown(*heap, 0)
	}

	return f
}

func heapUp(heap [][]int, i int) {
	e := heap[i]

	for j := (i - 1) / 2; i > 0 && heap[j][2] > e[2]; i, j = j, (j-1)/2 {
		heap[i] = heap[j]
	}

	heap[i] = e
}

func heapDown(heap [][]int, i int) {
	e := heap[i]

	for j := i*2 + 1; j < len(heap); i, j = j, j*2+1 {
		if j+1 < len(heap) && heap[j+1][2] < heap[j][2] {
			j++
		}

		if e[2] <= heap[j][2] {
			break
		}

		heap[i] = heap[j]
	}

	heap[i] = e
}
