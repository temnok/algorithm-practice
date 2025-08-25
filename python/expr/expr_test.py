import random as rand
from expr import evaluate_expr

random = rand.Random(0)


def test_example():
	assert evaluate_expr('((1+2)+3*4-5*(6+7))/(0-5)') == 10


def test_random_cases():
	for _ in range(10_000):
		expr, want, _ = gen_expr(random.randrange(10))
		got = evaluate_expr(expr)
		assert got == want, \
			f'evaluate_expr({expr}):\nwant {want}\n got {got}'


def gen_expr(depth):
	expr, val, add = '', 0, False

	if depth == 0:
		v = random.randrange(10)
		return f'{v}', v, False

	n = 1 + random.randrange(5)
	add = random.randrange(2) == 0

	for i in range(n):
		e, v, a = gen_expr(random.randrange(depth))
		if a and not add or random.randrange(10) == 0:
			e = '(' + e + ')'

		if i == 0:
			expr, val = e, v
			continue

		op = random.randrange(2)
		if add:
			if op == 0:
				expr += '+' + e
				val += v
			else:
				expr += '-(' + e + ')'
				val -= v
		else:
			if op == 0:
				expr += '*(' + e + ')'
				val *= v
			else:
				expr += '/(' + e + ')'
				if v != 0:
					val //= v
				else:
					val = 0

	return expr, val, add
