package sa

import "maps"

type SuffixAutomaton struct {
	root, last *node
}

type node struct {
	len  int
	link *node
	next map[byte]*node
}

func NewSuffixAutomaton(str string) *SuffixAutomaton {
	sa := &SuffixAutomaton{
		root: &node{},
	}
	sa.last = sa.root

	for _, ch := range []byte(str) {
		sa.add(ch)
	}

	return sa
}

func (sa *SuffixAutomaton) add(ch byte) {
	cur := &node{len: sa.last.len + 1}

	p := sa.last
	for ; p != nil && p.next[ch] == nil; p = p.link {
		if p.next == nil {
			p.next = map[byte]*node{ch: cur}
		} else {
			p.next[ch] = cur
		}
	}

	sa.last = cur
	if p == nil {
		cur.link = sa.root
		return
	}

	q := p.next[ch]
	if q.len == p.len+1 {
		cur.link = q
		return
	}

	clone := &node{
		len:  p.len + 1,
		link: q.link,
		next: maps.Clone(q.next),
	}
	cur.link, q.link = clone, clone

	for ; p != nil && p.next[ch] == q; p = p.link {
		p.next[ch] = clone
	}
}

func (sa *SuffixAutomaton) ContainsSubstring(substr string) bool {
	p := sa.root

	for _, ch := range []byte(substr) {
		if p = p.next[ch]; p == nil {
			return false
		}
	}

	return true
}
