package splay_tree;

import set.Set;

public class SplayTree implements Set {
	private Node root;

	// add should insert given val into this skip list
	// and return true if list did not contain val previously,
	// or do nothing and return false if val is already present
	public boolean add(int val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}

		findAndSplay(val);
		if (root.val == val) {
			return false;
		}

		Node node = new Node(val);

		if (val < root.val) {
			node.right = root;
			if (root.left != null) {
				node.left = root.left;
				node.left.parent = node;
				root.left = null;
			}
		} else {
			node.left = root;
			if (root.right != null) {
				node.right = root.right;
				node.right.parent = node;
				root.right = null;
			}
		}
		root.parent = node;
		root = node;

		return true;
	}

	// contains should return true if val is present in the list
	public boolean contains(int val) {
		findAndSplay(val);

		return root != null && root.val == val;
	}

	// remove should delete val in the list and return true if val was present
	// or do nothing and return false otherwise
	public boolean remove(int val) {
		findAndSplay(val);

		if (root == null || root.val != val) {
			return false;
		}

		if (root.left == null) {
			if (root.right != null) {
				root.right.parent = null;
			}
			root = root.right;
			return true;
		}

		Node prev = root.left;

		while (prev.right != null) {
			prev = prev.right;
		}

		Node toRemove = root;

		splay(prev);
		prev.right = toRemove.right;
		if (prev.right != null) {
			prev.right.parent = prev;
		}
		return true;
	}

	private void findAndSplay(int val) {
		if (root == null) {
			return;
		}

		Node cur = root;
		while (true) {
			if (val < cur.val && cur.left != null) {
				cur = cur.left;
			} else if (cur.val < val && cur.right != null) {
				cur = cur.right;
			} else {
				break;
			}
		}

		splay(cur);
	}

	private void splay(Node node) {
		while (node.parent != null) {
			Node parent = node.parent;
			Node gp = parent.parent;
			if (gp != null) {
				if ((gp.left == parent) == (parent.left == node)) {
					rotate(parent);
				} else {
					rotate(node);
				}
			}
			rotate(node);
		}
	}

	private void rotate(Node node) {
		if (node.parent == null) {
			return;
		}

		Node parent = node.parent;
		Node gp = parent.parent;
		node.parent = gp;
		if (gp != null) {
			if (gp.left == parent) {
				gp.left = node;
			} else {
				gp.right = node;
			}
		} else {
			root = node;
		}

		if (parent.left == node) {
			parent.left = node.right;
			if (node.right != null) {
				node.right.parent = parent;
			}
			node.right = parent;
		} else {
			parent.right = node.left;
			if (node.left != null) {
				node.left.parent = parent;
			}
			node.left = parent;
		}
		parent.parent = node;
	}

	private static class Node {
		Node left;
		Node right;
		Node parent;
		int val;

		public Node(int val) {
			this.val = val;
		}
	}
}
