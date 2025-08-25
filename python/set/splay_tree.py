from set import Set


class Node:
	def __init__(self, val):
		self.val = val
		self.parent = None
		self.left = None
		self.right = None

	def splay(self):
		while self.parent is not None:
			p = self.parent
			g = p.parent

			if g is not None:
				if (self == p.left) == (p == g.left):
					p.rotate()
				else:
					self.rotate()

			self.rotate()

	def rotate(self):
		p = self.parent
		if p is None:
			return

		g = p.parent
		if g is not None:
			if p == g.left:
				g.left = self
			else:
				g.right = self

		if self == p.left:
			m = self.right
			self.right = p
			p.left = m
		else:
			m = self.left
			self.left = p
			p.right = m

		self.parent = g
		p.parent = self
		if m is not None:
			m.parent = p

	def __str__(self):
		l = ''
		if self.left is not None:
			l = self.left.__str__()

		r = ''
		if self.right is not None:
			r = self.right.__str__()

		return f'({l}{self.val}{r})'


class SplayTree(Set):
	# __init__ should return initialized splay tree instance
	def __init__(self):
		# raise NotImplementedError('TODO')

		self.root = None

	# add should insert given val into this splay tree
	# and return true if list did not contain val previously,
	# or do nothing and return false if val is already present
	def add(self, val: int) -> bool:
		# raise NotImplementedError('TODO')

		if self.root is None:
			self.root = Node(val)
			return True

		if self.contains(val):
			return False

		c = self.root
		p = Node(val)
		self.root = p

		if c.val < val:
			m = c.right
			c.right = None

			p.left = c
			p.right = m
		else:
			m = c.left
			c.left = None

			p.right = c
			p.left = m

		c.parent = p
		if m is not None:
			m.parent = p

		return True

	# contains should return true if val is present in the tree
	def contains(self, val: int) -> bool:
		# raise NotImplementedError('TODO')

		c = self.root
		if c is None:
			return False

		while c.val != val:
			if val < c.val:
				if c.left is not None:
					c = c.left
				else:
					break
			else:
				if c.right is not None:
					c = c.right
				else:
					break

		c.splay()
		self.root = c
		return c.val == val

	# remove should delete val in the tree and return true if val was present
	# or do nothing and return false otherwise
	def remove(self, val: int) -> bool:
		# raise NotImplementedError('TODO')

		if not self.contains(val):
			return False

		p = self.root
		r = p.right
		if r is None:
			self.root = p.left
			if self.root is not None:
				self.root.parent = None
			return True

		while r.left is not None:
			r = r.left

		r.splay()
		self.root = r
		r.left = p.left
		if r.left is not None:
			r.left.parent = r

		return True

	def __str__(self):
		if self.root is None:
			return '()'
		else:
			return self.root.__str__()
