
from dijkstra_min_dist import dijkstra_min_dist
from dijkstra_test_data import RandomTestData


def test_example():
	want = [0, 30, 10, -1]
	got = dijkstra_min_dist(4, [[0, 2, 10], [0, 1, 100], [2, 1, 20]], 0)
	assert got == want


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		got = dijkstra_min_dist(td.n, td.edges, td.start)
		assert got == td.want, \
			f'dijkstra_min_dist({td.n}, {td.edges}, {td.start}):\nwant {td.want}\n got {got}'
