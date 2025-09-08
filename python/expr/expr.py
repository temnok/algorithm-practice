
# evaluate_expr should return 64-bit integer result of evaluating a *valid* expression in expr.
# Expression can contain 1-digit integers '0' .. '9', brackets '(', ')' and
# binary operators '+', '-', '*', '/'. Multiply and division have higher priority than add/sub.
# Division by zero produces zero result. For example, expression '1+2*(3/0-4)' evaluates to -7.
def evaluate_expr(expr: str) -> int:
	raise NotImplementedError('TODO')
