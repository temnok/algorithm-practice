
class DisjointSet:
	# constructor should initialize a disjoint set
	def __init__(self, n: int):
		# raise NotImplementedError('TODO')

		self.parent = [i for i in range(n)]
		self.size = [1 for _ in range(n)]

	# union should return false if elements i and j are already in the same set
	# or perform union operation on their sets and return true
	def union(self, i: int, j: int) -> bool:
		# raise NotImplementedError('TODO')

		i, j = self.find(i), self.find(j)
		if i == j:
			return False

		if self.size[i] < self.size[j]:
			self.parent[i] = j
			self.size[j] += self.size[i]
		else:
			self.parent[j] = i
			self.size[i] += self.size[j]

		return True

	# find should return set ID: if find(x) == find(y), then both x and y are in the same set
	def find(self, i: int) -> int:
		# raise NotImplementedError('TODO')

		while (p := self.parent[i]) != i:
			self.parent[i] = self.parent[p]
			i = p

		return i
