package splay_tree

import (
	"github.com/temnok/algorithms-practice/go/set"
)

type SplayTree struct {
	root *Node
}

// NewSplayTree should return initialized splay tree instance
func NewSplayTree() set.Set {
	return &SplayTree{}
}

// Add should insert given val into this splay tree
// and return true if list did not contain val previously,
// or do nothing and return false if val is already present
func (s *SplayTree) Add(val int) bool {
	if s.Contains(val) {
		return false
	}

	c := &Node{val: val}
	if s.root == nil {
		s.root = c
		return true
	}

	if s.root.val < val {
		c.left = s.root
		c.right = s.root.right
		s.root.right = nil
		if c.right != nil {
			c.right.parent = c
		}
	} else {
		c.right = s.root
		c.left = s.root.left
		s.root.left = nil
		if c.left != nil {
			c.left.parent = c
		}
	}

	s.root.parent = c
	s.root = c
	return true
}

// Contains should return true if val is present in the tree
func (s *SplayTree) Contains(val int) bool {
	c := s.root
	if c == nil {
		return false
	}

	for {
		if val < c.val && c.left != nil {
			c = c.left
		} else if c.val < val && c.right != nil {
			c = c.right
		} else {
			break
		}
	}

	c.splay()
	s.root = c
	return c.val == val
}

// Remove should delete val in the tree and return true if val was present
// or do nothing and return false otherwise
func (s *SplayTree) Remove(val int) bool {
	if !s.Contains(val) {
		return false
	}

	if r := s.root.right; r == nil {
		if s.root = s.root.left; s.root != nil {
			s.root.parent = nil
		}
	} else {
		for r.left != nil {
			r = r.left
		}
		r.splay()
		s.root = r

		r.left = r.left.left
		if r.left != nil {
			r.left.parent = r
		}
	}

	return true
}

type Node struct {
	val                 int
	parent, left, right *Node
}

func (c *Node) splay() {
	for c.parent != nil {
		p := c.parent

		if g := p.parent; g != nil {
			if (c == p.left) == (p == g.left) {
				p.rotate()
			} else {
				c.rotate()
			}
		}

		c.rotate()
	}
}

func (c *Node) rotate() {
	p := c.parent

	var m *Node
	if c == p.left {
		m = c.right
		c.right = p
		p.left = m
	} else {
		m = c.left
		c.left = p
		p.right = m
	}

	if m != nil {
		m.parent = p
	}

	g := p.parent
	c.parent = g
	p.parent = c

	if g != nil {
		if p == g.left {
			g.left = c
		} else {
			g.right = c
		}
	}
}
