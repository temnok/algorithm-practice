package mst;

import org.junit.Test;

public class PrimTest {
	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();
			var got = Prim.minimumSpanningForest(td.n, td.edges.clone());
			td.assertAnswer(got);
		}
	}
}
