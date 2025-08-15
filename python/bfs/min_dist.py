
# min_dist should return the minimal distance between start and end nodes in an arbitrary graph
# represented as an adjacency list, or -1 if there is no path from start to end nodes
from collections import deque


def min_dist(graph: list, start: int, end: int) -> int:
	# raise NotImplementedError('TODO')

	n = len(graph)
	dist = [-1] * n

	q = deque([start])
	dist[start] = 0

	while len(q) > 0:
		u = q.popleft()
		for v in graph[u]:
			if dist[v] < 0:
				dist[v] = dist[u]+1
				q.append(v)

	return dist[end]
