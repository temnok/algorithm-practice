from ith_min import ith_min
from quick_sort_test_data import RandomTestData


def test_ith_min_random_cases():
	ith_min_random_cases(1000, 100)


def test_ith_min_random_cases_large_arrays():
	ith_min_random_cases(100, 10_000)


def ith_min_random_cases(test_count: int, max_n: int):
	for _ in range(test_count):
		td = RandomTestData(max_n)

		want = td.want[td.random_index]
		got = ith_min(td.array, td.random_index)

		assert got == want, \
			f'ith_min({td.array}, {td.random_index}):\nwant {want}\ngot {got}'
