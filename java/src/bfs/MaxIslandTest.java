package bfs;

import org.junit.Assert;
import org.junit.Test;

public class MaxIslandTest {
	@Test
	public void getMaxIslandNoLand() {
		boolean [][] l = new boolean[10][10];

		Assert.assertEquals("For empty labyrinth max island should be 1", 1, MaxIsland.getMaxIsland(l));
	}

	@Test
	public void  getMaxIslandOneIsland() {
		boolean [][] l = new boolean[10][10];

		l[3][2] = l[3][3] = l[3][4] = l[4][3] = l[4][4] = l[4][5] = l[5][2] = l[5][3] = true;

		Assert.assertEquals("Island should be 9", 9, MaxIsland.getMaxIsland(l));
	}

	@Test
	public void  getMaxIslandOneIslandWithPond() {
		boolean [][] l = new boolean[10][10];

		l[3][2] = l[3][3] = l[3][4] = l[4][2] = l[4][4] = l[5][2] = l[5][3] = l[5][4] = true;

		Assert.assertEquals("Island should be 9", 9, MaxIsland.getMaxIsland(l));
	}

	@Test
	public void  getMaxIslandTwoIslands() {
		boolean [][] l = new boolean[10][10];

		l[3][2] = l[3][3] = l[3][4] = l[4][3] = l[4][4] = l[4][5] = l[5][2] = l[5][3] = true;
		l[8][1] = l[8][2] = l[8][3] = l[8][4] = l[8][5] = l[8][6] = l[9][1] = l[9][2] = l[9][3] = l[9][4] = l[9][5] = true;

		Assert.assertEquals("Island should be 12", 12, MaxIsland.getMaxIsland(l));
	}

	@Test
	public void  getMaxIslandTwoIslandsAdded() {
		boolean [][] l = new boolean[10][10];

		l[3][2] = l[3][3] = l[3][4] = l[4][3] = l[4][4] = l[4][5] = l[5][2] = l[5][3] = true;
		l[7][1] = l[7][2] = l[7][3] = l[7][4] = l[7][5] = l[7][6] = l[8][1] = l[8][2] = l[8][3] = l[8][4] = l[8][5] = true;

		Assert.assertEquals("Island should be 20", 20, MaxIsland.getMaxIsland(l));
	}

	@Test
	public void  getMaxIslandFourIslandsAdded() {
		boolean [][] l = new boolean[10][10];

		l[2][5] = l[3][5] = true;
		l[4][3] = l[4][4] = true;
		l[5][5] = l[6][5] = true;
		l[4][6] = l[4][7] = true;

		Assert.assertEquals("Island should be 9", 9, MaxIsland.getMaxIsland(l));
	}

}
