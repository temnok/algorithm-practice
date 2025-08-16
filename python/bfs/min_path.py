
# min_path should return list of nodes of the minimal path
# from start to end nodes in an arbitrary graph represented
# as an adjacency list, or empty list if there is no such path
from collections import deque


def min_path(graph: list[list[int]], start: int, end: int) -> list[int]:
	# raise NotImplementedError('TODO')
	n = len(graph)
	prev = [-1] * n

	q = deque([start])
	prev[start] = start

	while len(q) > 0:
		u = q.popleft()
		for v in graph[u]:
			if prev[v] < 0:
				prev[v] = u
				q.append(v)

	if prev[end] < 0:
		return []

	path = [end]
	v = end
	while v != start:
		path.append(prev[v])
		v = prev[v]

	path.reverse()
	return path
