package sa

import (
	"github.com/temnok/algorithms-practice/go/string"
	"math/rand/v2"
	"strings"
	"testing"
)

func TestContains(t *testing.T) {
	rand := rand.New(&rand.PCG{})

	for range 1_000 {
		str := string.RandomString(rand, 1_000)
		sa := NewSuffixAutomaton(str)

		for range 1_000 {
			substr := string.RandomString(rand, 100)

			got := sa.ContainsSubstring(substr)
			want := strings.Contains(str, substr)
			if got != want {
				t.Fatalf("NewSuffixAutomaton(%q)\n.ContainsSubstring(%q):\nwant %v\n got %v", str, substr, want, got)
			}
		}
	}
}
