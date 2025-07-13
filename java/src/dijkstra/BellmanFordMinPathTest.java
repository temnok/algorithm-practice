package dijkstra;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class BellmanFordMinPathTest {
	@Test
	public void testRandomCasesBellmanFord() {
		for (int i = 0; i < 10_000; i++) {
			var td = new randomTestData();

			var actual = BellmanFordMinPath.bellmanFordMinPath(td.n, td.edges, td.start);
			if (!Arrays.equals(actual, td.expected)) {
				Assert.fail(String.format(
					"dijkstraMinPath(%s, %s, %s):\nwant %s\n got %s",
					td.n, Arrays.deepToString(td.edges), td.start, Arrays.toString(td.expected), Arrays.toString(actual)
				));
			}
		}
	}

}
