package bfs;

import java.util.*;

public class MaxIsland {
	//in labyrinth isIsland=true;
	public static int getMaxIsland(boolean [][] labirinth) {
		int iMax = labirinth.length;
		int jMax = labirinth[0].length;

		boolean[] visited = new boolean[iMax*jMax];
		List<Island> islandsList = new ArrayList<>();

		for (int i = 0; i < labirinth.length; i++) {
			for (int j = 0; j < labirinth[0].length; j++) {
				if (labirinth[i][j] && !visited[calcIndex(i, j, jMax)]) {
					islandsList.add(createIsland(labirinth, visited, calcIndex(i, j, jMax)));
				}
			}
		}

		int[] sizes = new int[iMax*jMax];
		Arrays.fill(sizes, 1);
		int maxSize = 1;

		for (Island i : islandsList) {
			for (Integer border : i.borders) {
				sizes[border] += i.size;
				if (maxSize < sizes[border]) {
					maxSize = sizes[border];
				}
			}
		}

		return maxSize;
	}

	private static int calcIndex(int i, int j, int length) {
		return i*length + j;
	}

	private static class Island {
		int size;
		Set<Integer> borders = new HashSet<>();
	}

	private static Island createIsland(boolean[][] labirinth, boolean[] visited, int index) {
		List<Integer> queue = new ArrayList<>();
		Island island = new Island();
		queue.add(index);
		int maxJ = labirinth[0].length;
		visited[index] = true;
		island.size++;

		while (queue.size() > 0) {
			int cur = queue.removeFirst();

			for (int n : getNeibors(cur, labirinth.length, maxJ)) {
				if (labirinth[n/maxJ][n%maxJ] && !visited[n]) {
					queue.add(n);
					visited[n] = true;
					island.size++;
				}
				if (!labirinth[n/maxJ][n%maxJ]) {
					island.borders.add(n);
				}
			}
		}

		return island;
	}

	private static List<Integer> getNeibors(int cur, int maxI, int maxJ) {
		List<Integer> result = new ArrayList<>();
		int i = cur/ maxJ;
		int j = cur% maxJ;

		if (i > 0) {
			result.add(calcIndex(i - 1, j, maxJ));
		}
		if (i < maxI - 1) {
			result.add(calcIndex(i + 1, j, maxJ));
		}
		if (j > 0) {
			result.add(calcIndex(i, j - 1, maxJ));
		}
		if (j < maxJ - 1) {
			result.add(calcIndex(i, j + 1, maxJ));
		}

		return result;
	}

}
