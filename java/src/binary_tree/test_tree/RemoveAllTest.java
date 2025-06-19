package binary_tree.test_tree;

import binary_tree.Tree;
import org.apache.commons.lang3.function.TriConsumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveAllTest extends TreeTest {
//		public TriConsumer<Tree, Integer, Integer> testFunction = RemoveAll::removeAll;
	public TriConsumer<Tree, Integer, Integer> testFunction = (tree, start, end) -> {
		tree.removeAll(start, end);
	};

	@Test
	public void removeAllMiddleTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		Random random = new Random();
		int startPos = random.nextInt(1, 6);

		removeAllTest(expected, startPos, startPos + 2);
	}

	@Test
	public void removeAllFirstTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		removeAllTest(expected, 0, 3);
	}

	public void removeAllTest(List<String> initialValues, int start, int end) {
		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		testFunction.accept(tree, start, end);
		List<String> toRemove = expected.subList(start, end + 1);

		expected.removeAll(toRemove);

		checkAll(tree, expected);
	}

	@Test
	public void removeLastTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = 10;

		removeAllTest(expected, 7, 9);
	}
}
