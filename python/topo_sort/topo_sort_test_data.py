import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		n = 1 + random.randrange(100)
		self.graph = [[] for _ in range(n)]
	
		m = 1 + random.randrange(n*2)
		ins = [0] * n
		for _ in range(m):
			u = random.randrange(n)
			v = random.randrange(n)
			self.graph[u].append(v)
			ins[v] += 1

		self.adj = [[] for _ in range(n)]
		for u in range(n):
			self.adj[u] = [[] for _ in range(len(self.graph[u]))]
			for i in range(len(self.adj[u])):
				self.adj[u][i] = self.graph[u][i]

		q = [0]*n
		r = 0
		for v in range(n):
			if ins[v] == 0:
				q[r] = v
				r += 1

		l = 0
		while l < r:
			for v in self.adj[q[l]]:
				ins[v] -= 1
				if ins[v] == 0:
					q[r] = v
					r += 1
			l += 1

		self.hasCycle = r < n
