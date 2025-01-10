package trie

type Node struct {
	Next   map[rune]*Node
	Ending bool
}
