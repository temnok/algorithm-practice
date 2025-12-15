package expr

// EvaluateExpr should return 64-bit integer result of evaluating a *valid* expression in expr.
// Expression can contain 1-digit integers '0'..'9', brackets '(', ')' and
// binary operators '+', '-', '*', '/'. Multiply and division have higher priority than add/sub.
// Division by zero produces zero result. For example, expression '1+2*(3/0-4)' evaluates to -7.
func EvaluateExpr(expr string) int {
	return rpnEval(rpnFromExpr(expr))
}

func rpnFromExpr(expr string) []byte {
	var rpn, stack []byte

	pri := map[byte]int{
		'(': 0,
		')': 1,
		'+': 2,
		'-': 2,
		'*': 3,
		'/': 3,
	}

	for _, op := range []byte(expr) {
		switch {
		case op >= '0' && op <= '9':
			rpn = append(rpn, op)

		case op == '(':
			stack = append(stack, op)

		default:
			sp := len(stack) - 1
			for ; sp >= 0 && pri[stack[sp]] >= pri[op]; sp-- {
				rpn = append(rpn, stack[sp])
			}

			if op == ')' {
				stack = stack[:sp]
			} else {
				stack = append(stack[:sp+1], op)
			}
		}
	}

	for sp := len(stack) - 1; sp >= 0; sp-- {
		rpn = append(rpn, stack[sp])
	}

	return rpn
}

func rpnEval(rpn []byte) int {
	var stack []int

	for _, op := range rpn {
		if op >= '0' && op <= '9' {
			stack = append(stack, int(op-'0'))
			continue
		}

		sp := len(stack) - 2
		a, b, c := stack[sp], stack[sp+1], 0

		switch op {
		case '+':
			c = a + b
		case '-':
			c = a - b
		case '*':
			c = a * b
		case '/':
			if b != 0 {
				c = a / b
			}
		}

		stack = append(stack[:sp], c)
	}

	return stack[0]
}
