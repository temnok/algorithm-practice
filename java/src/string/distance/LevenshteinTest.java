package string.distance;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

public class LevenshteinTest {
    @Test
    public void testDistance() {
        Assert.assertEquals(1, Levenshtein.distance("aaa", "aa"));
        Assert.assertEquals(3, Levenshtein.distance("kitten", "sitting"));
    }

    @Test
    public void testRandomCases() {
        var random = new Random(0);

        for (var test = 0; test < 10_000; test++) {
            var m = random.nextInt(100);
            var n = random.nextInt(100);
            var r = 1 + random.nextInt(26);

            var a1 = new char[m];
            var b1 = new char[n];
            for (var i = 0; i < m; i++) {
                a1[i] = (char)('a' + random.nextInt(r));
            }
            for (var i = 0; i < n; i++) {
                b1[i] = (char)('a' + random.nextInt(r));
            }

            var a = new String(a1);
            var b = new String(b1);

            var got = Levenshtein.distance(a, b);
            var want = rec(a, b, new int[m][n]);
            if (got != want) {
                Assert.fail(String.format("Levenshtein.distance(%s, %s):\nwant %s\n got %s", a, b, want, got));
            }
        }
    }

    private static int rec(String a, String b, int[][] mem) {
        var i = a.length()-1;
        var j = b.length()-1;
        if (i < 0) { return j+1; }
        if (j < 0) { return i+1; }

        if (mem[i][j] == 0) {
            var d = a.charAt(i) == b.charAt(j)? 0 : 1;
            mem[i][j] = 1+Math.min(d + rec(a.substring(0, i), b.substring(0, j), mem),
                    Math.min(rec(a.substring(0, i), b, mem)+1, rec(a, b.substring(0, j), mem)+1));
        }
        return mem[i][j]-1;
    }
}
