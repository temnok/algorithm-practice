
# matches_fully should return true if str *fully* matches given regular expression pattern.
# The pattern can contain lowercase latin letters and symbols '.', '?', '+', '*' with
# the standard regexp behavior, as for java.util.regex.Pattern.
def matches_fully(s: str, pattern: str) -> bool:
	# raise NotImplementedError('TODO')

	m, n = len(s), len(pattern)

	state = [False]*(n + 1)
	state[0] = True

	for i in range(m+1):
		for j, p in enumerate(pattern):
			if (p == '?' or p == '*') and state[j-1]:
				state[j+1] = True

		if i == m:
			break

		new_state = [False]*(n + 1)

		for j, p in enumerate(pattern):
			if (p == '.' or p == s[i]) and state[j]:
				k = j+1
				if k < n:
					if pattern[k] == '+' or pattern[k] == '*':
						new_state[j] = True
						k += 1
					elif pattern[k] == '?':
						k += 1

				new_state[k] = True

		state = new_state

	return state[n]
