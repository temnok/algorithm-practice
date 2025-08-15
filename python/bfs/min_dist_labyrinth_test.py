from min_dist_labyrinth import min_dist_labyrinth
from random_labyrinth_test_data import RandomLabyrinthTestData


def test_min_dist():
	for _ in range(10_000):
		td = RandomLabyrinthTestData()

		want = td.min_dist
		got = min_dist_labyrinth(td.labyrinth)

		if got != want:
			assert False, f'minDistLabyrinth({td.labyrinth}):\nwant {want}\n got {got}'
