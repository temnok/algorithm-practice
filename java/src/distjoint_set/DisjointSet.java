package distjoint_set;

public class DisjointSet {
	private int[] par, size;

	// DisjointSet constructor should initialize a disjoint set
	public DisjointSet(int n) {
//		throw new UnsupportedOperationException("TODO");

		par = new int[n];
		size = new int[n];
		for (var i = 0; i < n; i++) {
			par[i] = i;
			size[i] = 1;
		}
	}

	// union should return false if elements i and j are already in the same set
	// or perform union operation on their sets and return true
	public boolean union(int i, int j) {
//		throw new UnsupportedOperationException("TODO");

		i = find(i);
		j = find(j);

		if (i == j) {
			return false;
		}

		if (size[i] < size[j]) {
			par[i] = j;
			size[j] += size[i];
		} else {
			par[j] = i;
			size[i] += size[j];
		}

		return true;
	}

	// find should return set ID: if find(x) == find(y), then both x and y are in the same set
	public int find(int i) {
//		throw new UnsupportedOperationException("TODO");

		while (i != par[i]) {
			var p = par[i];
			par[i] = par[p];
			i = p;
		}

		return i;
	}
}
