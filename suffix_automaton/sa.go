package suffix_automaton

type Instance struct {
	states []state
}

type stateMap map[rune]int

type state struct {
	Len, Link int
	Next      stateMap
}

func FromString(str string) *Instance {
	sa := create()
	for _, sym := range str {
		sa.addSymbol(sym)
	}
	return sa
}

func create() *Instance {
	return &Instance{
		states: []state{
			{
				Next: make(stateMap),
			},
		},
	}
}

func (sa *Instance) addSymbol(sym rune) {
	p := sa.lastState()
	s, cur := sa.appendState(state{
		Len:  sa.states[p].Len + 1,
		Link: 0,
		Next: make(stateMap),
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
		Next: make(stateMap),
	})
	for k, v := range s[q].Next {
		s[clone].Next[k] = v
	}
	s[q].Link, s[cur].Link = clone, clone
	for ; s[p].Next[sym] == q; p = s[p].Link {
		s[p].Next[sym] = clone
	}
}

func (sa *Instance) appendState(s state) ([]state, int) {
	sa.states = append(sa.states, s)
	return sa.states, len(sa.states) - 1
}

func (sa *Instance) lastState() int {
	if i := len(sa.states) - 1; i > 0 && sa.states[i-1].Len > sa.states[i].Len {
		return i - 1
	} else {
		return i
	}
}

func (sa *Instance) UniqueSubstringsCount() (count int) {
	for _, s := range sa.states[1:] {
		count += s.Len - sa.states[s.Link].Len
	}
	return
}
