package skiplist;

import set.Set;

import java.util.Random;

public class SkipList implements Set {
	Node root = new Node(0, 33);
	int height = 1;
	static Random random = new Random();

	// add should insert given val into this skip list
	// and return true if list did not contain val previously,
	// or do nothing and return false if val is already present
	public boolean add(int val) {
		var prev = findPrev(val);
		var cur = prev[0].next[0];
		if (cur != null && cur.val == val) {
			return false;
		}

		var h = 1;
		while (random.nextInt(2) == 0 && h < root.next.length) {
			h++;
		}
		
		cur = new Node(val, h);

		for (var i = 0; i < h && i < height; i++) {
			cur.next[i] = prev[i].next[i];
			prev[i].next[i] = cur;
		}

		for (var i = height; i < h; i++) {
			height++;
			root.next[i] = cur;
		}

		return true;
	}

	// contains should return true if val is present in the list
	public boolean contains(int val) {
		var prev = findPrev(val);
		var cur = prev[0].next[0];
		return cur != null && cur.val == val;
	}

	// remove should delete val in the list and return true if val was present
	// or do nothing and return false otherwise
	public boolean remove(int val) {
		var prev = findPrev(val);
		var cur = prev[0].next[0];
		if (cur == null || cur.val != val) {
			return false;
		}

		for (var i = 0; i < cur.next.length; i++) {
			prev[i].next[i] = cur.next[i];
		}

		return true;
	}

	private Node[] findPrev(int val) {
		var prev = new Node[height];
		var cur = root;

		for (var i = height-1; i >= 0; i--) {
			while (cur.next[i] != null && cur.next[i].val < val) {
				cur = cur.next[i];
			}

			prev[i] = cur;
		}

		return prev;
	}
}

class Node {
	int val;
	Node[] next;

	Node(int v, int h) {
		val = v;
		next = new Node[h];
	}
}
