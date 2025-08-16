import sys


class Node:
	def __init__(self, i):
		self.i = i
		self.heap_i = i
		self.val = sys.maxsize
		self.edges = []


class Edge:
	def __init__(self, v, w):
		self.v = v
		self.w = w


# dijkstra_min_dist should return min distance to all nodes from start node,
# or -1 if node is unreachable from the start node.
# edges contain triples [u, v, d], describing *directed* edge from node u to v with distance d (d > 0).
# There can be multiple edges between same one or two nodes with different distances.
# For example, if n = 4, edges = [[0, 2, 10], [0, 1, 100], [2, 1, 20]] and start = 0 then
# result should be [0, 30, 10, -1]
def dijkstra_min_dist(n: int, edges: list[list[int]], start: int) -> list[int]:
	# raise NotImplementedError('TODO')

	heap = []
	for i in range(n):
		heap.append(Node(i))

	for e in edges:
		u, v, w = e[0], e[1], e[2]
		heap[u].edges.append(Edge(heap[v], w))

	heap[start].val = 0
	heap_up(heap, start)

	dist = [-1]*n

	while heap[0].val < sys.maxsize:
		u = heap[0]
		dist[u.i] = u.val
		u.val = sys.maxsize
		heap_down(heap, u.heap_i)

		for e in u.edges:
			if dist[e.v.i] < 0 and dist[u.i] + e.w < e.v.val:
				e.v.val = dist[u.i] + e.w
				heap_up(heap, e.v.heap_i)

	return dist


def heap_down(heap: list[Node], i: int):
	node = heap[i]

	j = i*2+1
	while j < len(heap):
		if j+1 < len(heap) and heap[j+1].val < heap[j].val:
			j += 1

		if node.val <= heap[j].val:
			break

		heap[i] = heap[j]
		heap[i].heap_i = i

		i, j = j, j*2+1

	heap[i] = node
	heap[i].heap_i = i


def heap_up(heap: list[Node], i: int):
	node = heap[i]

	j = (i-1)//2
	while i > 0 and heap[j].val > node.val:
		heap[i] = heap[j]
		heap[i].heap_i = i

		i, j = j, (j-1)//2

	heap[i] = node
	heap[i].heap_i = i
