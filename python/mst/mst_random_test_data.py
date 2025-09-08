import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		self.n = 1 + random.randrange(100)
		max_weight = 1 + random.randrange(100)
		self.edges = [
			[random.randrange(self.n), random.randrange(self.n), 1 + random.randrange(max_weight)]
			for _ in range(random.randrange(100))
		]
	
		ordered_edges = self.edges.copy()
		ordered_edges.sort(key=lambda edge: edge[2])

		self.par = [i for i in range(self.n)]
		self.size = [1 for _ in range(self.n)]

		self.expected_weight_sum = 0
		for e in ordered_edges:
			u, v, w = e[0], e[1], e[2]
			if self.union(u, v):
				self.expected_weight_sum += w

	def assert_answer(self, answer):
		actual_sum = 0
		edge_set = set([(e[0], e[1], e[2]) for e in self.edges])

		for e in answer:
			assert (e[0], e[1], e[2]) in edge_set, \
				f'minimum_spanning_forest({self.n}, {self.edges}):\nunexpected edge in response: {e}\nfull response: {answer}'

			u, v, w = e[0], e[1], e[2]
			actual_sum += w

			assert self.find(self.par[u]) == self.find(self.par[v]), \
				f'minimum_spanning_forest({self.n}, {self.edges}):\nwant nodes {u} and {v} to remain connected\n got {answer}'

		assert actual_sum == self.expected_weight_sum, \
			f'minimum_spanning_forest({self.n}, {self.edges}):\nwant weight sum {self.expected_weight_sum}\n got weight sum {actual_sum}'

		for e in answer:
			u, v = e[0], e[1]
			self.par[u] = -1
			self.par[v] = -1

		for i in range(self.n):
			assert not (self.par[i] >= 0 and self.size[i] > 1), \
				f'minimum_spanning_forest({self.n}, {self.edges}):\nwant node {i} to remain connected to other node(s)\n got {answer}'

	def find(self, i):
		while i != self.par[i]:
			p = self.par[i]
			self.par[i] = self.par[p]
			i = p

		return i

	def union(self, i, j):
		i, j = self.find(i), self.find(j)
		if i == j:
			return False

		if self.size[i] < self.size[j]:
			self.par[i] = j
			self.size[j] += self.size[i]
		else:
			self.par[j] = i
			self.size[i] += self.size[j]

		return True
