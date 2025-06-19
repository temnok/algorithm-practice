package binary_tree.test_tree;

import binary_tree.Tree;
import org.apache.commons.lang3.function.TriConsumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertAllTest extends TreeTest {
//		public TriConsumer<Tree, Tree, Integer> testFunction = InsertAll::insertAll;
	public TriConsumer<Tree, Tree, Integer> testFunction = (tree, values, position) -> {
		tree.insertAll(values, position);
	};
	@Test
	public void insertAllMiddleTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		Random random = new Random();
		int pos = random.nextInt(1, 9);
		int repeat = random.nextInt(2, 5);
		List<String> values = new ArrayList<>(repeat);
		String initial = alphabeticalTestData.alphabet.get(pos - 1);

		for (int i = 0; i < repeat; i++) {
			initial = initial + alphabeticalTestData.alphabet.get(pos - 1);;
			values.addLast(initial);
		}

		insertAllTest(expected, values, pos);
	}

	@Test
	public void insertAllFirstTest() {
		int start = 3;
		int end = 12; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);
		int pos = 0;
		List<String> values = alphabeticalTestData.getRangeInclusive(pos, start - 1);

		insertAllTest(expected, values, pos);
	}

	public void insertAllTest(List<String> initialValues, List<String> values, int pos) {
		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		testFunction.accept(tree, createTree(values), pos);
		expected.addAll(pos, values);

		checkAll(tree, expected);
	}

	@Test
	public void insertAllLastTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);
		List<String> values = alphabeticalTestData.getRangeInclusive(10, 12);
		int pos = 10;

		insertAllTest(expected, values, pos);
	}
}
