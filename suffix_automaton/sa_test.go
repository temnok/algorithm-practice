package suffix_automaton

import (
	"bytes"
	"encoding/json"
	"math/rand"
	"testing"
	"unsafe"
)

const (
	A = 0
	B = 1
	C = 2
)

var examples = []struct {
	str      string
	expected *Instance
}{
	{
		str: "A",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "AA",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{A: 2},
				},
				{ // 2: AA
					Len:  2,
					Link: 1,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "AABAB",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, B: 6},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{A: 2, B: 6},
				},
				{ // 2: AA
					Len:  2,
					Link: 1,
					Next: stateMap{0, B: 3},
				},
				{ // 3: AAB
					Len:  3,
					Link: 6,
					Next: stateMap{A: 4},
				},
				{ // 4: AABA, ABA, BA
					Len:  4,
					Link: 1,
					Next: stateMap{B: 5},
				},
				{ // 5: AABAB, ABAB, BAB
					Len:  5,
					Link: 6,
					Next: stateMap{},
				},
				{ // 6: AB, B
					Len:  2,
					Link: 0,
					Next: stateMap{A: 4},
				},
			},
		},
	},
	{
		str: "AB",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, B: 2},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{B: 2},
				},
				{ // 2: AB, B
					Len:  2,
					Link: 0,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "ABAB",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, B: 2},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{B: 2},
				},
				{ // 2: AB, B
					Len:  2,
					Link: 0,
					Next: stateMap{A: 3},
				},
				{ // 3: ABA, BA
					Len:  3,
					Link: 1,
					Next: stateMap{B: 4},
				},
				{ // 4: ABAB, BAB
					Len:  4,
					Link: 2,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "ABB",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, B: 4},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{B: 2},
				},
				{ // 2: AB
					Len:  2,
					Link: 4,
					Next: stateMap{B: 3},
				},
				{ // 3: ABB, BB
					Len:  3,
					Link: 4,
					Next: stateMap{},
				},
				{ // 4: B
					Len:  1,
					Link: 0,
					Next: stateMap{B: 3},
				},
			},
		},
	},
	{
		str: "ABBCBC",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, B: 4, C: 8},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{B: 2},
				},
				{ // 2: AB
					Len:  2,
					Link: 4,
					Next: stateMap{B: 3},
				},
				{ // 3: ABB, BB
					Len:  3,
					Link: 4,
					Next: stateMap{C: 5},
				},
				{ // 4: B
					Len:  1,
					Link: 0,
					Next: stateMap{B: 3, C: 8},
				},
				{ // 5: ABBC, BBC
					Len:  4,
					Link: 8,
					Next: stateMap{B: 6},
				},
				{ // 6: ABBCB, BBCB, BCB, CB
					Len:  5,
					Link: 4,
					Next: stateMap{C: 7},
				},
				{ // 7: ABBCBC, BBCBC, BCBC, CBC
					Len:  6,
					Link: 8,
					Next: stateMap{},
				},
				{ // 8: BC, C
					Len:  2,
					Link: 0,
					Next: stateMap{B: 6},
				},
			},
		},
	},
}

func TestExamples(t *testing.T) {
	for _, x := range examples {
		actual := fromString(t, x.str)
		if e, a := len(x.expected.states), len(actual.states); e != a {
			t.Errorf("Len(fromString(\"%v\").states)): \nExpected: %v\n  Actual: %v", x.str, e, a)
			continue
		}
		for j, expectedState := range x.expected.states {
			if e, a := expectedState, actual.states[j]; e != a {
				e, a := toJson(t, expectedState), toJson(t, actual.states[j])
				t.Errorf("fromString(\"%v\").states[%v]: \nExpected: %v\n  Actual: %v", x.str, j, e, a)
			}
		}
	}
}

func TestInstance_LastState(t *testing.T) {
	examples := []struct {
		str      string
		expected uint32
	}{
		{"", 0},
		{"A", 1},
		{"AA", 2},
		{"AABAB", 5},
		{"AB", 2},
		{"ABAB", 4},
		{"ABB", 3},
		{"ABBCBC", 7},
	}
	for _, x := range examples {
		if e, a := x.expected, fromString(t, x.str).lastState(); e != a {
			t.Errorf("fromString(\"%v\").lastState(): \nExpected: %v\n  Actual: %v", x.str, e, a)
		}
	}
}

func TestInstance_UniqueSubstringsCount(t *testing.T) {
	examples := []struct {
		str      string
		expected int
	}{
		{"", 0},
		{"A", 1},
		{"AA", 2},
		{"AABAB", 11},
		{"AB", 3},
		{"ABAB", 7},
		{"ABB", 5},
		{"ABBCBC", 17},
	}
	for _, x := range examples {
		if e, a := x.expected, fromString(t, x.str).UniqueSubstringsCount(); e != a {
			t.Errorf("fromString(\"%v\").UniqueSubstringsCount(): \nExpected: %v\n  Actual: %v", x.str, e, a)
		}
	}
}

func TestInstance_UniqueSubstringsCount_Randomized(t *testing.T) {
	r := rand.New(rand.NewSource(0))
	for x := 0; x < 1_000; x++ {
		buf := make([]byte, 50)
		for i, _ := range buf {
			buf[i] = byte('A' + r.Intn(4))
		}
		str, unique := string(buf), map[string]bool{}
		for i, _ := range str {
			for j := i + 1; j <= len(str); j++ {
				unique[str[i:j]] = true
			}
		}
		if e, a := len(unique), fromString(t, str).UniqueSubstringsCount(); e != a {
			t.Errorf("fromString(\"%v\").UniqueSubstringsCount(): \nExpected: %v\n  Actual: %v", str, e, a)
		}
	}
}

func TestSizeOfState(t *testing.T) {
	if e, a := uintptr(6*4), unsafe.Sizeof(state{}); e != a {
		t.Errorf("unsafe.Sizeof(state{}): \nExpected: %v\n  Actual: %v", e, a)
	}
	if e, a := uintptr(100*6*4), unsafe.Sizeof([100]state{}); e != a {
		t.Errorf("unsafe.Sizeof([100]state{}): \nExpected: %v\n  Actual: %v", e, a)
	}
}

func (in stateMap) MarshalJSON() ([]byte, error) {
	out := map[string]uint32{}
	for k, v := range in {
		out[string(rune('A'+k))] = v
	}
	return json.Marshal(out)
}

func toJson(t *testing.T, v interface{}) string {
	j, e := json.Marshal(v)
	if e != nil {
		t.Errorf("JSON marshalling failure for %#v: \n%v", v, e)
	}
	return string(j)
}

func fromString(t *testing.T, str string) *Instance {
	buf := []byte(str)
	for i, sym := range buf {
		buf[i] = sym - 'A'
	}
	sa, _ := FromReader(bytes.NewReader(buf))
	return sa
}
