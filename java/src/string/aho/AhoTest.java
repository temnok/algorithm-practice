package string.aho;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class AhoTest {
    @Test
    public void testContainsSingle() {
        Assert.assertArrayEquals(new boolean[]{true}, Aho.contains("ab", new String[]{"a"}));
        Assert.assertArrayEquals(new boolean[]{true}, Aho.contains("ab", new String[]{"ab"}));
        Assert.assertArrayEquals(new boolean[]{true}, Aho.contains("ab", new String[]{"b"}));
        Assert.assertArrayEquals(new boolean[]{true}, Aho.contains("abc", new String[]{"bc"}));
        Assert.assertArrayEquals(new boolean[]{true}, Aho.contains("abc", new String[]{"c"}));
    }

    @Test
    public void testContainsExamples() {
        Assert.assertArrayEquals(
            new boolean[]{true, true, true},
            Aho.contains("xxxabc",
            new String[]{"abc", "bc", "c"}));

        Assert.assertArrayEquals(
            new boolean[]{true, true, true, true, true, false},
            Aho.contains("ababa",
                new String[]{"baba", "ab", "ababa", "a", "b", "babab"}));

        Assert.assertArrayEquals(
            new boolean[]{false, true},
            Aho.contains("babb",
                new String[]{"baba", "ab"}));

        Assert.assertArrayEquals(
            new boolean[]{true, true, true, true, false},
            Aho.contains("ahishers",
                new String[]{"he", "she", "hers", "his", "him"}));
    }

    @Test
    public void testRandomCases() {
        var random = new Random(0);
	    final var max = 20;

        for (var test = 0; test < 10_000; test++) {
            var text = generateString(random, max, 1+random.nextInt(26));
            var strs = new String[1+random.nextInt(max)];
            var m = 1 + random.nextInt(26);
            for (var i = 0; i < strs.length; i++) {
                strs[i] = generateString(random, max, m);
            }

            strs = removeDuplicates(strs);
            var got = Aho.contains(text, strs);
            var want = containsBruteForce(text, strs);
            if (!Arrays.equals(got, want)) {
                Assert.fail(String.format("Aho.contains(%q, %q):\nwant %s\n got %s",
                    text, Arrays.toString(strs), Arrays.toString(want), Arrays.toString(got)));
            }
        }
    }

    private static boolean[] containsBruteForce(String text, String[] strs) {
        var ans = new boolean[strs.length];

        for (var i = 0; i < strs.length; i++) {
            ans[i] = text.contains(strs[i]);
        }

        return ans;
    }

    private static String generateString(Random random, int n, int m) {
        var str = new char[1+random.nextInt(n)];
        for (var i = 0; i < str.length; i++) {
            str[i] = (char)('a' + random.nextInt(m));
        }
        return new String(str);
    }

    private static String[] removeDuplicates(String[] strs) {
        var known = new HashSet<String>();
        var ans = new ArrayList<String>();
        for (var str : strs) {
            if (!known.contains(str)) {
                ans.add(str);
                known.add(str);
            }
        }
        return ans.toArray(new String[]{});
    }
}
