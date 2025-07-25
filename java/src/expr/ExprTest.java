package expr;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class ExprTest {
	private Random random = new Random(0);

	@Test
	public void testRandomCases() {
		for (var test = 0; test < 100_000; test++) {
			var want = genExpr(random.nextInt(10));
			var got = Expr.evaluateExpr(want.expr);

			if (want.val != got) {
				Assert.assertEquals(String.format("evaluateExpr(\"%s\")", want.expr), want.val, got);
			}
		}
	}

	private Ans genExpr(int depth) {
		var ans = new Ans();
		if (depth == 0) {
			ans.val = random.nextInt(10);
			ans.expr = String.valueOf(ans.val);
			return ans;
		}

		var n = 1 + random.nextInt(5);
		ans.add = random.nextBoolean();

		for (var i = 0; i < n; i++) {
			var e = genExpr(random.nextInt(depth));
			if (e.add && !ans.add || random.nextInt(10) == 0) {
				e.expr = "(" + e.expr + ")";
			}

			if (i == 0) {
				ans = e;
				continue;
			}

			var op = random.nextBoolean();
			if (ans.add) {
				if (op) {
					ans.expr += "+" + e.expr;
					ans.val += e.val;
				} else {
					ans.expr += "-(" + e.expr + ")";
					ans.val -= e.val;
				}
			} else {
				if (op) {
					ans.expr += "*(" + e.expr + ")";
					ans.val *= e.val;
				} else {
					ans.expr += "/(" + e.expr + ")";
					if (e.val == 0) {
						ans.val = 0;
					} else {
						ans.val /= e.val;
					}
				}
			}
		}

		return ans;
	}

	private static class Ans {
		String expr;
		long val;
		boolean add;
	}
}