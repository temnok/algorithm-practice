
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
	raise NotImplementedError('TODO')
