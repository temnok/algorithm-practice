package dna_automaton

const (
	A = 0
	T = 1
	C = 2
	G = 3
)

type Instance struct {
	states []state
}

type stateMap [4]uint32

type state struct {
	Len, Link uint32
	Next      stateMap
}

func FromString(str string) *Instance {
	sa := create()
	for _, sym := range str {
		sa.addSymbol(ATCGtoInt(sym))
	}
	return sa
}

func ATCGtoInt(c rune) int {
	switch c {
	case 'A', 'a':
		return 0
	case 'T', 't':
		return 1
	case 'C', 'c':
		return 2
	case 'G', 'g':
		return 3
	default:
		return -1
	}
}

func IntToATCG(i int) byte {
	return "ATCG"[i]
}

func create() *Instance {
	return &Instance{
		states: []state{
			{},
		},
	}
}

func (sa *Instance) addSymbol(sym int) {
	p := sa.lastState()
	s, cur := sa.appendState(state{
		Len:  sa.states[p].Len + 1,
		Link: 0,
	})
	for ; s[p].Next[sym] == 0; p = s[p].Link {
		s[p].Next[sym] = cur
		if p == 0 {
			return
		}
	}
	q := s[p].Next[sym]
	if s[p].Len+1 == s[q].Len {
		s[cur].Link = q
		return
	}
	s, clone := sa.appendState(state{
		Len:  s[p].Len + 1,
		Link: s[q].Link,
		Next: s[q].Next,
	})
	s[q].Link, s[cur].Link = clone, clone
	for ; s[p].Next[sym] == q; p = s[p].Link {
		s[p].Next[sym] = clone
	}
}

func (sa *Instance) appendState(s state) ([]state, uint32) {
	sa.states = append(sa.states, s)
	return sa.states, uint32(len(sa.states) - 1)
}

func (sa *Instance) lastState() uint32 {
	if i := uint32(len(sa.states)) - 1; i > 0 && sa.states[i-1].Len > sa.states[i].Len {
		return i - 1
	} else {
		return i
	}
}

func (sa *Instance) UniqueSubstringsCount() (count int) {
	for _, s := range sa.states[1:] {
		count += int(s.Len) - int(sa.states[s.Link].Len)
	}
	return
}
