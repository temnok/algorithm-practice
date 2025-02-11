package distjoint_set;

import java.util.Random;

class randomTestData {
	private static final Random rand = new Random(0);

	int ops;
	int[] indexes;
	boolean[] expectedUnions;
	int[] expectedFinds;

	randomTestData(int n) {
		int[] par, size;
		par = new int[n];
		size = new int[n];
		for (var i = 0; i < n; i++) {
			par[i] = i;
			size[i] = 1;
		}

		ops = n + rand.nextInt(n*2);
		indexes = new int[ops*3];
		expectedUnions = new boolean[ops];
		expectedFinds = new int[ops];

		for (var op = 0; op < ops; op++) {
			int i = rand.nextInt(n), j = rand.nextInt(n), k = rand.nextInt(n);
			indexes[op*3] = i;
			indexes[op*3+1] = j;
			indexes[op*3+2] = k;

			i = find(par, i); j = find(par, j);
			if (i != j) {
				expectedUnions[op] = true;

				if (size[i] > size[j]) { par[j] = i; size[i] += size[j]; }
				else { par[i] = j; size[j] += size[i]; }
			}
			expectedFinds[op] = find(par, k);
		}
	}

	static int find(int[] par, int i) {
		while (i != par[i]) {
			var j = i;
			i = par[i];
			par[j] = par[i];
		}
		return i;
	}
}
