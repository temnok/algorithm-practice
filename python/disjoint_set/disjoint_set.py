
class DisjointSet:
	# constructor should initialize a disjoint set
	def __init__(self, n: int):
		raise NotImplementedError('TODO')

	# union should return false if elements i and j are already in the same set
	# or perform union operation on their sets and return true
	def union(self, i: int, j: int) -> bool:
		raise NotImplementedError('TODO')

	# find should return set ID: if find(x) == find(y), then both x and y are in the same set
	def find(self, i: int) -> int:
		raise NotImplementedError('TODO')
