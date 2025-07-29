package disjoint_set

type DisjointSet struct {
	parent, size []int
}

// DisjointSet constructor should initialize a disjoint set
func NewDisjointSet(n int) *DisjointSet {
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

// Union should return false if elements i and j are already in the same set
// or perform Union operation on their sets and return true
func (set *DisjointSet) Union(i, j int) bool {
	i, j = set.Find(i), set.Find(j)
	if i == j {
		return false
	}

	if set.size[i] < set.size[j] {
		set.parent[i] = j
		set.size[j] += set.size[i]
	} else {
		set.parent[j] = i
		set.size[i] += set.size[j]
	}

	return true
}

// Find should return set ID: if Find(x) == Find(y), then both x and y are in the same set
func (set *DisjointSet) Find(i int) int {
	for i != set.parent[i] {
		p := set.parent[i]
		set.parent[i] = set.parent[p]
		i = p
	}

	return i
}
