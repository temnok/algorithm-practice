from set import Set

import random as rand
random = rand.Random(0)


class Node:
	def __init__(self, val=0, nxt=None):
		self.val = val
		self.next = [nxt]


class SkipList(Set):
	# __init__ should return initialized skip list instance
	def __init__(self):
		# raise NotImplementedError('TODO')

		self.root = Node()

	# add should insert given val into this skip list
	# and return true if list did not contain val previously,
	# or do nothing and return false if val is already present
	def add(self, val: int) -> bool:
		# raise NotImplementedError('TODO')

		prev = self._find_prev(val)
		nxt = prev[0].next[0]
		if nxt is not None and nxt.val == val:
			return False

		node = Node(val, nxt)
		prev[0].next[0] = node

		while random.randrange(2) == 0:
			h = len(node.next)
			if h < len(prev):
				node.next.append(prev[h].next[h])
				prev[h].next[h] = node
			else:
				node.next.append(None)
				self.root.next.append(node)

		return True

	# contains should return true if val is present in the list
	def contains(self, val: int) -> bool:
		# raise NotImplementedError('TODO')

		prev = self._find_prev(val)
		node = prev[0].next[0]
		return node is not None and node.val == val

	# remove should delete val in the list and return true if val was present
	# or do nothing and return false otherwise
	def remove(self, val: int) -> bool:
		# raise NotImplementedError('TODO')

		prev = self._find_prev(val)

		node = prev[0].next[0]
		if node is None or node.val != val:
			return False

		for i, n in enumerate(node.next):
			prev[i].next[i] = n

		return True

	def _find_prev(self, val: int) -> list[Node]:
		cur = self.root
		prev = [cur]*len(cur.next)

		for i in range(len(prev)-1, -1, -1):
			while cur.next[i] is not None and cur.next[i].val < val:
				cur = cur.next[i]

			prev[i] = cur

		return prev
