package trie;

import java.util.HashMap;
import java.util.Map;

public class FindPrefix {
	// hasPrefix should return true if there is a string in dict that is a prefix of another string in dict.
	// All strings in dict are expected to consist of low-case latin letters only, 'a'..'z'.
	public static boolean hasPrefix(String[] dict) {
		var root = new Node();

		for (var word: dict) {
			var prev = root;

			for (var i = 0; i < word.length(); i++) {
				var c = word.charAt(i);
				var cur = prev.next.get(c);

				if (cur == null) {
					cur = new Node();
					prev.next.put(c, cur);
				} else if (cur.end || i == word.length()-1) {
					return true;
				}

				prev = cur;
			}

			prev.end = true;
		}

		return false;
	}

	static class Node {
		Map<Character, Node> next = new HashMap<>();
		boolean end;
	}
}
