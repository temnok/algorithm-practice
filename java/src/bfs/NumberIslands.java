package bfs;

import java.util.ArrayList;
import java.util.List;

public class NumberIslands {
	public int numIslands(char[][] grid) {
		int color = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				color = color(grid, index(i, j, grid[0].length), color);
			}
		}
		return color;
	}

	private int color(char[][] grid, int n, int color) {
		int maxI = grid.length;
		int maxJ = grid[0].length;

		if (grid[n / maxJ][n % maxJ] != '1') {
			return color;
		}
		color++;

		int [] queue = new int [maxI * maxJ];
		int l = 0, r = 0;
		queue[r++] = n;
		grid[n / maxJ][n % maxJ] = '2';

		while (l < r) {
			int cur = queue[l++];
			for (int i : getNeibors(cur/maxJ, cur%maxJ, maxI, maxJ)) {
				if (grid[i/maxJ][i%maxJ] == '1') {
					queue[r++] = i;
					grid[i/maxJ][i%maxJ] = (char) ('1' + color);
				}
			}
		}

		return color;
	}

	private List<Integer> getNeibors(int i, int j, int maxI, int maxJ) {
		List<Integer> result = new ArrayList<>();

		if (i > 0) {
			result.add(index(i - 1, j, maxJ));
		}
		if (i < maxI - 1) {
			result.add(index(i + 1, j, maxJ));
		}
		if (j > 0) {
			result.add(index(i, j - 1, maxJ));
		}
		if (j < maxJ - 1) {
			result.add(index(i, j + 1, maxJ));
		}

		return result;
	}

	private int index(int i, int j, int maxJ) {
		return i * maxJ + j;
	}

	public static void main(String[] args) {
		char[][] grid = new char[][] {
			{'1','1','0','0','0'},
			{'1','1','0','0','0'},
			{'0','0','1','0','0'},
			{'0','0','0','1','1'}};
		new NumberIslands().numIslands(grid);
		System.out.println();
	}

}
