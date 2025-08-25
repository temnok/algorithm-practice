from merge_sort_ints import merge_sort_ints
from merge_sort_ints_test_data import RandomIntsTestData


def test_int_random_cases():
	for _ in range(1_000):
		td = RandomIntsTestData()

		got = td.array.copy()
		merge_sort_ints(got)

		assert got == td.want_array, \
			f'merge_sort_ints({td.array}):\nwant: {td.want_array}\n got: {got}'
