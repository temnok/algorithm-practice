package binary_tree.test_static;

import binary_tree.Remove;
import binary_tree.Tree;
import binary_tree.test_tree.RemoveTest;

import java.util.function.BiConsumer;

public class RemoveTestStatic extends RemoveTest {
	private BiConsumer<Tree, Integer> testFunction = Remove::remove;

	public BiConsumer<Tree, Integer> getTestFunction() {
		return testFunction;
	}
}
