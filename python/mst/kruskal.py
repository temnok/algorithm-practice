
# minimum_spanning_forest should return subset of edges that form
# "Minimum Spanning Forest" of the input *undirected* graph represented
# by n nodes and provided list of edges. Each edge is represented by three
# integers (u, v, w), where u and v are node indexes, and w is edge weight.
#
# Restrictions:
# * Kruskal's algorithm should be used for implementation
# * input graph may be unconnected
# * edge (u, v, w) is equivalent to edge (v, u, w)
# * n > 0, 0 <= u < n, 0 <= v < n, w > 0
# * there can be multiple edges between same pair of nodes with same or different weights
# * order of returned edges is ignored
# * no duplicates are allowed in the returned list
def minimum_spanning_forest(n: int, edges: list[list[int]]) -> list[list[int]]:
	# raise NotImplementedError('TODO')

	edges.sort(key=lambda e: e[2])

	par = [i for i in range(n)]
	size = [1 for _ in range(n)]

	ans = []

	for e in edges:
		u, v = e[0], e[1]
		if union(par, size, u, v):
			ans.append(e)

	return ans


def find(par, i):
	while i != par[i]:
		p = par[i]
		par[i] = par[p]
		i = p

	return i


def union(par, size, i, j):
	i, j = find(par, i), find(par, j)
	if i == j:
		return False

	if size[i] < size[j]:
		par[i] = j
		size[j] += size[i]
	else:
		par[j] = i
		size[i] += size[j]

	return True
