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
			int j = rand.nextInt(n), i = rand.nextInt(n), k = rand.nextInt(n);
			indexes[op*3] = j;
			indexes[op*3+1] = i;
			indexes[op*3+2] = k;

			j = find(par, j); i = find(par, i);
			if (j != i) {
				expectedUnions[op] = true;

				if (size[j] >= size[i]) { par[i] = j; size[j] += size[i]; }
				else { par[j] = i; size[i] += size[j]; }
			}
			expectedFinds[op] = find(par, k);
		}
	}

	static int find(int[] par, int i) {
		while (i != par[i]) {
			var p = par[i];
			par[i] = par[p];
			i = p;
		}

		return i;
	}
}
