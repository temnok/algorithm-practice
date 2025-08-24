from abc import ABC, abstractmethod
import random as rand


class Set(ABC):
	@abstractmethod
	def add(self, val) -> bool:
		pass

	@abstractmethod
	def contains(self, val) -> bool:
		pass

	@abstractmethod
	def remove(self, val) -> bool:
		pass


def generic_random_cases(type_name, new_func):
	random = rand.Random(0)

	for _ in range(10_000):
		set = new_func()

		max_val = random.randrange(100)
		add_prob = random.random()
		find_prob = random.random()

		expected_set = {}

		for _ in range(1_000):
			val = random.randrange(max_val + 1)

			p = random.random()
			if p < add_prob:
				expected = not expected_set[val]
				actual = set.add(val)
				assert actual == expected, \
					f'{type_name}({expected_set})\n.add({val}):\nwant {expected}\n got {actual}'
				expected_set[val] = True
			elif p < find_prob:
				expected = expected_set[val]
				actual = set.contains(val)
				assert actual == expected, \
					f'{type_name}({expected_set})\n.contains({val}):\nwant {expected}\n got {actual}'
			else:
				expected = expected_set[val]
				actual = set.remove(val)
				assert actual == expected, \
					f'{type_name}({expected_set})\n.remove({val}):\nwant {expected}\n got {actual}'
				del(expected_set[val])
