package trie;

import java.util.HashMap;
import java.util.Map;

public class PrefixSuffix {

	public int countPrefixSuffixPairs(String[] words) {
		Node root = new Node("");
		int prefixSuffix = 0;

		for (int i = words.length - 1; i >= 0; i--) {
			Node cur = root;
			char[] word = words[i].toCharArray();
			boolean findSuffix = true;

			for (int j = 0; j < word.length; j++) {
				char c = word[j];

				if (findSuffix && (j >= word.length / 2 || word[j] != word[word.length - 1 - j])) {
					findSuffix = false;
					if (cur != root) {
						cur.isTerminal++;
					}
				}

				if (cur.children.containsKey(c)) {
					cur = cur.children.get(c);
					if (j == word.length - 1) {
						prefixSuffix += cur.isTerminal;
					}
				} else {
					if (findSuffix) {
						Node node = new Node(cur.val + c);
						cur.children.put(c, node);
						cur = node;
					}
				}
			}
		}

		return prefixSuffix;
	}

	private class Node {
		String val;
		Map<Character, Node> children = new HashMap<>();

		int isTerminal;

		public Node(String s) {
			val = s;
		}
	}

	public static void main(String[] args) {
		String [] words = {"a","aba","ababa","aa"};

		new PrefixSuffix().countPrefixSuffixPairs(words);
	}
}
