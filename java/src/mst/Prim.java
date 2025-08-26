package mst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Prim {
	// minimumSpanningForest should return subset of edges that form
	// "Minimum Spanning Forest" of the input *undirected* graph represented
	// by n nodes and provided list of edges. Each edge is represented by three
	// integers (u, v, w), where u and v are node indexes, and w is edge weight.
	//
	// Restrictions:
	// * Prim's algorithm should be used for implementation
	// * input graph may be unconnected
	// * edge (u, v, w) is equivalent to edge (v, u, w)
	// * n > 0, 0 <= u < n, 0 <= v < n, w > 0
	// * there can be multiple edges between same pair of nodes with same or different weights
	// * order of returned edges is ignored
	// * no duplicates are allowed in the returned list
	//
	public static int[][] minimumSpanningForest(int n, int[][] edges) {
//		throw new UnsupportedOperationException("TODO");

		List<int[]>[] graph = new List[n];
		for (var i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (var e: edges) {
			int u = e[0], v = e[1];
			graph[u].add(e);
			graph[v].add(e);
		}

		var ans = new ArrayList<int[]>();
		var heap = new ArrayList<int[]>();
		var connected = new boolean[n];

		for (var u = 0; u < n; u++) {
			if (connected[u]) continue;
			connected[u] = true;

			for (var e: graph[u]) {
				heapAdd(heap, e);
			}

			while (!heap.isEmpty()) {
				var e = heapRemove(heap);

				for (var j = 0; j < 2; j++) {
					var v = e[j];
					if (connected[v]) continue;
					connected[v] = true;

					ans.add(e);

					for (var ve: graph[v]) {
						heapAdd(heap, ve);
					}
				}
			}
		}

		return ans.toArray(new int[][]{});
	}

	static void heapAdd(List<int[]> heap, int[] edge) {
		heap.add(edge);
		heapUp(heap, heap.size()-1);
	}

	static int[] heapRemove(List<int[]> heap) {
		var f = heap.getFirst();
		var l = heap.removeLast();
		if (!heap.isEmpty()) {
			heap.set(0, l);
			heapDown(heap, 0);
		}

		return f;
	}

	static void heapUp(List<int[]> heap, int i) {
		var e = heap.get(i);

		for (var j = (i-1)/2; i > 0; i = j, j = (j-1)/2) {
			if (heap.get(j)[2] <= e[2]) break;

			heap.set(i, heap.get(j));
		}

		heap.set(i, e);
	}

	static void heapDown(List<int[]> heap, int i) {
		var e = heap.get(i);

		for (var j = i*2 + 1; j < heap.size(); i = j, j = j*2+1) {
			if (j+1 < heap.size() && heap.get(j+1)[2] < heap.get(j)[2]) {
				j++;
			}

			if (e[2] <= heap.get(j)[2]) break;

			heap.set(i, heap.get(j));
		}

		heap.set(i, e);
	}
}
