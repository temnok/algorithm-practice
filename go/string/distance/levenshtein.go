package distance

// Levenshtein should return the minimum number of single-character edits
// required to transform string a into b or vice versa. Edits are insertions,
// deletions or replacing once character with another. Examples:
//
// Levenshtein("aaa", "aa") must return 1
// Levenshtein("kitten", "sitting") must return 3
//
func Levenshtein(a, b string) int {
	m, n := len(a), len(b)
	prev, cur := make([]int, n+1), make([]int, n+1)
	for j := range prev {
		prev[j] = j
	}

	for i := range m {
		cur[0] = i+1
		for j := range n {
			best := prev[j]
			if a[i] != b[j] {
				best++
			}

			if d := prev[j+1]+1; d < best {
				best = d
			}

			if d := cur[j]+1; d < best {
				best = d
			}

			cur[j+1] = best
		}

		prev, cur = cur, prev
	}

	return prev[n]
}
