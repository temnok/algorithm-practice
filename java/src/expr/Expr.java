package expr;

import java.util.*;

public class Expr {
	// evaluateExpr should return 64-bit integer result of evaluating expression in expr.
	// Expression can contain 1-digit integers '0'..'9', brackets '(', ')' and
	// binary operators '+', '-', '*', '/'. Multiply and division have higher priority than add/sub.
	// Division by zero produces zero result. For example, expression '1+2*(3/0-4)' evaluates to -7.
	public static long evaluateExpr(String expr) {
		char [] pol = new char[expr.length()];
		char [] temp = new char[expr.length()];
		int r = 0, t = 0;
		long [] stack = new long[expr.length()];

		for (char c : expr.toCharArray()) {
			if (Character.isDigit(c)) {
				pol[r++] = c;
				continue;
			}

			while (c != '(' && t > 0 && priority(c) <= priority(temp[t - 1])) {
				pol[r++] = temp[--t];
			}

			if (c == ')') {
				t--;
			} else {
				temp[t++] = c;
			}
		}

		while (t > 0) {
			pol[r++] = temp[--t];
		}

		t = 0;
		for (int i = 0; i < r; i++) {
			char c = pol[i];
			if (Character.isDigit(c)) {
				stack[t++] = c - '0';
				continue;
			}

			long b = stack[--t];
			long a = stack[--t];
			switch (c) {
				case '*':
					stack[t++] = a * b;
					break;
				case '/':
					if (b == 0) {
						stack[t++] = 0;
					} else {
						stack[t++] = a/b;
					}
					break;
				case '+':
					stack[t++] = a + b;
					break;
				case '-':
					stack[t++] = a - b;
					break;
			}
		}

		return stack[0];
	}

	private static int priority(char c) {
		switch (c) {
			case '*':
			case '/':
				return 3;
			case '+':
			case '-':
				return 2;
			case ')':
				return 1;
			case '(':
				return 0;
		}
		return 0;
	}

}
