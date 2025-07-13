package hash;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class CustomHashSetTest {
	private static class TestData {
		int hash;
		String str;

		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass()) return false;
			TestData testData = (TestData) o;
			return hash == testData.hash && Objects.equals(str, testData.str);
		}

		@Override
		public int hashCode() {
			return hash;
		}
	}

	@Test
	public void simpleTest() {
		CustomHashSet<Integer> hs = new CustomHashSet<>();

		for (int i = 0; i < 1000; i++) {
			hs.add(i);
		}

		for (int i = 0; i < hs.nodes.length; i++) {
			CustomHashSet.Node node = hs.nodes[i];
			if (node == null) {
				continue;
			}

			Assert.assertTrue(node.prev == null);
			while (node.next != null) {
				Assert.assertTrue(node.next.prev == node);
				Assert.assertEquals(node.hashCode % hs.nodes.length, i);
				node = node.next;
			}
			Assert.assertEquals(node.val.hashCode(), node.hashCode);
			Assert.assertEquals(node.hashCode % hs.nodes.length, i);

		}
	}
}
