from min_task_time import min_task_time
from mtt_test_data import RandomTestData


def test_example():
	got = min_task_time(5, [20, 10, 30, 50, 40], [[3, 4], [2, 3], [1, 0], [2, 1], [3, 1], [3, 0]])
	assert got == 120


def test_random_cases():
	for _ in range(5_000):
		td = RandomTestData()
		got = min_task_time(td.n, td.time, td.before)

		assert got == td.want, \
			f'min_task_time({td.n}, {td.time}, {td.before}):\nwant {td.want}\n got {got}'
