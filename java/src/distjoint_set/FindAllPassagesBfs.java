package distjoint_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllPassagesBfs {
	public static boolean findPassages(boolean [][] labyrinth, Cell start, Cell end) {
		throw new UnsupportedOperationException();
	}

	private static int color(boolean [][] labyrinth, int[] passages, int cell, int color) {
		if (passages[cell] != 0) {
			return color;
		}
		int maxJ = labyrinth[0].length;
		int i = calcI(cell, maxJ);
		int j = calcJ(cell, maxJ);

		if (labyrinth[i][j]) {
			passages[cell] = -1;
			return color;
		}

		int [] queue = new int[passages.length];
		int l = 0, r = 0;
		queue[r++] = cell;
		passages[cell] = color;

		while (l < r) {
			int cur = queue[l++];

			for (int n : getNeibors(labyrinth, cur)) {
				if (passages[n] == 0) {
					queue[r++] = n;
					passages[n] = color;
				}
			}
		}

		return color + 1;
	}

	private static List<Integer> getNeibors(boolean[][] labyrinth, int c) {
		int maxJ = labyrinth[0].length;
		int i = calcI(c, maxJ);
		int j = calcJ(c, maxJ);
		List<Integer> result = new ArrayList<>();

		if (i > 0 && !labyrinth[i - 1][j]) {
			result.add(calcIndex(i - 1, j, maxJ));
		}

		if (i < labyrinth.length - 1 && !labyrinth[i + 1][j]) {
			result.add(calcIndex(i + 1, j, maxJ));
		}

		if (j > 0 && !labyrinth[i][j - 1]) {
			result.add(calcIndex(i, j - 1, maxJ));
		}

		if (j < maxJ - 1 && !labyrinth[i][j + 1]) {
			result.add(calcIndex(i, j + 1, maxJ));
		}

		return result;
	}

	private static int calcIndex(int i, int j, int maxJ) {
		return i*maxJ + j;
	}

	private static int calcI(int index, int maxJ) {
		return index/maxJ;
	}

	private static int calcJ(int index, int maxJ) {
		return index%maxJ;
	}

}
