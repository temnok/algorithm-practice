import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		n, max_val = random.randrange(50), random.randrange(50)
		self.array = [random.randrange(1 + max_val) for _ in range(n)]

		self.val = random.randrange(1 + max_val)
		self.expected_first = -1
		self.expected_last = -1
		self.expected_count = 0
	
		self.array.sort()
		for i in range(n):
			if self.array[i] == self.val:
				if self.expected_first < 0:
					self.expected_first = i

				self.expected_last = i
				self.expected_count += 1
