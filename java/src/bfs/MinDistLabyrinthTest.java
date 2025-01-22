package bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MinDistLabyrinthTest {
	@Test
	public void testRandomCases() {
		for (int i = 0; i < 100_000; i++) {
			var td = new randomLabyrinthTestData();

			var expected = td.minDist;
			var actual = MinDistLabyrinth.minDistLabyrinth(td.labyrinth);

			if (expected != actual) {
				Assert.fail(String.format(
					"minDistLabyrinth(%s):\nwant %s\n got %s",
					Arrays.deepToString(td.labyrinth), expected, actual
				));
			}
		}
	}
}
