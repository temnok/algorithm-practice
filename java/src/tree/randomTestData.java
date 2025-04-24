package tree;

import org.junit.Assert;

import java.util.*;

class randomTestData {
	private static final Random rand = new Random(0);

	private Node[] nodes, referenceNodes;
	private Map<Node, Node> referenceMap = new HashMap<>();
	private String snapshot;

	randomTestData(int maxNodeCount) {
		var n = 1 + rand.nextInt(maxNodeCount);
		nodes = new Node[n];
		referenceNodes = new Node[n];

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
			referenceNodes[i] = new Node(i);
			referenceMap.put(referenceNodes[i], nodes[i]);

			if (i > 0) {
				nodes[i-1].parent = nodes[i];
				nodes[i].left = nodes[i-1];

				referenceNodes[i-1].parent = referenceNodes[i];
				referenceNodes[i].left = referenceNodes[i-1];
			}
		}
	}

	Node randomNode() {
		snapshot = toSnapshot(nodes);

		var i = rand.nextInt(nodes.length);
		var c = referenceNodes[i];
		var p = c.parent;
		if (p != null) {
			var g = p.parent;
			if (g != null) if (p == g.left) g.left = c; else g.right = c;
			c.parent = g;
			p.parent = c;
			if (c == p.left) {
				p.left = c.right;
				if (p.left != null) p.left.parent = p;
				c.right = p;
			} else {
				p.right = c.left;
				if (p.right != null) p.right.parent = p;
				c.left = p;
			}
		}
		return nodes[i];
	}

	void assertNodes() {
		var actual = toSnapshot(nodes);
		var expected = toSnapshot(referenceNodes);
		Assert.assertEquals("State before rotation: " + snapshot, expected, actual);

		for (int i = 0; i < nodes.length; i++) {
			var n = nodes[i];
			var r = referenceNodes[i];
			Assert.assertSame(referenceMap.get(r), n);
			Assert.assertSame(referenceMap.get(r.left), n.left);
			Assert.assertSame(referenceMap.get(r.right), n.right);
			Assert.assertSame(referenceMap.get(r.parent), n.parent);
		}
	}

	private String toSnapshot(Node[] nodes) {
		Node root = null;
		for (var node: nodes) {
			if (node.parent == null) {
				if (root != null) {
					Assert.fail("More than one root node found");
				}
				root = node;
			}
		}
		if (root == null) {
			Assert.fail("No root node found");
		}
		var buf = new StringBuilder();
		try {
			toSnapshot(root, buf);
		} catch (StackOverflowError ignore) {
			System.err.println("(Stack overflow!!!)");
			if (buf.length() > 10_000) {
				buf.setLength(10_000);
				buf.append("...");
			}
		}
		return buf.toString();
	}

	private void toSnapshot(Node c, StringBuilder buf) {
		if (c == null) return;
		buf.append('(');
		toSnapshot(c.left, buf);
		buf.append(c.val);
		toSnapshot(c.right, buf);
		buf.append(')');
	}
}