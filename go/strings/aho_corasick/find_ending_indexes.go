package aho_corasick

func FindEndingIndexes(root *Node, str string) (result []int) {
	cur := root
ch:
	for i, ch := range str {
		for ; cur != nil; cur = cur.SuffixLink {
			if next := cur.Next[ch]; next != nil {
				cur = next
				if cur.Ending {
					result = append(result, i)
				}
				continue ch
			}
		}
		cur = root
	}
	return
}
