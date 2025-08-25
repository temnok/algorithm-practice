import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		n, max_val = 1+random.randrange(5), random.randrange(5)
		self.array = [random.randrange(1 + max_val) for _ in range(n)]
		self.heap = self.array.copy()

		for i0 in reversed(range(len(self.heap)//2 + 1)):
			i = i0
			val = self.heap[i]
			j = i*2 + 1
			while j < len(self.heap):
				if j+1 < len(self.heap) and self.heap[j+1] < self.heap[j]:
					j += 1
				if val <= self.heap[j]:
					break
				self.heap[i] = self.heap[j]

				i, j = j, j*2+1
			self.heap[i] = val

		self.random_index = random.randrange(n)
		self.randomValue = random.randrange(1 + max_val)


def min_heap_is_ok(heap: list) -> bool:
	for i in range(1, len(heap)):
		if heap[(i-1)//2] > heap[i]:
			return False

	return True
