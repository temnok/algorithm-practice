package bfs;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch {
	// dfs should visit graph nodes starting from node start
	// using provided adjacency list and add the nodes in Breadth-First order to the output list.
	// For example, for the given adjacency list
	//
	// { 0:{1, 3}, 1:{2,4}, 2:{5}, 3:{4,6}, 4:{5,7}, 5:{8}, 6:{7}, 7:{8}, 8:{2} }
	//
	// 0->1->2<-
	// v  v  v |
	// 3->4->5 |
	// v  v  v |
	// 6->7->8-
	//
	// and start node 0, the result should be { 0, 1, 3, 2, 4, 6, 5, 7, 8 }
	public static List<Integer> bfs(List<List<Integer>> graph, int start) {
//		throw new UnsupportedOperationException("TODO");

		var q = new ArrayList<Integer>();
		var visited = new boolean[graph.size()];

		q.add(start);
		visited[start] = true;

		for (var i = 0; i < q.size(); i++) {
			var u = q.get(i);
			for (var v: graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					q.add(v);
				}
			}
		}

		return q;
	}
}
