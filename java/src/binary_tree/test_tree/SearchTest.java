package binary_tree.test_tree;

import binary_tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class SearchTest extends TreeTest {
//	public BiFunction<Tree, String, Integer> testFunction = Search::search;
	public BiFunction<Tree, String, Integer> testFunction = (tree, value) -> {
		return tree.search(value);
	};

	@Test
	public void searchRandom() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);

		int pos = new Random().nextInt(1, 9);

		searchTest(expected, pos);
	}

	public void searchTest(List<String> initialValues, int position) {
		Tree tree = createTree(initialValues);

		String value =  initialValues.get(position);
		int actual = testFunction.apply(tree, value);

		Assert.assertEquals(String.format("Value %s should be at position %s but is at position %s", value,
			position, actual), position, actual);

		checkAll(tree, initialValues);
	}
}
