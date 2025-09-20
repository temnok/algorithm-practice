package tree_traversal;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TreeTraversalTest {
	@Test
	public void testInorder() {
		randomTestData td = new randomTestData(100);
		Node root = td.nodes[0];

		var actual = TreeTraversal.inorder(root, td.nodes.length);
		List<Integer> values = new ArrayList<>();
		getInorder(root, values);
		int[] expected = values.stream().mapToInt(Integer::intValue).toArray();

		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Expected: %s\n actual: %s\n", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	@Test
	public void testPreorder() {
		randomTestData td = new randomTestData(100);
		Node root = td.nodes[0];

		var actual = TreeTraversal.preorder(root, td.nodes.length);
		List<Integer> values = new ArrayList<>();
		getPreorder(root, values);
		int[] expected = values.stream().mapToInt(Integer::intValue).toArray();

		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Expected: %s\n actual: %s\n", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	@Test
	public void testPostorder() {
		randomTestData td = new randomTestData(100);
		Node root = td.nodes[0];

		var actual = TreeTraversal.postorder(root, td.nodes.length);
		List<Integer> values = new ArrayList<>();
		getPostorder(root, values);
		int[] expected = values.stream().mapToInt(Integer::intValue).toArray();

		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Expected: %s\n actual: %s\n", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	@Test
	public void testBfs() {
		randomTestData td = new randomTestData(100);
		Node root = td.nodes[0];

		var actual = TreeTraversal.bfs(root, td.nodes.length);
		List<Integer> values = new ArrayList<>();
		getBfs(root, values);
		int[] expected = values.stream().mapToInt(Integer::intValue).toArray();

		if (!Arrays.equals(expected, actual)) {
			Assert.fail(String.format("Expected: %s\n actual: %s\n", Arrays.toString(expected),
				Arrays.toString(actual)));
		}
	}

	private void getInorder(Node node, List<Integer> result) {
		if (node == null) {
			return;
		}
		getInorder(node.left, result);
		result.addLast(node.val);
		getInorder(node.right, result);
	}

	private void getPreorder(Node node, List<Integer> result) {
		if (node == null) {
			return;
		}
		result.addLast(node.val);
		getPreorder(node.left, result);
		getPreorder(node.right, result);
	}

	private void getPostorder(Node node, List<Integer> result) {
		if (node == null) {
			return;
		}
		getPostorder(node.left, result);
		getPostorder(node.right, result);
		result.addLast(node.val);
	}

	private void getBfs(Node root, List<Integer> result) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			result.addLast(cur.val);

			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

}
