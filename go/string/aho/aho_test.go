package aho

import (
	"github.com/stretchr/testify/assert"
	"math/rand/v2"
	"reflect"
	"strings"
	"testing"
)

func TestContainsSingle(t *testing.T) {
	assert.Equal(t, []bool{true}, Contains("ab", []string{"a"}))
	assert.Equal(t, []bool{true}, Contains("ab", []string{"ab"}))
	assert.Equal(t, []bool{true}, Contains("ab", []string{"b"}))
	assert.Equal(t, []bool{true}, Contains("abc", []string{"bc"}))
	assert.Equal(t, []bool{true}, Contains("abc", []string{"c"}))
}

func TestContainsExamples(t *testing.T) {
	tests := []struct {
		text string
		strs []string
		want []bool
	}{
		{
			text: "xxxabc",
			strs: []string{"abc", "bc", "c"},
			want: []bool{true, true, true},
		},
		{
			text: "ababa",
			strs: []string{"baba", "ab", "ababa", "a", "b", "babab"},
			want: []bool{true, true, true, true, true, false},
		},
		{
			text: "babb",
			strs: []string{"baba", "ab"},
			want: []bool{false, true},
		},
		{
			text: "ahishers",
			strs: []string{"he", "she", "hers", "his", "him"},
			want: []bool{true, true, true, true, false},
		},
		{
			text: "cbbacbbdb",
			strs: []string{"bbbaabbbaaaaaaabaab", "aabbab", "bbb", "abbaaa", "bbababaabaaabaaa", "aabb",
				"a", "bbaba", "ba", "aaabaabbbbbbbbabbb"},
			want: []bool{false, false, false, false, false, false, true, false, true, false},
		},
	}

	for _, test := range tests {
		got := Contains(test.text, test.strs)
		if !reflect.DeepEqual(got, test.want) {
			t.Fatalf("Contains(%v, %v):\nwant %v\n got %v", test.text, test.strs, test.want, got)
		}
	}
}

func TestFindEndingsRandom(t *testing.T) {
	random := rand.New(rand.NewPCG(0, 0))
	const max = 20

	for test := 0; test < 10_000; test++ {
		text := generateString(random, max, 1+random.IntN(26))
		strs := make([]string, 1+random.IntN(max))
		m := 1 + random.IntN(26)
		for i, _ := range strs {
			strs[i] = generateString(random, max, m)
		}

		strs = removeDuplicates(strs)
		got, want := Contains(text, strs), containsBruteForce(text, strs)
		if !reflect.DeepEqual(got, want) {
			t.Fatalf("Contains(%v, %v):\nwant %v\n got %v", text, strs, want, got)
		}
	}
}

func containsBruteForce(text string, strs []string) (ans []bool) {
	for _, str := range strs {
		ans = append(ans, strings.Contains(text, str))
	}
	return
}

func generateString(random *rand.Rand, n, m int) string {
	str := make([]byte, 1+random.IntN(n))
	for i, _ := range str {
		str[i] = byte('a' + random.IntN(m))
	}
	return string(str)
}

func removeDuplicates(strs []string) (out []string) {
	known := map[string]bool{}
	for _, str := range strs {
		if !known[str] {
			out, known[str] = append(out, str), true
		}
	}
	return
}
