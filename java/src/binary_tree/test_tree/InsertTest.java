package binary_tree.test_tree;

import binary_tree.Tree;
import org.apache.commons.lang3.function.TriConsumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertTest extends TreeTest {
	//	public TriConsumer<Tree, String, Integer> testFunction = Insert::insert;
	public TriConsumer<Tree, String, Integer> testFunction = (tree, value, position) -> {
		tree.insert(value, position);
	};

	@Test
	public void insertMiddleTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = new Random().nextInt(1, 9);
		String value = expected.get(pos - 1) + expected.get(pos - 1);

		insertTest(expected, value, pos);
	}

	@Test
	public void insertFirstTest() {
		int start = 1;
		int end = 10; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = 0;
		String value = alphabeticalTestData.alphabet.get(pos);

		insertTest(expected, value, pos);
	}

	public void insertTest(List<String> initialValues, String value, int pos) {
		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		testFunction.accept(tree, value, pos);
		expected.add(pos, value);

		checkAll(tree, expected);
	}

	@Test
	public void insertLastTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = 10;
		String value = alphabeticalTestData.alphabet.get(pos);

		insertTest(expected, value, pos);
	}
}
