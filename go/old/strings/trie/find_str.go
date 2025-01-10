package trie

func FindStr(root *Node, str string) bool {
	cur := root
	for _, ch := range str {
		next := cur.Next[ch]
		if next == nil {
			return false
		}
		cur = next
	}
	return cur.Ending
}
