package distjoint_set;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class FindAllPassagesTest {
	@Test
	public void testFindAllPassages() {
		int n = 5;
		boolean[][] lab = getRandomLabirinth(n);
		List<Cell> cellList = getCellList(n, lab);

		for (int i = 0; i < n*n; i++) {
			for (int j = 0; j < n*n; j++) {
				boolean expected = checkPassage(cellList, cellList.get(i), cellList.get(j));
				boolean actual = FindAllPassages.findPassages(lab, cellList.get(i).cell, cellList.get(j).cell);
				Assert.assertEquals(String.format("For start.i=%s, start.j=%s end.i=%s end.j=%s result is wrong, lab=%s", cellList.get(i).i,
						cellList.get(i).j, cellList.get(j).i, cellList.get(j).j, getLabString(lab)), expected,
					actual);
			}
		}
	}

	@Test
	public void testFindAllPassagesBfs() {
		int n = 5;
		boolean[][] lab = getRandomLabirinth(n);
		List<Cell> cellList = getCellList(n, lab);

		for (int i = 0; i < n*n; i++) {
			for (int j = 0; j < n*n; j++) {
				if (i == j) {
					continue;
				}
				boolean expected = checkPassage(cellList, cellList.get(i), cellList.get(j));
				boolean actual = FindAllPassagesBfs.findPassages(lab, cellList.get(i).cell, cellList.get(j).cell);
				Assert.assertEquals(String.format("For start.i=%s, start.j=%s end.i=%s end.j=%s result is wrong, lab=%s", cellList.get(i).i,
						cellList.get(i).j, cellList.get(j).i, cellList.get(j).j, getLabString(lab)), expected,
					actual);
			}
		}
	}

	@Test
	public void testFindAllPassageDfs() {
		int n = 5;
		boolean[][] lab = getRandomLabirinth(n);
		List<Cell> cellList = getCellList(n, lab);

		for (int i = 0; i < n*n; i++) {
			for (int j = 0; j < n*n; j++) {
				if (i == j) {
					continue;
				}
				boolean expected = checkPassage(cellList, cellList.get(i), cellList.get(j));
				boolean actual = FindAllPassagesDfs.findPassages(lab, cellList.get(i).cell, cellList.get(j).cell);
				Assert.assertEquals(String.format("For start.i=%s, start.j=%s end.i=%s end.j=%s result is wrong, lab=%s", cellList.get(i).i,
						cellList.get(i).j, cellList.get(j).i, cellList.get(j).j, getLabString(lab)), expected,
					actual);
			}
		}
	}

	private boolean[][] getRandomLabirinth(int n) {
		boolean [][] lab = new boolean[n][n];
		for (int i = 0; i < 5; i++) {
			lab[i] = getRandomBooleanArray(n);
		}
		return lab;
	}

	private List<Cell> getCellList(int n, boolean[][] lab) {
		Map<Cell, Cell> cells = new HashMap<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Cell cell = new Cell(i, j, lab[i][j]);
				cells.put(cell, cell);
			}
		}

		for (Cell c : cells.keySet()) {
			if (c.wall) {
				continue;
			}

			if (c.j > 0) {
				Cell left = cells.get(new Cell(c.i, c.j - 1));
				if (!left.wall) {
					left.neibors.add(c);
				}
			}

			if (c.j < n - 1) {
				Cell right = cells.get(new Cell(c.i, c.j + 1));
				if (!right.wall) {
					right.neibors.add(c);
				}
			}

			if (c.i > 0) {
				Cell top = cells.get(new Cell(c.i - 1, c.j));
				if (!top.wall) {
					top.neibors.add(c);
				}
			}

			if (c.i < n - 1) {
				Cell bottom = cells.get(new Cell(c.i + 1, c.j));
				if (!bottom.wall) {
					bottom.neibors.add(c);
				}
			}
		}

		return new ArrayList<>(cells.keySet());
	}

	private String getLabString(boolean[][] lab) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lab.length; i++) {
			sb.append(Arrays.toString(lab[i]));
		}
		return sb.toString();
	}

	private boolean checkPassage(List<Cell> cells, Cell start, Cell end) {
		Cell[] queue = new Cell[cells.size()];
		int l = 0, r = 0;
		queue[r++] = start;
		start.visited = true;

		while (l < r) {
			Cell cur = queue[l++];
			if (cur.equals(end)) {
				cells.forEach(cell -> cell.visited = false);
				return true;
			}

			for (Cell c : cur.neibors) {
				if (!c.visited) {
					queue[r++] = c;
					c.visited = true;

				}
			}
		}

		cells.forEach(cell -> cell.visited = false);
		return false;
	}

	private static class Cell {
		distjoint_set.Cell cell;

		int i;
		int j;
		boolean visited;

		boolean wall;

		List<Cell> neibors = new ArrayList<>();

		public Cell(int i, int j, boolean wall) {
			this.i = i;
			this.j = j;
			this.cell = new distjoint_set.Cell(i, j);
			this.wall = wall;
		}

		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass()) return false;
			Cell cell = (Cell) o;
			return i == cell.i && j == cell.j;
		}

		@Override
		public int hashCode() {
			return Objects.hash(i, j);
		}
	}

	private boolean[] getRandomBooleanArray(int n) {
		Random random = new Random();
		boolean[] result = new boolean[n];
		for (int i = 0; i < n; i++) {
			result[i] = random.nextBoolean();
		}

		return result;
	}


}
