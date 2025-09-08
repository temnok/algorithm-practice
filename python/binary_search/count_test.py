from count import count_ints
from bs_random_test_data import RandomTestData


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		expected = td.expected_count
		actual = count_ints(td.array, td.val)
		assert actual == expected, \
			f'count_ints({td.array}, {td.val}):\n  Actual: {actual}\nExpected: {expected}'
