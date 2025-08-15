from collections import deque
import random as rand

random = rand.Random(0)


class RandomLabyrinthTestData:
	def __init__(self):
		m, n = 1+random.randrange(0, 50), 1+random.randrange(0, 50)

		self.labyrinth = [[False]*n for _ in range(m)]
		self.min_dist = 0
	
		for i in range(m):
			for j in range(n):
				self.labyrinth[i][j] = random.randrange(0, 2) == 1
	
		self.labyrinth[0][0] = False
		self.labyrinth[m-1][n-1] = False
	
		self.find_min_dist()

	def find_min_dist(self):
		m, n = len(self.labyrinth), len(self.labyrinth[0])
		dist = [-1]*(m*n)

		queue = deque([0])
		dist[0] = 0
	
		ds = [[-1, 0], [0, -1], [0, 1], [1, 0]]
	
		while len(queue) > 0:
			u = queue.popleft()
			if u == n*m-1:
				break

			i0, j0 = u // n, u % n
	
			for d in ds:
				i, j = i0+d[0], j0+d[1]
	
				if 0 <= i < m and 0 <= j < n and not self.labyrinth[i][j]:
					v = i*n + j
					if dist[v] < 0:
						queue.append(v)
						dist[v] = dist[u] + 1

		self.min_dist = dist[m * n - 1]
