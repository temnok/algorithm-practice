package aho

type node struct {
	next   map[rune]*node
	strEnd bool
	strI   int

	suffix *node
}

// Contains must implement Aho-Corasick algorithm.
// Returned array should contain true at position i if strs[i] is found in text, false otherwise.
// - strs contains unique non-empty strings
func Contains(text string, strs []string) []bool {
	root := buildTrie(strs)
	findSuffixes(root)

	ans := make([]bool, len(strs))

	cur := root
	for _, char := range text {
		for cur != nil && cur.next[char] == nil {
			cur = cur.suffix
		}

		if cur == nil {
			cur = root
			continue
		}

		cur = cur.next[char]
		for suffix := cur; suffix.strEnd && !ans[suffix.strI]; suffix = suffix.suffix {
			ans[suffix.strI] = true
		}
	}

	return ans
}

func buildTrie(strs []string) *node {
	root := &node{}

	for i, str := range strs {
		cur := root

		for _, char := range str {
			next := cur.next[char]

			if next == nil {
				next = &node{}

				if cur.next == nil {
					cur.next = map[rune]*node{char: next}
				} else {
					cur.next[char] = next
				}
			}

			cur = next
		}

		cur.strEnd, cur.strI = true, i
	}

	return root
}

func findSuffixes(root *node) {
	q := []*node{root}

	for i := 0; i < len(q); i++ {
		cur := q[i]

		for char, next := range cur.next {
			q = append(q, next)

			s := cur.suffix
			for s != nil && s.next[char] == nil {
				s = s.suffix
			}

			if s == nil {
				next.suffix = root
				continue
			}

			next.suffix = s.next[char]
			if !next.strEnd && next.suffix.strEnd {
				next.strEnd, next.strI = true, next.suffix.strI
			}
		}
	}
}
