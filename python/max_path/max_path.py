
# max_path_length should return max path length within the graph with n nodes and provided edges,
# or -1 if the graph contains a cycle and thus the max path length is infinity.
# edges contain triples [u, v, d], each describing *directed* edge from node u to v with distance d (d > 0)
# between nodes u and v.
#
# There can be multiple edges between same one or two nodes with different distances.
# For example, if n = 5, edges = [[3, 1, 100], [1, 0, 20], [3, 2, 30], [2, 1, 10], [3, 4, 110]] then
# result should be 120.
#
#               100
#          -------------
#         |             |
#     20  v  10     30  |  110
#  *<-----*<-----*<-----*------>*
#  0      1      2      3       4
def max_path_length(n: int, edges: list[list[int]]) -> int:
	# raise NotImplementedError('TODO')

	graph = [[] for _ in range(n)]
	ins = [0]*n
	for e in edges:
		u, v = e[0], e[1]
		graph[u].append(e)
		ins[v] += 1

	order = []
	for v, in_count in enumerate(ins):
		if in_count == 0:
			order.append(v)

	i = 0
	while i < len(order):
		u = order[i]
		i += 1

		for e in graph[u]:
			v = e[1]
			ins[v] -= 1
			if ins[v] == 0:
				order.append(v)

	if len(order) < n:
		return -1

	dist = [0]*n
	max_dist = 0
	for u in order:
		for e in graph[u]:
			v, w = e[1], e[2]
			dist[v] = max(dist[v], dist[u]+w)
			max_dist = max(max_dist, dist[v])

	return max_dist
