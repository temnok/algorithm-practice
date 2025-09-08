import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		self.n = 1 + random.randrange(50)
		self.start = random.randrange(self.n)
		self.edges = [
			[random.randrange(self.n), random.randrange(self.n), 1 + random.randrange(50)]
			for _ in range(random.randrange(self.n*2))
		]

		self.want = [-1] * self.n
		self.want[self.start] = 0
	
		for _ in range(self.n-1):
			for edge in self.edges:
				u, v, dist = edge[0], edge[1], edge[2]
				if self.want[u] >= 0 and (self.want[v] < 0 or self.want[u] + dist < self.want[v]):
					self.want[v] = self.want[u] + dist
