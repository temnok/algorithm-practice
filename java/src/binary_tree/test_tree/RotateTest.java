package binary_tree.test_tree;

import binary_tree.Node;
import binary_tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class RotateTest extends TreeTest {
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
	public void rotateRoot() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node root = tree.root;
		tree.rotate(root);

		Assert.assertEquals("Root has changed", root, tree.root);

		checkAll(tree, expected);
	}

	@Test
	public void rotateSecondLevelLeft() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node node = tree.root.left;
		Node root = tree.root;
		tree.rotate(node);

		Assert.assertEquals("Node is not root", node, tree.root);
		Assert.assertEquals("Right node is not root", node.right, root);

		checkAll(tree, expected);
	}

	@Test
	public void rotateSecondLevelRight() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node node = tree.root.right;
		Node root = tree.root;
		tree.rotate(node);

		Assert.assertEquals("Node is not root", node, tree.root);
		Assert.assertEquals("Left node is not root", node.left, root);

		checkAll(tree, expected);
	}

	@Test
	public void rotateThirdLevel() {
		int start = 0;
		int end = 9; // size = end - start + 1 = 10;
		List<String> initialValues = alphabeticalTestData.getRangeInclusive(start, end);

		Tree tree = createTree(initialValues);
		List<String> expected = new ArrayList<>(initialValues);

		Node node = tree.root.left.left;
		Node parent = tree.root.left;
		Node gp = tree.root;
		tree.rotate(node);

		Assert.assertEquals("Node parent is not root", node.parent, gp);
		Assert.assertEquals("Node is not parent to former parent", parent.parent, node);

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
