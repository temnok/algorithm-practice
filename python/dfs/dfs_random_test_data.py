import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		self.graph = []
		self.start = 0
		self.order1 = []
		self.order2 = []

		n, max_adj = 1+random.randrange(50), random.randrange(50)
		for _ in range(n):
			adj = []
	
			m = random.randrange(1 + max_adj)
			for _ in range(m):
				v = random.randrange(n)
				adj.append(v)

			self.graph.append(adj)

		self.start = random.randrange(n)
		self.walk1()
		self.walk2()

	def walk1(self):
		stack = [self.start]
		visited = [False] * len(self.graph)
		visited[self.start] = True

		while len(stack) > 0:
			u = stack.pop()
			self.order1.append(u)

			for v in self.graph[u]:
				if not visited[v]:
					stack.append(v)
					visited[v] = True

	def walk2(self):
		stack = [self.start]
		visited = [False] * len(self.graph)
		visited[self.start] = True

		while len(stack) > 0:
			u = stack.pop()
			stack = stack[:len(stack)-1]
			self.order2.append(u)

			row = self.graph[u]
			for i in reversed(range(len(row))):
				v = row[i]
				if not visited[v]:
					stack.append(v)
					visited[v] = True
