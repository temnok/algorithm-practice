
from bs_random_test_data import RandomTestData
from exists import exists_int


def test_exists_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		want = td.expected_first >= 0
		got = exists_int(td.array, td.val)
		assert got == want
