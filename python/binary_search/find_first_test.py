
from bs_random_test_data import RandomTestData
from find_first import find_first_int


def test_find_first_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		want = td.expected_first
		got = find_first_int(td.array, td.val)
		assert got == want
