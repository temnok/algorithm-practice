from quick_sort import quick_sort_ints
from quick_sort_test_data import RandomTestData


def test_quick_sort_random_cases():
	for _ in range(1_000):
		td = RandomTestData()

		got = td.array.copy()
		quick_sort_ints(got)

		assert got == td.want, \
			f'quick_sort_ints({td.array}):\nwant: {td.want}\n got: {got}'


def test_performance_for_large_zero_array():
	arr = [0]*10_000_000
	quick_sort_ints(arr)


def test_performance_for_large_sparse_array():
	arr = [0]*10_000_000

	for i in range(len(arr)):
		if i % 1000 == 0:
			arr[i] = len(arr) - i

	quick_sort_ints(arr)
