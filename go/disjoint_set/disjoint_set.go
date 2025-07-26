package disjoint_set

type DisjointSet struct {
	parent, size []int
}

// DisjointSet constructor should initialize a disjoint set
func newDisjointSet(n int) *DisjointSet {
	panic("TODO")
}

// union should return false if elements i and j are already in the same set
// or perform union operation on their sets and return true
func (set *DisjointSet) union(i, j int) bool {
	panic("TODO")
}

// find should return set ID: if find(x) == find(y), then both x and y are in the same set
func (set *DisjointSet) find(i int) int {
	panic("TODO")
}
