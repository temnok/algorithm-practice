package skiplist

import "github.com/temnok/algorithms-practice/go/set"

type SkipList struct {
	// TODO
}

// NewSkipList should return initialized skip list instance
func NewSkipList() set.Set {
	panic("TODO")
}

// Add should insert given val into this skip list
// and return true if list did not contain val previously,
// or do nothing and return false if val is already present
func (s *SkipList) Add(val int) bool {
	panic("TODO")
}

// Contains should return true if val is present in the list
func (s *SkipList) Contains(val int) bool {
	panic("TODO")
}

// Remove should delete val in the list and return true if val was present
// or do nothing and return false otherwise
func (s *SkipList) Remove(val int) bool {
	panic("TODO")
}
