package regex

// MatchesFully should return true if str *fully* matches given regular expression pattern.
// The pattern can contain lowercase latin letters and symbols '.', '?', '+', '*' with
// the standard regexp behavior, as for java.util.regex.Pattern.
func MatchesFully(str, pattern string) bool {
	n := len(str)
	m := len(pattern)
	state := make([]bool, m+1)
	nextState := make([]bool, m+1)

	state[0] = true
	for i := 0; ; i++ {
		for j := 0; j < m; j++ {
			if p := pattern[j]; (p == '?' || p == '*') && state[j-1] {
				state[j+1] = true
			}
		}

		if i == n {
			return state[m]
		}

		for j := 0; j < m; j++ {
			if p := pattern[j]; (p == '.' || p == str[i]) && state[j] {
				if j+1 < m {
					if p = pattern[j+1]; p == '+' || p == '*' {
						nextState[j] = true
						j++
					} else if p == '?' {
						j++
					}
				}

				nextState[j+1] = true
			}
		}

		state, nextState = nextState, state
		clear(nextState)
	}
}
