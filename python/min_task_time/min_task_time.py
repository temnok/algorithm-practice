
# min_task_time should return minimal time for completing n tasks where each task i takes time[i] > 0 to complete.
# Tasks may have dependencies so one must be performed strictly before another.
# Array 'before' contains pairs [u, v] meaning that task u should be done before v. Dependencies are
# transitive so if 'before' array contains pairs [a, b] and [b, c], then task a should be done before task c.
# If there is no dependencies before two tasks, they must be completed in parallel.
#
# There will be no dependency cycles between the tasks so the answer should always be positive.
#
# For example, for the following example
#
# n = 5, time = [20, 10, 30, 50, 40], before = [[3, 4], [2, 3], [1, 0], [2, 1], [3, 1], [3, 0]]
#
# the result is 120:
# task 2 must be done first (t=30), then task 3 (t=30+50=80), then tasks 4 and (1-then-0) can be done in parallel
# (t=80+max(40, 10+20)=120)
#
#  +-------+------------+
#  |       |            |
#  v       v            |
#  *<-----*<-----*----->*------>*
#  0      1      2      3       4
# t=20   t=10   t=30   t=50   t=40

def min_task_time(n: int, time: list[int], before: list[list[int]]) -> int:
	# raise NotImplementedError('TODO')

	graph = [[] for _ in range(n)]
	ins = [0]*n
	for e in before:
		u, v = e[0], e[1]
		graph[u].append(v)
		ins[v] += 1

	order = []
	for v, in_count in enumerate(ins):
		if in_count == 0:
			order.append(v)

	i = 0
	while i < len(order):
		u = order[i]
		i += 1
		for v in graph[u]:
			ins[v] -= 1
			if ins[v] == 0:
				order.append(v)

	max_time = max(time)
	dist = time.copy()

	for u in order:
		for v in graph[u]:
			dist[v] = max(dist[v], dist[u] + time[v])
			max_time = max(max_time, dist[v])

	return max_time
