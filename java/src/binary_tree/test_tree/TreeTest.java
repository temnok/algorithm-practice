package binary_tree.test_tree;

import binary_tree.Node;
import binary_tree.Tree;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TreeTest {
	public List<String> inorder(Node node, int size) {
		List<String> result = new ArrayList<>();
		NodeWrapper[] stack = new NodeWrapper[size];
		int r = 0;
		stack[r++] = new NodeWrapper(node);

		while (r > 0) {
			NodeWrapper cur = stack[r - 1];
			if (cur.visited || cur.node.left == null) {
				result.addLast(cur.node.value);
				r--;
				if (cur.node.right != null) {
					stack[r++] = new NodeWrapper(cur.node.right);
				}
			} else {
				stack[r++] = new NodeWrapper(cur.node.left);
				cur.visited = true;
			}
		}

		return result;
	}

	public void inorder(Consumer<Node> function, Node root, int size) {
		NodeWrapper [] stack = new NodeWrapper[size];
		int r = 0;
		stack[r++] = new NodeWrapper(root);

		while(r > 0) {
			NodeWrapper cur = stack[r - 1];
			if (cur.visited || cur.node.left == null) {
				function.accept(cur.node);
				r--;
				if (cur.node.right != null) {
					stack[r++] = new NodeWrapper(cur.node.right);
				}
			} else {
				stack[r++] = new NodeWrapper(cur.node.left);
				cur.visited = true;
			}
		}
	}

	public void checkOrder(List<String> expected, List<String> actual) {
		Assert.assertEquals(String.format("Size should be %s but is ", expected.size(), actual.size()), expected.size(),
			actual.size());
		for(int i = 0; i < expected.size(); i++) {
			if (!expected.get(i).equals(actual.get(i))) {
				actual.forEach(System.out::println);
				Assert.fail(String.format("Element %s should be %s but is %s", i, expected.get(i), actual.get(i)));
			}
		}
	}

	public void checkIntegrity(Tree tree, int size) {
		if (tree.root == null) {
			return;
		}

		Assert.assertNull("Root should have null parent", tree.root.parent);

		inorder(stringNode -> {
			if (stringNode.left != null && stringNode.left.parent != stringNode) {
				Assert.fail(String.format("Node %s should have parent %s but has %s", stringNode.left.value, stringNode.value,
					stringNode.left.parent != null ? stringNode.left.parent.value : "null"));
			}
			if (stringNode.right != null && stringNode.right.parent != stringNode) {
				Assert.fail(String.format("Node %s should have parent %s but has %s", stringNode.right.value, stringNode.value,
					stringNode.right.parent != null ? stringNode.right.parent.value : "null"));
			}
		}, tree.root, size);
	}

	public void checkCount(Tree tree, int size) {
		if (tree.root == null) {
			return;
		}

		inorder(stringNode -> {
			int count = calcSize(stringNode);
			Assert.assertEquals(String.format("Node %s size should be %s but is %s", stringNode.value, count,
				stringNode.count), count, stringNode.count);
		}, tree.root, size);

		int count = inorder(tree.root, size).size();

		Assert.assertEquals(String.format("Tree count should be %s but is %s", count, tree.root.count), count,
			tree.root.count);
	}

	private int calcSize(Node stringNode) {
		return (stringNode.left != null ? stringNode.left.count : 0) + (stringNode.right != null ? stringNode.right.count : 0) + 1;
	}

	public void checkAll(Tree tree, List<String> expected) {
		List<String> actual = inorder(tree.root, expected.size());

		checkOrder(expected, actual);
		checkIntegrity(tree, expected.size());
		checkCount(tree, expected.size());

	}

	public Tree createTree(List<String> values) {
		Tree tree = new Tree();

		if (values.size() > 0) {
			tree.root = buildNode(values.toArray(new String[0]), 0, values.size());
		}

		checkAll(tree, values);

		return tree;
	}

	private Node buildNode(String [] arr, int l, int r) {
		if (l == r) {
			return null;
		}

		int m = (l + r)/2;
		Node node = new Node();
		node.value = arr[m];
		node.left = buildNode(arr, l, m);
		node.right = buildNode(arr, m + 1, r);
		int count = 1;

		if (node.left != null) {
			node.left.parent = node;
			count += node.left.count;
		}

		if (node.right != null) {
			node.right.parent = node;
			count += node.right.count;
		}

		node.count = count;
		return node;
	}



	private static class NodeWrapper {
		public Node node;
		public boolean visited;

		public NodeWrapper(Node node) {
			this.node = node;
		}
	}
}
