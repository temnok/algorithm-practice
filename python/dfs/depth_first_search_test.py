from depth_first_search import dfs
from dfs_random_test_data import RandomTestData


def test_dfs():
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
	want1 = [0, 1, 2, 5, 8, 4, 7, 3, 6]
	want2 = [0, 3, 6, 7, 8, 2, 5, 4, 1]

	got = dfs(graph, 0)
	assert got == want1 or got == want2, \
		f'Dfs({graph}, 0):\nwant {want1} or {want2}\n got {got}'


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		want1 = td.order1
		want2 = td.order2
		got = dfs(td.graph, td.start)

		assert got == want1 or got == want2, \
			f'Dfs({td.graph}, {td.start}):\nwant {want1} or {want2}\ngot {got}'
