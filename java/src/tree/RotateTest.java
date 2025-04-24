package tree;

import org.junit.Test;

public class RotateTest {
	@Test
	public void testRandom() {
		for (var i = 0; i < 10_000; i++) {
			var td = new randomTestData(100);
			for (var j = 0; j < 100; j++) {
				Rotate.rotate(td.randomNode());
				td.assertNodes();
			}
		}
	}
}
