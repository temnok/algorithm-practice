package mst;

import org.junit.Test;

public class KruskalTest {
	@Test
	public void testRandomCases() {
		for (int test = 0; test < 10_000; test++) {
			var td = new randomTestData();
			var got = Kruskal.minimumSpanningForest(td.n, td.edges.clone());
			td.assertAnswer(got);
		}
	}
}
