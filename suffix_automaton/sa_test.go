package suffix_automaton

import (
	"encoding/json"
	"math/rand"
	"testing"
)

var examples = []struct {
	str      string
	expected *Instance
}{
	{
		str: "a",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "aa",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{'a': 2},
				},
				{ // 2: aa
					Len:  2,
					Link: 1,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "aabab",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1, 'b': 6},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{'a': 2, 'b': 6},
				},
				{ // 2: aa
					Len:  2,
					Link: 1,
					Next: stateMap{'b': 3},
				},
				{ // 3: aab
					Len:  3,
					Link: 6,
					Next: stateMap{'a': 4},
				},
				{ // 4: aaba, aba, ba
					Len:  4,
					Link: 1,
					Next: stateMap{'b': 5},
				},
				{ // 5: aabab, abab, bab
					Len:  5,
					Link: 6,
					Next: stateMap{},
				},
				{ // 6: ab, b
					Len:  2,
					Link: 0,
					Next: stateMap{'a': 4},
				},
			},
		},
	},
	{
		str: "ab",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1, 'b': 2},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{'b': 2},
				},
				{ // 2: ab, b
					Len:  2,
					Link: 0,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "abab",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1, 'b': 2},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{'b': 2},
				},
				{ // 2: ab, b
					Len:  2,
					Link: 0,
					Next: stateMap{'a': 3},
				},
				{ // 3: aba, ba
					Len:  3,
					Link: 1,
					Next: stateMap{'b': 4},
				},
				{ // 4: abab, bab
					Len:  4,
					Link: 2,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "abb",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1, 'b': 4},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{'b': 2},
				},
				{ // 2: ab
					Len:  2,
					Link: 4,
					Next: stateMap{'b': 3},
				},
				{ // 3: abb, bb
					Len:  3,
					Link: 4,
					Next: stateMap{},
				},
				{ // 4: b
					Len:  1,
					Link: 0,
					Next: stateMap{'b': 3},
				},
			},
		},
	},
	{
		str: "abbcbc",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{'a': 1, 'b': 4, 'c': 8},
				},
				{ // 1: a
					Len:  1,
					Link: 0,
					Next: stateMap{'b': 2},
				},
				{ // 2: ab
					Len:  2,
					Link: 4,
					Next: stateMap{'b': 3},
				},
				{ // 3: abb, bb
					Len:  3,
					Link: 4,
					Next: stateMap{'c': 5},
				},
				{ // 4: b
					Len:  1,
					Link: 0,
					Next: stateMap{'b': 3, 'c': 8},
				},
				{ // 5: abbc, bbc
					Len:  4,
					Link: 8,
					Next: stateMap{'b': 6},
				},
				{ // 6: abbcb, bbcb, bcb, cb
					Len:  5,
					Link: 4,
					Next: stateMap{'c': 7},
				},
				{ // 7: abbcbc, bbcbc, bcbc, cbc
					Len:  6,
					Link: 8,
					Next: stateMap{},
				},
				{ // 8: bc, c
					Len:  2,
					Link: 0,
					Next: stateMap{'b': 6},
				},
			},
		},
	},
}

func TestExamples(t *testing.T) {
	for _, x := range examples {
		expected, actual := x.expected, FromString(x.str)
		if e, a := len(expected.states), len(actual.states); e != a {
			t.Errorf("Len(FromString(\"%v\").states)): \nExpected: %v\n  Actual: %v", x.str, e, a)
			continue
		}
		for j, expectedState := range expected.states {
			if e, a := toJson(t, expectedState), toJson(t, actual.states[j]); e != a {
				t.Errorf("FromString(\"%v\").states[%v]: \nExpected: %v\n  Actual: %v", x.str, j, e, a)
			}
		}
	}
}

func TestInstance_LastState(t *testing.T) {
	examples := []struct {
		str      string
		expected int
	}{
		{"", 0},
		{"a", 1},
		{"aa", 2},
		{"aabab", 5},
		{"ab", 2},
		{"abab", 4},
		{"abb", 3},
		{"abbcbc", 7},
	}
	for _, x := range examples {
		if e, a := x.expected, FromString(x.str).lastState(); e != a {
			t.Errorf("FromString(\"%v\").lastState(): \nExpected: %v\n  Actual: %v", x.str, e, a)
		}
	}
}

func TestInstance_UniqueSubstringsCount(t *testing.T) {
	examples := []struct {
		str      string
		expected int
	}{
		{"", 0},
		{"a", 1},
		{"aa", 2},
		{"aabab", 11},
		{"ab", 3},
		{"abab", 7},
		{"abb", 5},
		{"abbcbc", 17},
	}
	for _, x := range examples {
		if e, a := x.expected, FromString(x.str).UniqueSubstringsCount(); e != a {
			t.Errorf("FromString(\"%v\").UniqueSubstringsCount(): \nExpected: %v\n  Actual: %v", x.str, e, a)
		}
	}
}

func TestInstance_UniqueSubstringsCount_Randomized(t *testing.T) {
	r := rand.New(rand.NewSource(0))
	for x := 0; x < 1000; x++ {
		buf := make([]byte, 3+r.Intn(50))
		alphabetSize := 1 + r.Intn(26)
		for i, _ := range buf {
			buf[i] = byte('a' + r.Intn(alphabetSize))
		}
		str, unique := string(buf), map[string]bool{}
		for i, _ := range str {
			for j := i + 1; j <= len(str); j++ {
				unique[str[i:j]] = true
			}
		}
		if e, a := len(unique), FromString(str).UniqueSubstringsCount(); e != a {
			t.Errorf("FromString(\"%v\").UniqueSubstringsCount(): \nExpected: %v\n  Actual: %v", str, e, a)
		}
	}
}

func (in stateMap) MarshalJSON() ([]byte, error) {
	out := map[string]int{}
	for k, v := range in {
		out[string(k)] = v
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
