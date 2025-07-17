package skiplist;

import org.junit.Test;
import set.SetTest;

import java.util.Random;

public class SkipListTest {
	@Test
	public void testRandomCases() {
		new SetTest().randomCases(() -> new SkipList());
	}
}
