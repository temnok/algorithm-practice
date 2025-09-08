import random as rand
from disjoint_set import DisjointSet

random = rand.Random(0)


def test_large_array():
	n = 1 << 20
	ds = DisjointSet(n)
	
	step = 1
	while step < n:
		i = 0
		while i < n:
			j = i + step
			assert ds.find(i) != ds.find(j)
			assert ds.union(i, j)
			assert ds.find(i) == ds.find(j)
			i += step * 2

		step *= 2


def test_random_cases():
	for _ in range(1_000):
		n = 1 + random.randrange(100)
		ref = [i for i in range(n)]

		ds = DisjointSet(n)

		for _ in range(100):
			i, j = random.randrange(n), random.randrange(n)
			if random.randrange(2) == 0:
				want = ref[i] == ref[j]
				got = ds.find(i) == ds.find(j)
				assert got == want, \
					f'DisjointSet({ref}):\nfind({i})==find({j}):\nwant {want}\n got {got}'
			else:
				want = ref[i] != ref[j]
				if want:
					old, new = ref[i], ref[j]
					for k in range(n):
						if ref[k] == old:
							ref[k] = new

				got = ds.union(i, j)
				assert got == want, \
					f'DisjointSet({ref}):\nunion({i},{j}):\nwant {want}\n got {got}'
