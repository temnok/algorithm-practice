package trie

// HasPrefix should return true if there is a string in dict that is a prefix of another string in dict.
// All strings in dict are expected to consist of low-case latin letters only, 'a'..'z'.
func HasPrefix(dict []string) bool {
	root := &Node{}

	for _, word := range dict {
		cur := root

		for i, c := range word {
			next := cur.next[c]

			if next == nil {
				next = &Node{end: i == len(word)-1}
				if cur.next == nil {
					cur.next = map[rune]*Node{c: next}
				} else {
					cur.next[c] = next
				}
			} else if next.end || i == len(word)-1 {
				return true
			}

			cur = next
		}
	}

	return false
}

type Node struct {
	end  bool
	next map[rune]*Node
}
