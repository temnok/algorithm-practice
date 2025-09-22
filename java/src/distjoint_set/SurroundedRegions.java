package distjoint_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SurroundedRegions {
	public static final char X ='X';
	public static final char O ='O';

	public void solve(char[][] board) {
		int [] next = {-1, 1, - board[0].length, board[0].length};

		int maxI = board.length;
		int maxJ = board[0].length;

		int size = maxI * maxJ;
		boolean [] visited = new boolean[size];
		int [] queue = new int[size];
		int l = 0, r = 0;

		for (int i = 0; i < maxI; i++) {
			if (board[i][0] == O && !visited[i * maxJ]) {
				queue[r++] = i * maxJ;
				visited[i * maxJ] = true;
			}
			if (board[i][maxJ - 1] == O && !visited[i * maxJ + maxJ - 1]) {
				queue[r++] = i * maxJ + maxJ - 1;
				visited[i * maxJ + maxJ - 1] = true;
			}
		}

		for (int j = 0; j < maxJ; j++) {
			int index = j;
			if (board[0][j] == O && !visited[index]) {
				queue[r++] = index;
				visited[index] = true;
			}
			index = (maxI - 1) * maxJ + j;

			if (board[maxI - 1][j] == O && !visited[index]) {
				queue[r++] = index;
				visited[index] = true;
			}
		}

		while (l < r) {
			int cur = queue[l++];
			board[cur / maxJ][cur % maxJ] = 'S';

			for (int n : Arrays.stream(next).map(operand -> operand + cur)
				.filter(value -> value >= 0 && value < size)
				.filter(value -> !visited[value] && board[value / maxJ][value % maxJ] == O).toArray()) {
				queue[r++] = n;
				visited[n] = true;
			}
		}




	}
	public void solve1(char[][] board) {
		int maxJ = board[0].length;
		int maxI = board.length;
		DS ds = new DS(maxI * maxJ);

		for (int n = 0; n < maxI * maxJ; n++) {
			int i = n / maxJ;
			int j = n % maxJ;

			if (board[i][j] == X) {
				continue;
			}

			if (i > 0 && board[i - 1][j] == O) {
				ds.union(n, index(i - 1, j, maxJ));
			}
			if (i < maxI - 1 && board[i + 1][j] == O) {
				ds.union(n, index(i + 1, j, maxJ));
			}
			if (j > 0 && board[i][j - 1] == O) {
				ds.union(n, index(i, j - 1, maxJ));
			}
			if (j < maxJ - 1 && board[i][j + 1] == O) {
				ds.union(n, index(i, j + 1, maxJ));
			}
		}

		Set<Integer> pars = new HashSet<>();
		for (int j = 0; j < maxJ; j++) {
			if (board[0][j] == O) {
				pars.add(ds.find(index(0, j, maxJ)));
			}
			if (board[maxI - 1][j] == O) {
				pars.add(ds.find(index(maxI - 1, j, maxJ)));
			}
		}

		for (int i = 0; i < maxI; i++) {
			if (board[i][0] == O) {
				pars.add(ds.find(index(i, 0, maxJ)));
			}
			if (board[i][maxJ - 1] == O) {
				pars.add(ds.find(index(i, maxJ  - 1, maxJ)));
			}
		}

		for (int n = 0; n < ds.par.length; n++) {
			int i = n / maxJ;
			int j = n % maxJ;

			if (board[i][j] == O && !pars.contains(ds.find(n))) {
				board[i][j] = X;
			}
		}
	}

	private static int index(int i, int j, int maxJ) {
		return i * maxJ + j;
	}

	private static class DS {
		int [] par, size;

		DS(int n) {
			par = IntStream.range(0, n).toArray();
			size = new int[n];
			Arrays.fill(size, 1);
		}

		int find(int i) {
			while (par[i] != i) {
				int j = i;
				i = par[i];
				par[j] = par[i];
			}

			return i;
		}

		boolean union(int i, int j) {
			int parI = par[i];
			int parJ = par[j];

			if (parI == parJ) {
				return false;
			}

			if (size[parI] > size[parJ]) {
				par[parJ] = parI;
				size[parI] += size[parJ];
			} else {
				par[parI] = parJ;
				size[parJ] += size[parI];
			}

			return true;
		}
	}


	public static void main(String[] args) {

//		char[][] data = new char[][]{
//			{'X','O','X','O','X','O','O','O','X','O'},
//			{'X','O','O','X','X','X','O','O','O','X'},
//			{'O','O','O','O','O','O','O','O','X','X'},
//			{'O','O','O','O','O','O','X','O','O','X'},
//			{'O','O','X','X','O','X','X','O','O','O'},
//			{'X','O','O','X','X','X','O','X','X','O'},
//			{'X','O','X','O','O','X','X','O','X','O'},
//			{'X','X','O','X','X','O','X','O','O','X'},
//			{'O','O','O','O','X','O','X','O','X','O'},
//			{'X','X','O','X','X','X','X','O','O','O'}};
//		new SurroundedRegions().solve(data);
//
//		System.out.println();
	}
}
