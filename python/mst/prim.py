
# minimum_spanning_forest should return subset of edges that form
# "Minimum Spanning Forest" of the input *undirected* graph represented
# by n nodes and provided list of edges. Each edge is represented by three
# integers (u, v, w), where u and v are node indexes, and w is edge weight.
#
# Restrictions:
# * Prim's algorithm should be used for implementation
# * input graph may be unconnected
# * edge (u, v, w) is equivalent to edge (v, u, w)
# * n > 0, 0 <= u < n, 0 <= v < n, w > 0
# * there can be multiple edges between same pair of nodes with same or different weights
# * order of returned edges is ignored
# * no duplicates are allowed in the returned list
def minimum_spanning_forest(n: int, edges: list[list[int]]) -> list[list[int]]:
	# raise NotImplementedError('TODO')

	graph = [[] for _ in range(n)]
	for e in edges:
		u, v = e[0], e[1]
		graph[u].append(e)
		graph[v].append(e)

	visited = [False]*n
	heap = []
	ans = []

	for u in range(n):
		if visited[u]:
			continue

		visited[u] = True

		for e in graph[u]:
			heap_add(heap, e)

		while len(heap) > 0:
			e = heap_remove(heap)

			for v in e[:2]:
				if visited[v]:
					continue

				visited[v] = True
				ans.append(e)

				for ve in graph[v]:
					heap_add(heap, ve)

	return ans


def heap_add(heap, e):
	heap.append(e)
	heap_up(heap, len(heap)-1)


def heap_remove(heap):
	f = heap[0]
	l = heap.pop()

	if len(heap) > 0:
		heap[0] = l
		heap_down(heap, 0)

	return f


def heap_up(heap, i):
	e = heap[i]

	j = (i-1)//2
	while i > 0 and e[2] < heap[j][2]:
		heap[i] = heap[j]
		i, j = j, (j-1)//2

	heap[i] = e


def heap_down(heap, i):
	e = heap[i]

	j = i*2 + 1
	while j < len(heap):
		if j+1 < len(heap) and heap[j+1][2] < heap[j][2]:
			j += 1

		if e[2] <= heap[j][2]:
			break

		heap[i] = heap[j]
		i, j = j, j*2 + 1

	heap[i] = e
