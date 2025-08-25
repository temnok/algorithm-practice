# bfs should visit graph nodes starting from node start
# using provided adjacency list and add the nodes in Breadth-First order to the output list.
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
# and start node 0, the result should be { 0, 1, 3, 2, 4, 6, 5, 7, 8 }

def bfs(graph: list[list[int]], start: int) -> list[int]:
	# raise NotImplementedError('TODO')
	n = len(graph)
	visited = [False] * n

	q = [start]
	visited[start] = True

	i = 0
	while i < len(q):
		u = q[i]
		i += 1

		for v in graph[u]:
			if not visited[v]:
				visited[v] = True
				q.append(v)

	return q
