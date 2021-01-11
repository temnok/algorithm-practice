package suffix_automaton

type Instance struct {
	states []state
}

type stateMap [4]uint32

type state struct {
	Len, Link uint32
	Next      stateMap
}

func New() *Instance {
	return &Instance{states: []state{{}}}
}

func (sa *Instance) SetCapacity(c int) {
	if cap(sa.states) < c {
		s := make([]state, len(sa.states), c)
		copy(s, sa.states)
		sa.states = s
	}
}

func (sa *Instance) Append(str []byte) int {
	alphabetSize, errors := byte(len(stateMap{})), 0
	for _, sym := range str {
		if sym >= alphabetSize {
			errors++
			continue
		}
		sa.add(sym)
	}
	return errors
}

func (sa *Instance) add(sym byte) {
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

func (sa *Instance) DistinctSubstringsCount() (count int) {
	for _, s := range sa.states[1:] {
		count += int(s.Len) - int(sa.states[s.Link].Len)
	}
	return
}
