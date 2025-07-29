package dijkstra

import "math"

// DijkstraMinDist should return min distance to all nodes from start node,
// or -1 if node is unreachable from the start node.
// edges contain triples [u, v, d], describing *directed* edge from node u to v with distance d (d > 0).
// There can be multiple edges between same one or two nodes with different distances.
// For example, if n = 4, edges = [[0, 2, 10], [0, 1, 100], [2, 1, 20]] and start = 0 then
// result should be [0, 30, 10, -1]
func DijkstraMinDist(n int, edges [][]int, start int) []int {
	dist := make([]int, n)
	for i := range n {
		dist[i] = -1
	}
	dist[start] = 0

	heap := make([]*Node, n)
	for i := range n {
		heap[i] = &Node{val: math.MaxInt, i: i, heapI: i}
	}

	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		heap[u].edges = append(heap[u].edges, &Edge{w: w, v: heap[v]})
	}

	heap[start].val = 0
	heapUp(heap, start)

	for heap[0].val < math.MaxInt {
		u := heap[0]
		dist[u.i] = u.val
		u.val = math.MaxInt
		heapDown(heap, 0)

		for _, e := range u.edges {
			if dist[e.v.i] < 0 {
				if d := dist[u.i] + e.w; d < e.v.val {
					e.v.val = d
					heapUp(heap, e.v.heapI)
				}
			}
		}
	}

	return dist
}

type Node struct {
	val      int
	i, heapI int
	edges    []*Edge
}

type Edge struct {
	w int
	v *Node
}

func heapUp(heap []*Node, i int) {
	node := heap[i]

	for j := (i - 1) / 2; i > 0 && node.val < heap[j].val; i, j = j, (j-1)/2 {
		heap[i] = heap[j]
		heap[i].heapI = i
	}

	heap[i] = node
	heap[i].heapI = i
}

func heapDown(heap []*Node, i int) {
	node := heap[i]

	for j := i*2 + 1; j < len(heap); i, j = j, j*2+1 {
		if j+1 < len(heap) && heap[j+1].val < heap[j].val {
			j++
		}

		if node.val <= heap[j].val {
			break
		}

		heap[i] = heap[j]
		heap[i].heapI = i
	}

	heap[i] = node
	heap[i].heapI = i
}
