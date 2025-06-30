package dijkstra

import "math"

// dijkstraMinPath should return min distance to all nodes from start node,
// or -1 if node is unreachable from the start node.
// edges contain triples [u, v, d], describing *directed* edge from node u to v with distance d (d > 0).
// There can be multiple edges between same one or two nodes with different distances.
// For example, if n = 4, edges = [[0, 2, 10], [0, 1, 100], [2, 1, 20]] and start = 0 then
// result should be [0, 30, 10, -1]
func dijkstraMinPath(n int, edges [][]int, start int) []int {
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}

	heap := make([]*Node, n)
	for i := range heap {
		heap[i] = &Node{i: i, heapI: i, dist: math.MaxInt}
	}

	for _, edge := range edges {
		u, v, w := heap[edge[0]], heap[edge[1]], edge[2]
		u.edges = append(u.edges, &Edge{next: v, dist: w})
	}

	heap[start].dist = 0
	heapUp(heap, start)

	for heap[0].dist < math.MaxInt {
		u := heap[0]
		ans[u.i] = u.dist

		for _, e := range u.edges {
			v := e.next
			if ans[v.i] < 0 && u.dist+e.dist < v.dist {
				v.dist = u.dist + e.dist
				heapUp(heap, v.heapI)
			}
		}

		u.dist = math.MaxInt
		heapDown(heap, 0)
	}

	return ans
}

type Node struct {
	i, heapI, dist int
	edges          []*Edge
}

type Edge struct {
	next *Node
	dist int
}

func heapUp(heap []*Node, i int) {
	val := heap[i]

	for j := (i - 1) / 2; i > 0; i, j = j, (j-1)/2 {
		if heap[j].dist <= val.dist {
			break
		}

		heap[i] = heap[j]
		heap[i].heapI = i
	}

	heap[i] = val
	heap[i].heapI = i
}

func heapDown(heap []*Node, i int) {
	val := heap[i]

	for j := i*2 + 1; j < len(heap); i, j = j, j*2+1 {
		if j+1 < len(heap) && heap[j+1].dist < heap[j].dist {
			j++
		}

		if val.dist <= heap[j].dist {
			break
		}

		heap[i] = heap[j]
		heap[i].heapI = i
	}

	heap[i] = val
	heap[i].heapI = i
}
