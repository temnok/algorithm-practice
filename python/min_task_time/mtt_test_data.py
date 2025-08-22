import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):	
		while not self._success():
			pass
	
	def _success(self):
		self.n = 1 + random.randrange(50)
		self.time = [1 + random.randrange(50) for _ in range(self.n)]

		mat = [[0]*(self.n+1) for _ in range(self.n+1)]
		edges = []

		for i in range(random.randrange(self.n*2)):
			u, v = random.randrange(self.n), random.randrange(self.n)
			if mat[u][v] > 0:
				continue

			edges.append([u, v])
			mat[u][v] = self.time[u]

		for u in range(self.n):
			edges.append([u, self.n])
			mat[u][self.n] = self.time[u]

		for m in range(self.n+1):
			for u in range(self.n+1):
				if mat[u][m] == 0:
					continue

				for v in range(self.n+1):
					if mat[m][v] == 0:
						continue

					w = mat[u][m] + mat[m][v]
					if -w < -mat[u][v]:
						mat[u][v] = w

		self.want = 0
		for u in range(self.n+1):
			if mat[u][u] > 0:
				return False

			for v in range(self.n+1):
				if mat[u][v] > self.want:
					self.want = mat[u][v]

		self.before = edges[:len(edges)-self.n]
	
		return True
