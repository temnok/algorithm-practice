package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstraMinPath {
	// dijkstraMinPath should return min distance to all nodes from start node,
	// or -1 if node is unreachable from the start node.
	// edges contain triples [u, v, d], describing *directed* edge from node u to v with distance d (d > 0).
	// There can be multiple edges between same one or two nodes with different distances.
	// For example, if n = 4, edges = [[0, 2, 10], [0, 1, 100], [2, 1, 20]] and start = 0 then
	// result should be [0, 30, 10, -1]
	public static int[] dijkstraMinPath(int n, int[][] edges, int start) {
//		throw new UnsupportedOperationException("TODO");

		var heap = new Node[n];
		for (var i = 0; i < n; i++) {
			heap[i] = new Node(i);
		}

		for (var e: edges) {
			int u = e[0], v = e[1], l = e[2];
			heap[u].edges.add(new Edge(heap[v], l));
		}

		heap[start].dist = 0;
		heapUp(heap, heap[start]);

		var dist = new int[n];
		Arrays.fill(dist, -1);

		while (heap[0].dist < Integer.MAX_VALUE) {
			var u = heap[0];
			dist[u.i] = u.dist;

			for (var e: u.edges) {
				if (dist[e.v.i] < 0) {
					var d = u.dist + e.len;
					if (d < e.v.dist) {
						e.v.dist = d;
						heapUp(heap, e.v);
					}
				}
			}

			u.dist = Integer.MAX_VALUE;
			heapDown(heap, u);
		}

		return dist;
	}

	private static void heapUp(Node[] heap, Node cur) {
		var i = cur.heapI;

		for (var j = (i-1)/2; i > 0 && cur.dist < heap[j].dist; i = j, j = (j-1)/2) {
			heap[i] = heap[j];
			heap[i].heapI = i;
		}

		heap[i] = cur;
		cur.heapI = i;
	}

	private static void heapDown(Node[] heap, Node cur) {
		var i = cur.heapI;

		for (var j = i*2 + 1; j < heap.length; i = j, j = j*2 + 1) {
			if (j+1 < heap.length && heap[j+1].dist < heap[j].dist) {
				j++;
			}

			if (cur.dist <= heap[j].dist) break;

			heap[i] = heap[j];
			heap[i].heapI = i;
		}

		heap[i] = cur;
		cur.heapI = i;
	}
}

class Node {
	List<Edge> edges = new ArrayList<>();
	int i, heapI, dist;

	Node(int i) {
		this.i = i;
		this.heapI = i;
		this.dist = Integer.MAX_VALUE;
	}
}

class Edge {
	final Node v;
	final int len;

	Edge(Node v, int len) {
		this.v = v;
		this.len = len;
	}
}
