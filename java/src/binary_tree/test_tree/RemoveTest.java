package binary_tree.test_tree;

import binary_tree.Tree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class RemoveTest extends TreeTest {
//		public BiConsumer<Tree, Integer> testFunction = Remove::remove;
	public BiConsumer<Tree, Integer> testFunction = (tree, position) -> {
		tree.remove(position);
	};

	@Test
	public void removeMiddleTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = new Random().nextInt(1, 9);

		removeTest(expected, pos);
	}

	@Test
	public void removeFirstTest() {
		int start = 1;
		int end = 10; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = 0;

		removeTest(expected, pos);
	}

	public void removeTest(List<String> initialValues, int pos) {
		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		testFunction.accept(tree, pos);
		expected.remove(pos);

		checkAll(tree, expected);
	}

	@Test
	public void removeLastTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		removeTest(expected, 9);
	}
}
