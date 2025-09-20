package topo_sort;

import java.util.*;

public class TopoSortKahn {
	// topoSort should perform topological sorting of a directed graph represented by its adjacency list.
	// The provided graph can contain duplicate links or links from a node to itself.
	// If graph is not a DAG (i.e. contains cycles) then the empty list should be returned.
	// Otherwise, returned array should contain node numbers as in the following example:
	//
	// Input:
	//
	// Adjacency list: [[2, 1], [], [1], [0, 2]]
	//
	//         0
	//    -----*<----
	//   |     |    |
	//   v     v    |
	//   *<---*<----*
	//   1    2    3
	//
 	// Expected output: [3, 0, 2, 1]
	//
	public static int[] topoSort(int[][] adjacencyList) {
//		throw new UnsupportedOperationException("TODO");

		var n = adjacencyList.length;
		var ins = new int[n];
		for (var row: adjacencyList) {
			for (var v: row) {
				ins[v]++;
			}
		}

		var order = new int[n];
		var m = 0;
		for (var u = 0; u < n; u++) {
			if (ins[u] == 0) {
				order[m++] = u;
			}
		}

		for (var i = 0; i < m; i++) {
			var u = order[i];
			for (var v: adjacencyList[u]) {
				if (--ins[v] == 0) {
					order[m++] = v;
				}
			}
		}

		if (m < n) {
			return new int[] {};
		}

		return order;
	}

}
