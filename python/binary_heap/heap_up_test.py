from heap_up import heap_up
from bh_random_test_data import RandomTestData
from bh_random_test_data import min_heap_is_ok


def test_heap_up_random_cases():
	for _ in range(1_000):
		td = RandomTestData()

		assert min_heap_is_ok(td.heap)

		td.heap[td.random_index] -= td.randomValue

		got = td.heap.copy()
		heap_up(got, td.random_index)

		assert min_heap_is_ok(got), \
			f'heap_up({td.heap}, {td.random_index}): not a valid heap after the call:\n{got}'

		assert sorted(got) == sorted(td.heap), \
			f'HeapUp({td.heap}, {td.random_index}): not same elements after the call:\n{got}'
