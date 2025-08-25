import random as rand

random = rand.Random(0)


class RandomIntsTestData:
	def __init__(self):
		n, max_val = random.randrange(50), random.randrange(50)
		self.array = [random.randrange(1 + max_val) for _ in range(n)]
		self.want_array = self.array.copy()
		self.want_array.sort()
