package expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Expr {
	// evaluateExpr should return 64-bit integer result of evaluating expression in expr.
	// Expression can contain 1-digit integers '0'..'9', brackets '(', ')' and
	// binary operators '+', '-', '*', '/'. Multiply and division have higher priority than add/sub.
	// Division by zero produces zero result. For example, expression '1+2*(3/0-4)' evaluates to -7.
	public static long evaluateExpr(String expr) {
//		throw new UnsupportedOperationException("TODO");

		var rpn = exprToRPN(expr);
		return evaluateRPN(rpn);
	}

	private static String exprToRPN(String expr) {
		var rpn = new StringBuilder();

		var pri = new HashMap<Character, Integer>();
		pri.put('(', 0);
		pri.put(')', 1);
		pri.put('+', 2);
		pri.put('-', 2);
		pri.put('*', 3);
		pri.put('/', 3);

		var stack = new Stack<Character>();
		for (var c: expr.toCharArray()) {
			if (c >= '0' && c <= '9') {
				rpn.append(c);
				continue;
			}

			if (c == '(') {
				stack.push(c);
				continue;
			}

			var p = pri.get(c);
			while (!stack.isEmpty() && pri.get(stack.peek()) >= p) {
				rpn.append(stack.pop());
			}

			if (c == ')') {
				stack.pop();
			} else {
				stack.push(c);
			}
		}

		while (!stack.isEmpty()) {
			rpn.append(stack.pop());
		}

		return rpn.toString();
	}

	private static long evaluateRPN(String rp) {
		var stack = new Stack<Long>();

		for (var c: rp.toCharArray()) {
			if (c >= '0' && c <= '9') {
				stack.push((long)c - '0');

				continue;
			}

			var b = stack.pop();
			var a = stack.pop();
			switch (c) {
				case '+': stack.push(a + b); break;
				case '-': stack.push(a - b); break;
				case '*': stack.push(a * b); break;
				case '/': stack.push(b != 0? a / b : (long)0); break;
			}
		}

		return stack.pop();
	}
}
