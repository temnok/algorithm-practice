from min_dist import min_dist
from random_test_data import RandomTestData


def test_min_dist():
	for _ in range(10_000):
		td = RandomTestData()

		want = td.min_dist
		got = min_dist(td.graph, td.start, td.end)

		if got != want:
			assert False, f'minDist({td.graph}, {td.start}, {td.end}):\nwant {want}\n got {got}'
