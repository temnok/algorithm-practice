package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

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
		boolean [] visited = new boolean[n];
		List<Edge>[] al = IntStream.range(0, n).mapToObj(ArrayList::new).toArray(List[]::new);

		for (int[] e : edges) {
			al[e[0]].add(new Edge(e[0], e[1], e[2]));
			al[e[1]].add(new Edge(e[0], e[1], e[2]));
		}

		Heap heap = new Heap(edges.length * 2);
		List<Edge> forest = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				for (Edge e : al[i]) {
					int to = e.i == i ? e.j : e.i;
					if (!visited[to]) {
						heap.add(e);
					}
				}

				while (!heap.isEmpty()) {
					Edge cur = heap.getFirst();
					int to = !visited[cur.i] ? cur.i : (!visited[cur.j] ? cur.j : -1);
					heap.removeFirst();
					if (to != -1) {
						forest.add(cur);
						visited[to] = true;
						for (Edge e : al[to]) {
							int to2 = e.i == to ? e.j : e.i;
							if (!visited[to2]) {
								heap.add(e);
							}
						}
					}

				}
			}
		}

		int [][] result = new int[forest.size()][3];
		for (int i = 0; i < forest.size(); i++) {
			Edge e = forest.get(i);
			result[i] = new int[] {e.i, e.j, e.d};
		}

		return result;
	}

	private static class Heap {
//		List<Edge> heap = new ArrayList<>();
		Edge[] heap;
		int n;

		Heap(int max) {
			heap = new Edge[max];
		}
		void add(Edge e) {
			if (n == heap.length) {
				throw new IllegalStateException();
			}
			heap[n++] = e;
			heapUp(n - 1);
		}

		void removeFirst() {
			if (n == 1) {
				n = 0;
				return;
			}

			heap[0] = heap[n - 1];
			n--;
			heapDown(0);
		}

		boolean isEmpty() {
			return n == 0;
		}

		Edge getFirst() {
			return heap[0];
		}

		void heapUp(int i) {
			Edge val = heap[i];

			for (int j = (i - 1)/2; i > 0; i= j, j = (j - 1)/2) {
				if (heap[j].d <= val.d) {
					break;
				}

				heap[i] = heap[j];
			}
			heap[i] = val;
		}

		void heapDown(int i) {
			Edge val = heap[i];

			for (int j = 2*i + 1; j < n; i = j, j = 2*j + 1) {
				if (j + 1 < n && heap[j + 1].d < heap[j].d) {
					j = j + 1;
				}

				if (heap[j].d >= val.d) {
					break;
				}

				heap[i] = heap[j];
			}

			heap[i] = val;

		}
	}

	private static class Edge {
		int i;
		int j;
		int d;

		public Edge(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
}
