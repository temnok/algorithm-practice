import random as rand

random = rand.Random(0)

class RandomStringsTestData:
	def __init__(self):
		n, max_val = random.randrange(50), random.randrange(1_000_000_000)
		self.array = [f'{random.randrange(1 + max_val)}' for _ in range(n)]
		self.want_array = self.array.copy()
		self.want_array.sort(key=lambda v: len(v))
