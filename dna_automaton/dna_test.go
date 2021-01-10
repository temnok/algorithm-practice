package dna_automaton

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
		str: "AATAT",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, T: 6},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{A: 2, T: 6},
				},
				{ // 2: AA
					Len:  2,
					Link: 1,
					Next: stateMap{0, T: 3},
				},
				{ // 3: AAT
					Len:  3,
					Link: 6,
					Next: stateMap{A: 4},
				},
				{ // 4: AATA, ATA, TA
					Len:  4,
					Link: 1,
					Next: stateMap{T: 5},
				},
				{ // 5: AATAT, ATAT, TAT
					Len:  5,
					Link: 6,
					Next: stateMap{},
				},
				{ // 6: AT, T
					Len:  2,
					Link: 0,
					Next: stateMap{A: 4},
				},
			},
		},
	},
	{
		str: "AT",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, T: 2},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{T: 2},
				},
				{ // 2: AT, T
					Len:  2,
					Link: 0,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "ATAT",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, T: 2},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{T: 2},
				},
				{ // 2: AT, T
					Len:  2,
					Link: 0,
					Next: stateMap{A: 3},
				},
				{ // 3: ATA, TA
					Len:  3,
					Link: 1,
					Next: stateMap{T: 4},
				},
				{ // 4: ATAT, TAT
					Len:  4,
					Link: 2,
					Next: stateMap{},
				},
			},
		},
	},
	{
		str: "ATT",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, T: 4},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{T: 2},
				},
				{ // 2: AT
					Len:  2,
					Link: 4,
					Next: stateMap{T: 3},
				},
				{ // 3: ATT, TT
					Len:  3,
					Link: 4,
					Next: stateMap{},
				},
				{ // 4: T
					Len:  1,
					Link: 0,
					Next: stateMap{T: 3},
				},
			},
		},
	},
	{
		str: "ATTCTC",
		expected: &Instance{
			states: []state{
				{ // 0
					Len:  0,
					Link: 0,
					Next: stateMap{A: 1, T: 4, C: 8},
				},
				{ // 1: A
					Len:  1,
					Link: 0,
					Next: stateMap{T: 2},
				},
				{ // 2: AT
					Len:  2,
					Link: 4,
					Next: stateMap{T: 3},
				},
				{ // 3: ATT, TT
					Len:  3,
					Link: 4,
					Next: stateMap{C: 5},
				},
				{ // 4: T
					Len:  1,
					Link: 0,
					Next: stateMap{T: 3, C: 8},
				},
				{ // 5: ATTC, TTC
					Len:  4,
					Link: 8,
					Next: stateMap{T: 6},
				},
				{ // 6: ATTCT, TTCT, TCT, CT
					Len:  5,
					Link: 4,
					Next: stateMap{C: 7},
				},
				{ // 7: ATTCTC, TTCTC, TCTC, CTC
					Len:  6,
					Link: 8,
					Next: stateMap{},
				},
				{ // 8: TC, C
					Len:  2,
					Link: 0,
					Next: stateMap{T: 6},
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
		expected uint32
	}{
		{"", 0},
		{"A", 1},
		{"AA", 2},
		{"AATAT", 5},
		{"AT", 2},
		{"ATAT", 4},
		{"ATT", 3},
		{"ATTCTC", 7},
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
		{"A", 1},
		{"AA", 2},
		{"AATAT", 11},
		{"AT", 3},
		{"ATAT", 7},
		{"ATT", 5},
		{"ATTCTC", 17},
	}
	for _, x := range examples {
		if e, a := x.expected, FromString(x.str).UniqueSubstringsCount(); e != a {
			t.Errorf("FromString(\"%v\").UniqueSubstringsCount(): \nExpected: %v\n  Actual: %v", x.str, e, a)
		}
	}
}

func TestInstance_UniqueSubstringsCount_Randomized(t *testing.T) {
	r := rand.New(rand.NewSource(0))
	alphabet := "ATCG"
	for x := 0; x < 1000; x++ {
		buf := make([]byte, 3+r.Intn(50))
		alphabetSize := 1 + r.Intn(len(alphabet))
		for i, _ := range buf {
			buf[i] = alphabet[r.Intn(alphabetSize)]
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
	out := map[string]uint32{}
	for k, v := range in {
		out[string(IntToATCG(k))] = v
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
