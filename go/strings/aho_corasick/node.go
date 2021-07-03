package aho_corasick

type Node struct {
	Next       map[rune]*Node
	Ending     bool
	SuffixLink *Node
}
