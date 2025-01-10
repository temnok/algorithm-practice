package aho_corasick

func AssignSuffixLinks(root *Node) {
	queue := []*Node{root}
	for len(queue) > 0 {
		cur := queue[0]
		queue = queue[1:]
	next:
		for c, next := range cur.Next {
			queue = append(queue, next)
			for s := cur.SuffixLink; s != nil; s = s.SuffixLink {
				if sc := s.Next[c]; sc != nil {
					next.SuffixLink = sc
					if sc.Ending {
						next.Ending = true
					}
					continue next
				}
			}
			next.SuffixLink = root
		}
	}
}
