from merge_sort_array import merge_sort_array
from merge_sort_strings_test_data import RandomStringsTestData


def test_string_random_cases():
	for _ in range(1_000):
		td = RandomStringsTestData()

		got = td.array.copy()
		merge_sort_array(got, lambda a, b: len(a) - len(b))

		assert got == td.want_array, \
			f'merge_sort_array({td.array}):\nwant: {td.want_array}\n got: {got}'
