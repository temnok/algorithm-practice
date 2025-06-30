package trie

// hasPrefix should return true if there is a string in dict that is a prefix of another string in dict.
// All strings in dict are expected to consist of low-case latin letters only, 'a'..'z'.
func hasPrefix(dict []string) bool {
	root := &Node{}

	for _, str := range dict {
		p := root

		for i, ch := range str {
			n := p.next[ch]

			if n == nil {
				if p.next == nil {
					p.next = map[rune]*Node{}
				}
				n = &Node{}
				p.next[ch] = n
			} else if n.end || i == len(str)-1 {
				return true
			}

			p = n
		}

		p.end = true
	}

	return false
}

type Node struct {
	end  bool
	next map[rune]*Node
}
