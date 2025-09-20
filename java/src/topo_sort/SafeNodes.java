package topo_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafeNodes {
	public static List<Integer> getSafeNodes(int[][] graph) {
		List<Integer> result = new ArrayList<>();
		Type[] nodes = new Type[graph.length];
		Arrays.fill(nodes, Type.UNKNOWN);

		for (int i = 0; i < graph.length; i++) {
			addIfSafe(graph, nodes, result, i);
		}

		return result.stream().sorted().toList();
	}

	private static Type addIfSafe(int[][] graph, Type[] nodes, List<Integer> result, int i) {
		// entered method for second type meaning there is a loop
		if (nodes[i] == Type.PROCESSING) {
			nodes[i] = Type.UNSAFE;
		} else if (nodes[i] == Type.UNKNOWN) {
			nodes[i] = Type.PROCESSING;

			Type type = Type.SAFE;
			for (int c : graph[i]) {
				if (addIfSafe(graph, nodes, result, c) != Type.SAFE) {
					type = Type.UNSAFE;
				}
			}

			nodes[i] = type;

			if (nodes[i] == Type.SAFE) {
				result.add(i);
			}
		}

		return nodes[i];
	}

	private static enum Type {
		SAFE,
		UNSAFE,
		UNKNOWN,
		PROCESSING
	}
}
