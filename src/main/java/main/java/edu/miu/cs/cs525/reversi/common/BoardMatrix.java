package main.java.edu.miu.cs.cs525.reversi.common;

import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;

public class BoardMatrix {
	public int numberOfRow = BoardEnum.ROW_COUNT.value();
	public int numberOfColumn = BoardEnum.COL_COUNT.value();
	private int[][] mat = new int[numberOfRow][numberOfColumn];

	private BoardMatrix() {
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				mat[i][j] = 0;
			}
		}
	}
	public static BoardMatrix boardMatrixFactory1(){
		return new BoardMatrix();
	}

	private BoardMatrix(BoardMatrix src) {
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				mat[i][j] = src.mat[i][j];
			}
		}
	}
	public static BoardMatrix boardMatrixFactory2(BoardMatrix src){
		return new BoardMatrix(src);
	}

	private BoardMatrix(int[][] src) {
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				mat[i][j] = src[i][j];
			}
		}
	}
	public static BoardMatrix boardMatrixFactory3(int[][] src){
		return new BoardMatrix(src);
	}


	public void set(int i, int j, int a) {
		mat[i][j] = a;
	}

	public int get(int i, int j) {
		return mat[i][j];
	}

	public void print() {
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public int getMax() {
		int max = mat[0][0];
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				if (mat[i][j] > max) {
					max = mat[i][j];
				}
			}
		}
		return max;
	}

	public Location getMaxLoc() {
		Location l = Location.locationFactory1();
		int max = mat[0][0];
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				if (mat[i][j] > max) {
					max = mat[i][j];
					l.row = i;
					l.column = j;
				}
			}
		}
		return l;
	}

	public BoardMatrix multiply(BoardMatrix b) {
		BoardMatrix r = new BoardMatrix();
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				r.mat[i][j] = mat[i][j] * b.mat[i][j];
			}
		}
		return r;
	}

	// public BoardMatrix getRandomMatrix(int min, int max) {
	// BoardMatrix m = new BoardMatrix();
	// Random rg = new Random(System.currentTimeMillis());
	// int i, j;
	// for (i = 0; i < numberOfRow; i++) {
	// for (j = 0; j < numberOfColumn; j++) {
	// m.mat[i][j] = min + rg.nextInt(max);
	// }
	// }
	// return m;
	// }

}
