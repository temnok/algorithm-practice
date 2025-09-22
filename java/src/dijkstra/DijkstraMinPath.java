package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DijkstraMinPath {
	// dijkstraMinPath should return min distance to all nodes from start node,
	// or -1 if node is unreachable from the start node.
	// edges contain triples [u, v, d], describing *directed* edge from node u to v with distance d (d > 0).
	// There can be multiple edges between same one or two nodes with different distances.
	// For example, if n = 4, edges = [[0, 2, 10], [0, 1, 100], [2, 1, 20]] and start = 0 then
	// result should be [0, 30, 10, -1]
	public static int[] dijkstraMinPath(int n, int[][] edges, int start) {
		int result [] = new int[n];
		Arrays.fill(result, -1);

		Node[] heap = IntStream.range(0, n).mapToObj(value -> new Node(value)).toArray(Node[]::new);
		for (int [] e : edges) {
			heap[e[0]].edges.add(new Edge(heap[e[1]], e[2]));
		}

		heap[start].distance = 0;
		heapUp(heap, heap[start]);

		while (heap[0].distance != Integer.MAX_VALUE) {
			Node cur = heap[0];
			for (Edge e : cur.edges) {
				if (!e.to.visited && e.to.distance > cur.distance + e.distance) {
					e.to.distance = cur.distance + e.distance;
					heapUp(heap, e.to);
				}
			}
			result[cur.id] = cur.distance;
			cur.visited = true;
			cur.distance = Integer.MAX_VALUE;
			heapDown(heap, cur, n);
		}

		return result;
	}

	private static void heapUp(Node[] heap, Node node) {
		int val = node.distance;
		int i = node.heapId;

		for (int j = (i - 1)/2; i > 0; i = j, j = (j - 1)/2) {
			if (val >= heap[j].distance) {
				break;
			}

			heap[i] = heap[j];
			heap[i].heapId = i;
		}

		heap[i] = node;
		heap[i].heapId = i;
	}

	private static void heapDown(Node[] heap, Node node, int n) {
		int val = node.distance;
		int i = node.heapId;

		for (int j = 2*i + 1; j < n; i = j, j = 2*j + 1) {
			if (j + 1 < n && heap[j + 1].distance < heap[j].distance) {
				j = j + 1;
			}

			if (val <= heap[j].distance) {
				break;
			}
			heap[i] = heap[j];
			heap[i].heapId = i;
		}

		heap[i] = node;
		heap[i].heapId = i;
	}

	private static class Node {
		public List<Edge> edges = new ArrayList<>();
		int id;
		int heapId;

		int distance = Integer.MAX_VALUE;

		boolean visited;

		public Node(int id) {
			this.id = id;
			this.heapId = id;
		}
	}

	private static class Edge {
		Node to;
		int distance;

		public Edge(Node i, int d) {
			this.to = i;
			this.distance = d;
		}
	}
}
