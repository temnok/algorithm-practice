
from bs_random_test_data import RandomTestData
from find_last import find_last_int


def test_find_first_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		want = td.expected_last
		got = find_last_int(td.array, td.val)
		assert got == want
