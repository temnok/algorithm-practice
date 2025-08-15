# min_dist_labyrinth should return minimal length of a path from
# top-left corner to bottom-right corner of the MxN labyrinth (M > 0, N > 0)
# or -1 if no such path exists. Path should consists of empty cells (with "false" value) only.
# Both start and end cells will be empty. Only horizontal and vertical moves are allowed.
# Diagonal moves are NOT allowed.

from collections import deque


def min_dist_labyrinth(labyrinth: list) -> int:
	# raise NotImplementedError('TODO')

	m, n = len(labyrinth), len(labyrinth[0])
	dist = [-1] * (m*n)

	q = deque([0])
	dist[0] = 0
	while len(q) > 0:
		u = q.popleft()
		i0, j0 = u // n, u % n
		for (i, j) in [(i0-1, j0), (i0, j0-1), (i0, j0+1), (i0+1, j0)]:
			if 0 <= i < m and 0 <= j < n and not labyrinth[i][j] and dist[v := i*n + j] < 0:
				dist[v] = dist[u] + 1
				q.append(v)

	return dist[n*m-1]
