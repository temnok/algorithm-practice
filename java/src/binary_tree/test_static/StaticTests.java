package binary_tree.test_static;

import binary_tree.test_tree.InsertAllTest;
import binary_tree.test_tree.RemoveAllTest;
import binary_tree.test_tree.RemoveTest;
import binary_tree.test_tree.SearchTest;
import binary_tree.test_tree.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GetTestStatic.class,
	InsertAllTestStatic.class,
    InsertAllTestStatic.class,
	RemoveAllTestStatic.class,
	RemoveAllTestStatic.class,
	SearchTestStatic.class
		})
public class StaticTests {
}
