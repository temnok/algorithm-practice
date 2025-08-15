from min_path import min_path
from random_test_data import RandomTestData


def test_min_path():
	for _ in range(10_000):
		td = RandomTestData()

		want = td.min_path
		got = min_path(td.graph, td.start, td.end)

		if got != want:
			assert False, f'minPath({td.graph}, {td.start}, {td.end}):\nwant {want}\n got {got}'
