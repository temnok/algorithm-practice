from heap_init import heap_init
from bh_random_test_data import RandomTestData
from bh_random_test_data import min_heap_is_ok


def test_heap_init_random_cases():
	for _ in range(1_000):
		td = RandomTestData()

		assert min_heap_is_ok(td.heap)

		got = td.array.copy()
		heap_init(got)

		assert min_heap_is_ok(got), \
			f'heap_init({td.array}): not a valid heap after the call:\n{got}'

		assert sorted(td.array) == sorted(got), \
			f'heap_init({td.array}): not same elements after the call:\n{got}'
