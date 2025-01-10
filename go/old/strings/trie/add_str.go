package trie

func AddStr(root *Node, str string) {
	cur := root
	for _, ch := range str {
		next := cur.Next[ch]
		if next == nil {
			next = &Node{
				Next: map[rune]*Node{},
			}
			cur.Next[ch] = next
		}
		cur = next
	}
	cur.Ending = true
}
