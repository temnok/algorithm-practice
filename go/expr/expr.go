package expr

// EvaluateExpr should return 64-bit integer result of evaluating a *valid* expression in expr.
// Expression can contain 1-digit integers '0'..'9', brackets '(', ')' and
// binary operators '+', '-', '*', '/'. Multiply and division have higher priority than add/sub.
// Division by zero produces zero result. For example, expression '1+2*(3/0-4)' evaluates to -7.
func EvaluateExpr(expr string) int {
	pri := map[byte]int{
		'*': 3,
		'/': 3,
		'+': 2,
		'-': 2,
		')': 1,
		'(': 0,
	}

	var rp, stack []byte
	for _, op := range []byte(expr) {
		switch {
		case op >= '0' && op <= '9':
			rp = append(rp, op)

		case op == '(':
			stack = append(stack, '(')

		default:
			sp := len(stack)
			for ; sp > 0 && pri[stack[sp-1]] >= pri[op]; sp-- {
				rp = append(rp, stack[sp-1])
			}

			stack = stack[:sp]

			if op == ')' {
				stack = stack[:sp-1]
			} else {
				stack = append(stack, op)
			}
		}
	}

	for sp := len(stack) - 1; sp >= 0; sp-- {
		rp = append(rp, stack[sp])
	}

	var calcStack []int

	for _, op := range rp {
		if op >= '0' && op <= '9' {
			calcStack = append(calcStack, int(op-'0'))
			continue
		}

		sp := len(calcStack) - 2
		a, b := calcStack[sp], calcStack[sp+1]
		switch op {
		case '+':
			calcStack[sp] = a + b
		case '-':
			calcStack[sp] = a - b
		case '*':
			calcStack[sp] = a * b
		case '/':
			if b == 0 {
				calcStack[sp] = 0
			} else {
				calcStack[sp] = a / b
			}
		}

		calcStack = calcStack[:sp+1]
	}

	return calcStack[0]
}
