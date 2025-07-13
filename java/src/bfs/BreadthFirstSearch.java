package bfs;

import java.util.ArrayList;
import java.util.Arrays;
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
		int queue[] = new int[graph.size()];
		int l = 0, r = 0;
		boolean [] visited = new boolean[graph.size()];
		queue[r++] = start;
		visited[start] = true;
		List<Integer> result = new ArrayList<>();

		while (l < r) {
			int cur = queue[l++];
			result.addLast(cur);
			for (int i : graph.get(cur)) {
				if (!visited[i]) {
					queue[r++] = i;
					visited[i] = true;
				}
			}
		}
		return result;
	}
}
