package binary_tree.test_static;

import binary_tree.RemoveAll;
import binary_tree.Tree;
import binary_tree.test_tree.RemoveAllTest;
import binary_tree.test_tree.TreeTest;
import binary_tree.test_tree.alphabeticalTestData;
import org.apache.commons.lang3.function.TriConsumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveAllTestStatic extends RemoveAllTest {
	private TriConsumer<Tree, Integer, Integer> testFunction = RemoveAll::removeAll;

	public TriConsumer<Tree, Integer, Integer> getTestFunction() {
		return testFunction;
	}
}
