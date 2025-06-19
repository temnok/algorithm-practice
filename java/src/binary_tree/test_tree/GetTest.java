package binary_tree.test_tree;

import binary_tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class GetTest extends TreeTest {
//	public BiFunction<Tree, Integer, String> testFunction = Get::get;
	private BiFunction<Tree, Integer, String> testFunction = (tree, position) -> {
		return tree.get(position);
	};

	public BiFunction<Tree, Integer, String> getTestFunction() {
		return testFunction;
	}

	@Test
	public void getMiddleTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = new Random().nextInt(1, 9);

		getTest(expected, pos);
	}

	@Test
	public void getFirstTest() {
		int start = 1;
		int end = 10; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		getTest(expected, 0);
	}

	public void getTest(List<String> initialValues, int pos) {
		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		String value = getTestFunction().apply(tree, pos);

		Assert.assertEquals(String.format("Value at position %s should be %s but is %s", pos, expected.get(pos), value),
			expected.get(pos), value);

		checkAll(tree, expected);
	}

	@Test
	public void getLastTest() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		getTest(expected, 9);
	}
}
