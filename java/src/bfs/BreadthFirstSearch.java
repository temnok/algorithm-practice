package bfs;

import java.util.List;

public class BreadthFirstSearch {
	// dfs should visit graph nodes starting from node 0
	// using provided adjacency list and add the nodes in Breadth-First order to output list.
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
	// the result should be { 0, 1, 3, 2, 4, 6, 5, 7, 8 }
	public static void bfs(List<List<Integer>> adjacencyList, List<Integer> outputNodes) {
		throw new UnsupportedOperationException("TODO");
	}
}