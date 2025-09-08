import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		self.n = 1 + random.randrange(50)
		self.want = 0

		self.edges = [
			[random.randrange(self.n), random.randrange(self.n), 1+random.randrange(100)]
			for _ in range(random.randrange(self.n*2))
		]

		mat = [[0]*self.n for _ in range(self.n)]
		for u, v, w in self.edges:
			mat[u][v] = max(mat[u][v], w)

		for m in range(self.n):
			for u in range(self.n):
				if mat[u][m] == 0:
					continue

				for v in range(self.n):
					if mat[m][v] == 0:
						continue

					w = mat[u][m] + mat[m][v]
					if -w < -mat[u][v]:
						mat[u][v] = w

		for u in range(self.n):
			if mat[u][u] > 0:
				self.want = -1
				break

			for v in range(self.n):
				if mat[u][v] > self.want:
					self.want = mat[u][v]
