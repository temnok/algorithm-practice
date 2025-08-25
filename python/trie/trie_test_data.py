import random as rand

random = rand.Random(0)


class RandomTestData:
	def __init__(self):
		n = 1 + random.randrange(26)
		m = 1 + random.randrange(20)

		self.strings = [
			str(''.join([
				chr(ord('a') + random.randrange(n))
				for _ in range(1 + random.randrange(m))
			])) for _ in range(1 + random.randrange(20))
		]

		self.has_prefix = False

		for i, a in enumerate(self.strings):
			for j, b in enumerate(self.strings):
				if i != j and (a.startswith(b) or b.startswith(a)):
					self.has_prefix = True
					break
