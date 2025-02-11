package distjoint_set;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class DisjointSetTest {
	@Test
	public void testLargeArray() {
		var n = 1 << 24;
		var set = new DisjointSet(n);
		for (var step = 1; step < n; step *= 2) {
			for (var i = 0; i < n; i += step*2) {
				int j = i + step;
				Assert.assertFalse(set.find(i) == set.find(j));
				Assert.assertTrue(set.union(i, j));
				Assert.assertTrue(set.find(i) == set.find(j));
			}
		}
	}

	@Test
	public void testRandomCases() {
		var rand = new Random(0);
		for (var test = 0; test < 1_000; test++) {
			var n = 1 + rand.nextInt(8*1024);
			var td = new randomTestData(n);

			var set = new DisjointSet(n);
			for (var op = 0; op < td.ops; op++) {
				int i = td.indexes[op*3], j = td.indexes[op*3+1], k = td.indexes[op*3+2];
				Assert.assertEquals(td.expectedUnions[op], set.union(i, j));
				Assert.assertEquals(td.expectedFinds[op], set.find(k));
			}
		}
	}
}
