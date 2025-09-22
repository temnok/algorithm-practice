package skiplist;

import set.Set;

import java.util.Random;

public class SkipList implements Set {
	private Node root = new Node(0, 33);
	private int height = 1;

	private Node[] getPrev(int val) {
		Node[] prev = new Node[height];
		Node cur = root;

		for (int i = height - 1; i >= 0; i--) {
			while (cur.next[i] != null && cur.next[i].val < val) {
				cur = cur.next[i];
			}
			prev[i] = cur;
		}

		return prev;
	}

	// add should insert given val into this skip list
	// and return true if list did not contain val previously,
	// or do nothing and return false if val is already present
	public boolean add(int val) {
		Node[] prev = getPrev(val);

		if (isNext(prev, val)) {
			return false;
		}

		Node node = new Node(val, getHeight());
		int h = 0;

		for (;h < prev.length && h < node.next.length; h++) {
			node.next[h] = prev[h].next[h];
			prev[h].next[h] = node;
		}

		for (; h < node.next.length; h++) {
			root.next[height++] = node;
		}
		return true;
	}

	private int getHeight() {
		return Integer.numberOfTrailingZeros(new Random().nextInt()) + 1;
	}

	private boolean isNext(Node[] prev, int val) {
		return prev[0].next[0] != null && prev[0].next[0].val == val;
	}

	// contains should return true if val is present in the list
	public boolean contains(int val) {
		Node[] prev = getPrev(val);

		return isNext(prev, val);
	}

	// remove should delete val in the list and return true if val was present
	// or do nothing and return false otherwise
	public boolean remove(int val) {
		Node[] prev = getPrev(val);

		if (!isNext(prev, val)) {
			return false;
		}

		Node toRemove = prev[0].next[0];

		for (int i = 0; i < toRemove.next.length; i++) {
			prev[i].next[i] = toRemove.next[i];
		}

		return true;
	}

	private static class Node {
		int val;
		Node[] next;

		public Node(int val, int height) {
			this.val = val;
			next = new Node[height];
		}
	}
}
