package skiplist

import (
	"github.com/temnok/algorithms-practice/go/set"
	"math/bits"
	"math/rand/v2"
)

type SkipList struct {
	root *Node
}

// NewSkipList should return initialized skip list instance
func NewSkipList() set.Set {
	return &SkipList{
		root: &Node{
			next: []*Node{nil},
		},
	}
}

// Add should insert given val into this skip list
// and return true if list did not contain val previously,
// or do nothing and return false if val is already present
func (s *SkipList) Add(val int) bool {
	prev := s.findPrev(val)
	if cur := prev[0].next[0]; cur != nil && cur.val == val {
		return false
	}

	h := 1 + bits.TrailingZeros(rand.Uint())
	cur := &Node{
		val:  val,
		next: make([]*Node, h),
	}

	for i := 0; i < len(prev) && i < h; i++ {
		cur.next[i] = prev[i].next[i]
		prev[i].next[i] = cur
	}

	for i := len(prev); i < h; i++ {
		s.root.next = append(s.root.next, cur)
	}

	return true
}

// Contains should return true if val is present in the list
func (s *SkipList) Contains(val int) bool {
	cur := s.findPrev(val)[0].next[0]
	return cur != nil && cur.val == val
}

// Remove should delete val in the list and return true if val was present
// or do nothing and return false otherwise
func (s *SkipList) Remove(val int) bool {
	prev := s.findPrev(val)
	cur := prev[0].next[0]
	if cur == nil || cur.val != val {
		return false
	}

	for i := 0; i < len(cur.next); i++ {
		prev[i].next[i] = cur.next[i]
	}

	return true
}

func (s *SkipList) findPrev(val int) []*Node {
	prev := make([]*Node, len(s.root.next))
	cur := s.root
	for i := len(prev) - 1; i >= 0; i-- {
		for cur.next[i] != nil && cur.next[i].val < val {
			cur = cur.next[i]
		}
		prev[i] = cur
	}
	return prev
}

type Node struct {
	val  int
	next []*Node
}
