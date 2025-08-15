import random as rand
from counting_sort import counting_sort

random = rand.Random(0)


def test_random_cases():
	for _ in range(10_000):
		mi = random.randrange(2_000_000_000) - 1_000_000_000
		ma = mi + random.randrange(1000)

		arr = [mi + random.randrange(ma-mi+1) for _ in range(1+random.randrange(1000))]

		want = arr.copy()
		want.sort()

		got = arr.copy()
		counting_sort(got)

		assert got == want, f'CountingSort({arr}):\nwant {want}\n got {got}'
