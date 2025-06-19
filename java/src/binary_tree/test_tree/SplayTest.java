package binary_tree.test_tree;

import binary_tree.Node;
import binary_tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SplayTest extends TreeTest {
//	@Test
//	public void getMiddleTest() {
//		int start = 0;
//		int end = 9; // size = end - start + 1 = 10;
//		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);
//
//		int pos = new Random().nextInt(1, 9);
//
//		getTest(expected, pos);
//	}

//	@Test
//	public void getFirstTest() {
//		int start = 1;
//		int end = 10; // size = end - start + 1 = 10;
//		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);
//
//		getTest(expected, 0);
//	}

	@Test
	public void splayRoot() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node root = tree.root;
		Node left = root.left;
		Node right = root.right;
		tree.splay(root);

		Assert.assertEquals("Root has changed", root, tree.root);
		Assert.assertEquals("Left node has changed", left, tree.root.left);
		Assert.assertEquals("Right node has changed", right, tree.root.right);

		checkAll(tree, expected);
	}

	@Test
	public void splayFirst() {
		int start = 0;
		int end = 14; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node node = tree.root.left.left.left;
		tree.splay(node);

		Assert.assertEquals("Node is not root", node, tree.root);

		checkAll(tree, expected);
	}

	@Test
	public void splaySecond() {
		int start = 0;
		int end = 14; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node node = tree.root.left.left.right;
		tree.splay(node);

		Assert.assertEquals("Node is not root", node, tree.root);

		checkAll(tree, expected);
	}

//	@Test
//	public void getLastTest() {
//		int start = 0;
//		int end = 9; // size = end - start + 1 = 10;
//		List<String> expected = alphabeticalTestData.getRangeInclusive(start, end);
//
//		getTest(expected, 9);
//	}
}
