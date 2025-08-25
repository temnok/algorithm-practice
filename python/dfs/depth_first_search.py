
# dfs should visit graph nodes starting from node start
# using provided adjacency list and add the nodes in Depth-First order to output list.
# For example, for the given adjacency list
#
# { 0:{1, 3}, 1:{2,4}, 2:{5}, 3:{4,6}, 4:{5,7}, 5:{8}, 6:{7}, 7:{8}, 8:{2} }
#
# 0->1->2<-
# v  v  v |
# 3->4->5 |
# v  v  v |
# 6->7->8-
#
# and start node 0, the result should be either { 0, 1, 2, 5, 8, 4, 7, 3, 6 } or { 0, 3, 6, 7, 8, 2, 5, 4, 1 }
def dfs(graph: list[list[int]], start: int) -> list[int]:
	raise NotImplementedError('TODO')
