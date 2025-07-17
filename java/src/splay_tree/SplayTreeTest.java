package splay_tree;

import org.junit.Test;
import set.SetTest;

public class SplayTreeTest {
	@Test
	public void testRandomCases() {
		new SetTest().randomCases(() -> new SplayTree());
	}
}
