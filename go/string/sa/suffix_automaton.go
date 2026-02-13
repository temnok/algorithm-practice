package sa

type SuffixAutomaton struct {
}

func NewSuffixAutomaton(str string) *SuffixAutomaton {
	return &SuffixAutomaton{}
}

func (sa *SuffixAutomaton) ContainsSubstring(substr string) bool {
	return false
}
