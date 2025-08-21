
# has_prefix should return true if there is a string in dict that is a prefix of another string in dict.
# All strings in dict are expected to consist of low-case latin letters only, 'a'...'z'.
def has_prefix(dict: list[str]) -> bool:
	# raise NotImplementedError('TODO')

	root = Node()
	for word in dict:
		cur = root
		for i, char in enumerate(word):
			next = cur.next.get(char)

			if next is None:
				next = Node()
				cur.next[char] = next
			else:
				if next.end or i == len(word)-1:
					return True

			cur = next

		cur.end = True

	return False


class Node:
	def __init__(self):
		self.next = {}
		self.end = False
