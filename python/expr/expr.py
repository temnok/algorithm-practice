
# evaluate_expr should return 64-bit integer result of evaluating a *valid* expression in expr.
# Expression can contain 1-digit integers '0' .. '9', brackets '(', ')' and
# binary operators '+', '-', '*', '/'. Multiply and division have higher priority than add/sub.
# Division by zero produces zero result. For example, expression '1+2*(3/0-4)' evaluates to -7.
def evaluate_expr(expr: str) -> int:
	# raise NotImplementedError('TODO')

	rpn = []

	pri = {'(': 0, ')': 1, '+': 2, '-': 2, '*': 3, '/': 3}
	stack = []

	for op in expr:
		if '0' <= op <= '9':
			rpn.append(op)
			continue

		if op == '(':
			stack.append(op)
			continue

		while len(stack) > 0 and pri[stack[-1]] >= pri[op]:
			rpn.append(stack.pop())

		if op == ')':
			stack.pop()
		else:
			stack.append(op)

	while len(stack) > 0:
		rpn.append(stack.pop())

	stack.clear()

	for op in rpn:
		if '0' <= op <= '9':
			stack.append(ord(op) - ord('0'))
			continue

		b, a = stack.pop(), stack.pop()

		match op:
			case '+': stack.append(a+b)
			case '-': stack.append(a-b)
			case '*': stack.append(a*b)
			case '/': stack.append(a//b if b != 0 else 0)

	return stack.pop()
