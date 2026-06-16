package string.distance;

public class Levenshtein {
    // distance should return the minimum number of single-character edits
    // required to transform string a into b or vice versa. Edits are insertions,
    // deletions or replacing once character with another. Examples:
    //
    // distance("aaa", "aa") must return 1
    // distance("kitten", "sitting") must return 3
    //
    public static int distance(String a, String b) {
        var m = a.length();
        var n = b.length();

        var cur = new int[n+1];
        for (var j = 0; j <= n; j++) {
            cur[j] = j;
        }

        var prev = new int[n+1];
        for (var i = 0; i < m; i++) {
            var tmp = prev; prev = cur; cur = tmp;

            cur[0] = i+1;

            for (var j = 0; j < n; j++) {
                var x = prev[j];
                if (a.charAt(i) != b.charAt(j)) {
                    x++;
                }

                var y = Math.min(cur[j], prev[j+1])+1;
                cur[j+1] = Math.min(x, y);
            }

        }

        return cur[n];
    }
}
