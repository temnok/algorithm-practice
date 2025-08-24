from ..set.set import Set


class Skiplist(Set):
	# __init__ should return initialized skip list instance
	def __init__(self):
		raise NotImplementedError('TODO')

	# add should insert given val into this skip list
	# and return true if list did not contain val previously,
	# or do nothing and return false if val is already present
	def add(self, val: int) -> bool:
		raise NotImplementedError('TODO')

	# contains should return true if val is present in the list
	def contains(self, val: int) -> bool:
		raise NotImplementedError('TODO')

	# remove should delete val in the list and return true if val was present
	# or do nothing and return false otherwise
	def remove(self, val: int) -> bool:
		raise NotImplementedError('TODO')
