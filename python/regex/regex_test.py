import random as rand
import re
from regex import matches_fully

random = rand.Random(0)


def test_random_cases():
	for test in range(50_000):
		str_freq = 1 + random.randrange(20)

		s = ''.join([
			chr(ord('a') + random.randrange(str_freq))
			for _ in range(random.randrange(50))
		])

		pat_chars = ['']*(1+random.randrange(20))
		dot_prob = 1 + random.randrange(20)
		pat_freq = 1 + random.randrange(20)

		j = 0
		while j < len(pat_chars):
			pat_chars[j] = chr(ord('a') + random.randrange(pat_freq))
			if random.randrange(100) < dot_prob:
				pat_chars[j] = '.'

			if j+1 < len(pat_chars):
				prob = random.randrange(100)
				if prob < 20:
					j += 1
					pat_chars[j] = '?'
				elif prob < 40:
					j += 1
					pat_chars[j] = '+'
				elif prob < 60:
					j += 1
					pat_chars[j] = '*'

			j += 1

		pat = ''.join(pat_chars)
		want = re.fullmatch(pat, s) is not None
		got = matches_fully(s, pat)

		assert got == want, f'matches_fully("{s}", "{pat}"):\nwant {want}\n got {got}'
