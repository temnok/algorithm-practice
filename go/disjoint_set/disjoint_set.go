package disjoint_set

type DisjointSet struct {
	parent, size []int
}

// DisjointSet constructor should initialize a disjoint set
func newDisjointSet(n int) *DisjointSet {
	set := &DisjointSet{
		parent: make([]int, n),
		size:   make([]int, n),
	}

	for i := range n {
		set.parent[i] = i
		set.size[i] = 1
	}

	return set
}

// union should return false if elements i and j are already in the same set
// or perform union operation on their sets and return true
func (set *DisjointSet) union(i, j int) bool {
	i, j = set.find(i), set.find(j)
	if i == j {
		return false
	}

	if set.size[i] > set.size[j] {
		i, j = j, i
	}

	set.parent[i] = j
	set.size[j] += set.size[i]

	return true
}

// find should return set ID: if find(x) == find(y), then both x and y are in the same set
func (set *DisjointSet) find(i int) int {
	for i != set.parent[i] {
		p := set.parent[i]
		set.parent[i] = set.parent[p]
		i = p
	}

	return i
}
