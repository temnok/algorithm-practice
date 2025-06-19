package binary_tree.test_static;

import binary_tree.Insert;
import binary_tree.Tree;
import binary_tree.test_tree.InsertTest;
import binary_tree.test_tree.TreeTest;
import binary_tree.test_tree.alphabeticalTestData;
import org.apache.commons.lang3.function.TriConsumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertTestStatic extends InsertTest {
	private TriConsumer<Tree, String, Integer> testFunction = Insert::insert;

	public TriConsumer<Tree, String, Integer> getTestFunction() {
		return testFunction;
	}
}
