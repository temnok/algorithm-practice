from find_prefix import has_prefix
from trie_test_data import RandomTestData


def test_random_cases():
	for _ in range(10_000):
		td = RandomTestData()

		actual = has_prefix(td.strings)
		assert actual == td.has_prefix, \
			f'has_prefix({td.strings}):\nwant {td.has_prefix}\n got {actual}'
