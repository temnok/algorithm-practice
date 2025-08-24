from ..set.set import Set


class SplayTree(Set):
	# __init__ should return initialized splay tree instance
	def __init__(self):
		raise NotImplementedError('TODO')

	# add should insert given val into this splay tree
	# and return true if list did not contain val previously,
	# or do nothing and return false if val is already present
	def add(self, val: int) -> bool:
		raise NotImplementedError('TODO')

	# contains should return true if val is present in the tree
	def contains(self, val: int) -> bool:
		raise NotImplementedError('TODO')

	# remove should delete val in the tree and return true if val was present
	# or do nothing and return false otherwise
	def remove(self, val: int) -> bool:
		raise NotImplementedError('TODO')
