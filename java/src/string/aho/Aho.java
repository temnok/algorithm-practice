package string.aho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Aho {
    // Contains must implement Aho-Corasick algorithm.
    // Returned array should contain true at position i if strs[i] is found in text, false otherwise.
    // - strs contains unique non-empty strings
    public static boolean[] contains(String text, String[] strs) {
        var root = buildTrie(strs);
        findSuffixes(root);

        var ans = new boolean[strs.length];

        var cur = root;
        for (var chr: text.toCharArray()) {
            while (cur != null && !cur.next.containsKey(chr)) {
                cur = cur.suffix;
            }

            if (cur == null) {
                cur = root;
                continue;
            }

            cur = cur.next.get(chr);
            for (var suffix = cur; suffix.strEnd && !ans[suffix.strI]; suffix = suffix.suffix) {
                ans[suffix.strI] = true;
            }
        }

        return ans;
    }

    static class node {
        Map<Character, node> next = new HashMap<>();
        boolean strEnd;
        int strI;

        node suffix;
    }

    static node buildTrie(String[] strs) {
        var root = new node();

        for (var i = 0; i < strs.length; i++) {
            var str = strs[i];
            var cur = root;

            for (var chr: str.toCharArray()) {
                var next = cur.next.get(chr);

                if (next == null) {
                    next = new node();
                    cur.next.put(chr, next);
                }

                cur = next;
            }

            cur.strEnd = true;
            cur.strI = i;
        }

        return root;
    }

    static void findSuffixes(node root) {
        var q = new ArrayList<node>();
        q.add(root);

        for (var i = 0; i < q.size(); i++) {
            var cur = q.get(i);

            for (var kv: cur.next.entrySet()) {
                var chr = kv.getKey();
                var next = kv.getValue();

                q.add(next);

                var s = cur.suffix;
                while (s != null && !s.next.containsKey(chr)) {
                    s = s.suffix;
                }

                if (s == null) {
                    next.suffix = root;
                    continue;
                }

                next.suffix = s.next.get(chr);
                if (!next.strEnd && next.suffix.strEnd) {
                    next.strEnd = true;
                    next.strI = next.suffix.strI;
                }
            }
        }
    }
}
