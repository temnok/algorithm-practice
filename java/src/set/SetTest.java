package set;

import org.junit.Assert;

import java.util.Random;
import java.util.TreeSet;
import java.util.function.Supplier;

public class SetTest {
	Random random = new Random(0);

	public void randomCases(Supplier<Set> newFunc) {
		for (int test = 0; test < 10_000; test++) {
			var skiplist = newFunc.get();

			var maxVal = random.nextInt(100);
			var addProb = random.nextDouble();
			var findProb = random.nextDouble();

			var set = new TreeSet<Integer>();
			for (int op = 0; op < 1_000; op++) {
				var val = random.nextInt(maxVal+1);

				var p = random.nextDouble();
				if (p < addProb) {
					var expected = !set.contains(val);
					var actual = skiplist.add(val);
					if (actual != expected) {
						Assert.fail(String.format("SkipList(%s)\n.add(%s):\nwant %s\n got %s",
							set, val, expected, actual));
					}
					set.add(val);
				} else if (p < findProb) {
					var expected = set.contains(val);
					var actual = skiplist.contains(val);
					if (actual != expected) {
						Assert.fail(String.format("SkipList(%s)\n.contains(%s):\nwant %s\n got %s",
							set, val, expected, actual));
					}
				} else {
					var expected = set.contains(val);
					var actual = skiplist.remove(val);
					if (actual != expected) {
						Assert.fail(String.format("SkipList(%s)\n.remove(%s):\nwant %s\n got %s",
							set, val, expected, actual));
					}
					set.remove(val);
				}
			}
		}
	}
}