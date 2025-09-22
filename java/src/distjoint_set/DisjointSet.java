package distjoint_set;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DisjointSet {
	private int[] par, size;

	// DisjointSet constructor should initialize a disjoint set
	public DisjointSet(int n) {
//		throw new UnsupportedOperationException("TODO");

		par = IntStream.range(0, n).toArray();
		size = new int[n];
		Arrays.fill(size, 1);
	}

	// union should return false if elements i and j are already in the same set
	// or perform union operation on their sets and return true
	public boolean union(int i, int j) {
//		throw new UnsupportedOperationException("TODO");

		int parI = find(i), parJ = find(j);

		if (parI == parJ) {
			return false;
		}

		if (size[parI] >= size[parJ]) {
			par[parJ] = parI;
			size[parI] += size[parJ];
		} else {
			par[parI] = parJ;
			size[parJ] += size[parI];
		}

		return true;
	}

	// find should return set ID: if find(x) == find(y), then both x and y are in the same set
	public int find(int i) {
//		throw new UnsupportedOperationException("TODO");

		while (par[i] != i) {
			int j = i;
			i = par[i];
			par[j] = i;
		}

		return i;
	}
}
