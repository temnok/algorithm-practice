from breadth_first_search import bfs
from random_test_data import RandomTestData


def test_bfs():
	graph = [
		[1, 3],
		[2, 4],
		[5],
		[4, 6],
		[5, 7],
		[8],
		[7],
		[8],
		[2],
	]

	want = [0, 1, 3, 2, 4, 6, 5, 7, 8]

	got = bfs(graph, 0)

	if got != want:
		assert False, f'bfs({graph}, 0):\nwant {want}\n got {got}'


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		want = td.order
		got = bfs(td.graph, td.start)

		if got != want:
			assert False, f'bfs({td.graph}, {td.start}):\nwant {want}\n got {got}'
