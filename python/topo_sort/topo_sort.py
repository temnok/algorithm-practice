
# topo_sort should perform topological sorting of a directed graph represented by its adjacency list.
# The provided graph can contain duplicate links or links from a node to itself.
# If graph is not a DAG (i.e. contains cycles) then the empty list should be returned.
# Otherwise, returned array should contain node numbers as in the following example:
#
# Input:
#
# Adjacency list: [[2, 1], [], [1], [0, 2]]
#
#         0
#    -----*<----
#   |     |    |
#   v     v    |
#   *<---*<----*
#   1    2    3
#
# Expected output: [3, 0, 2, 1]

def topo_sort(graph: list[list[int]]) -> list[int]:
	# raise NotImplementedError('TODO')

	n = len(graph)
	ins = [0] * n
	for vs in graph:
		for v in vs:
			ins[v] += 1

	q = [u for u in range(n) if ins[u] == 0]
	i = 0
	while i < len(q):
		u = q[i]
		i += 1
		for v in graph[u]:
			ins[v] -= 1
			if ins[v] == 0:
				q.append(v)

	return q if len(q) == n else []
