package binary_tree.test_static;

import binary_tree.InsertAll;
import binary_tree.Tree;
import binary_tree.test_tree.InsertAllTest;
import binary_tree.test_tree.TreeTest;
import binary_tree.test_tree.alphabeticalTestData;
import org.apache.commons.lang3.function.TriConsumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertAllTestStatic extends InsertAllTest {
	public TriConsumer<Tree, Tree, Integer> testFunction = InsertAll::insertAll;

	public TriConsumer<Tree, Tree, Integer> getTestFunction() {
		return testFunction;
	}

}
