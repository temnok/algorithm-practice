import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self, max_n=50):
		n, max_val = 1+random.randrange(max_n), random.randrange(max_n + 1)
		self.array = [random.randrange(1 + max_val) for _ in range(n)]

		self.want = self.array.copy()
		self.want.sort()
	
		self.random_index = random.randrange(n)
