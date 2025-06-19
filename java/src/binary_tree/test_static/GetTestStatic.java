package binary_tree.test_static;

import binary_tree.Get;
import binary_tree.Tree;
import binary_tree.test_tree.GetTest;

import java.util.function.BiFunction;

public class GetTestStatic extends GetTest {
	private BiFunction<Tree, Integer, String> testFunction = Get::get;

	public BiFunction<Tree, Integer, String> getTestFunction() {
		return testFunction;
	}
}
