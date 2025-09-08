from topo_sort import topo_sort
from topo_sort_test_data import RandomTestData


def test_example():
	assert topo_sort([[2, 1], [], [1], [0, 2]]) == [3, 0, 2, 1]

	assert len(topo_sort([[2, 1], [3], [1], [0, 2]])) == 0
	assert len(topo_sort([[0]])) == 0
	assert len(topo_sort([[1], [0]])) == 0


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		actual = topo_sort(td.adj)

		if td.hasCycle:
			assert len(actual) == 0
			continue

		n = len(td.graph)
		assert len(actual) == n

		order = [0] * n
		for i in range(n):
			order[actual[i]] = i

		for u in range(n):
			for v in td.graph[u]:
				assert order[u] < order[v], \
					f'topo_sort({td.graph}):\n  Node {u} should come before node {v} in the result!\n  Result: {actual}'
