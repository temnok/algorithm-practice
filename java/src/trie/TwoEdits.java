package trie;

import java.util.*;

public class TwoEdits {
	public List<String> twoEditWords(String[] queries, String[] dictionary) {
		Node root = new Node("");

		for (String word : dictionary) {
			Node cur = root;
			for (char c: word.toCharArray()) {
				if (cur.children.containsKey(c)) {
					cur = cur.children.get(c);
				} else {
					Node node = new Node(cur.val + c);
					cur.children.put(c, node);
					cur = node;
				}
				if (cur.val.length() == word.length()) {
					cur.terminal = true;
				}
			}
		}

		List<String> result = new ArrayList<>();
		for (String query : queries) {
			Node cur = root;
			Node fallback = root;
			int count = 0;
			for (char c : query.toCharArray()) {
				if (cur.children.containsKey(c)) {
					cur = cur.children.get(c);
				} else {

				}
			}
		}

		return null;
	}

	private class Node {
		String val;
		Map<Character, Node> children = new LinkedHashMap<>();

		Node fallback;

		boolean terminal;

		public Node(String s) {
			this.val = s;
		}
	}
}
