from max_path import max_path_length
from max_path_test_data import RandomTestData


def test_example():
	got = max_path_length(5, [[3, 1, 100], [1, 0, 20], [3, 2, 30], [2, 1, 10], [3, 4, 110]])
	assert got == 120


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()
		got = max_path_length(td.n, td.edges)
		assert got == td.want, \
			f'max_path_length({td.n}, {td.edges}):\nwant {td.want}\n got {got}'
