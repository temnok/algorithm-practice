package disjoint_set

type DisjointSet struct {
	// TODO
}

// DisjointSet constructor should initialize a disjoint set
func NewDisjointSet(n int) *DisjointSet {
	panic("TODO")
}

// Union should return false if elements i and j are already in the same set
// or perform Union operation on their sets and return true
func (set *DisjointSet) Union(i, j int) bool {
	panic("TODO")
}

// Find should return set ID: if Find(x) == Find(y), then both x and y are in the same set
func (set *DisjointSet) Find(i int) int {
	panic("TODO")
}
