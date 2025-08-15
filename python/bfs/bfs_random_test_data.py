import random as rand

random = rand.Random(0)

class RandomTestData:
	def __init__(self):
		self.graph = []
		self.start = 0
		self.end = 0
		self.min_dist = 0
		self.min_path = []
		self.order = []

		n = 1 + random.randrange(0, 50)
		max_adj = random.randrange(0, 50)

		for _ in range(n):
			adj = []
			m = random.randrange(0, 1 + max_adj)
			for _ in range(m):
				v = random.randrange(0, n)
				adj.append(v)

			self.graph.append(adj)

		self.start = random.randrange(0, n)
		self.end = random.randrange(0, n)
		self.walk()
		self.min_dist = len(self.min_path) - 1


	def walk(self):
		prev = [-1]*len(self.graph)

		self.order.append(self.start)
		prev[self.start] = self.start

		i = 0
		while i < len(self.order):
			u = self.order[i]
			i += 1
			for v in self.graph[u]:
				if prev[v] < 0:
					self.order.append(v)
					prev[v] = u

		if prev[self.end] < 0:
			return

		v = self.end
		while True:
			self.min_path.append(v)
			if v == prev[v]:
				break
			v = prev[v]

		l, r = 0, len(self.min_path) - 1
		while l < r:
			self.min_path[l], self.min_path[r] = self.min_path[r], self.min_path[l]
			l, r = l+1, r-1
