from bh_random_test_data import RandomTestData
from heap_sort import heap_sort


def test_heap_sort_random_cases():
	for _ in range(1_000):
		td = RandomTestData()

		want = sorted(td.array)

		got = td.array.copy()
		heap_sort(got)

		assert got == want, \
			f'HeapSort({td.array}):\nwant {want}\n got {got}'
