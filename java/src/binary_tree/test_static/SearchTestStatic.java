package binary_tree.test_static;

import binary_tree.Search;
import binary_tree.Tree;
import binary_tree.test_tree.SearchTest;
import binary_tree.test_tree.TreeTest;
import binary_tree.test_tree.alphabeticalTestData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class SearchTestStatic extends SearchTest {
	private BiFunction<Tree, String, Integer> testFunction = Search::search;

	public BiFunction<Tree, String, Integer> getTestFunction() {
		return testFunction;
	}
}
