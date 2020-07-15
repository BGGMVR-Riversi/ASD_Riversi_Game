package main.java.edu.miu.cs.cs525.reversi.common;

import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;

public class AnimationMatrix {
	public int numberOfRow = BoardEnum.ROW_COUNT.value();
	public int numberOfColumn = BoardEnum.COL_COUNT.value();

	private int[][] mat = new int[numberOfRow][numberOfColumn];

	private AnimationMatrix() {
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				mat[i][j] = 0;
			}
		}
	}


	private AnimationMatrix(AnimationMatrix src) {
		int i, j;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				mat[i][j] = src.mat[i][j];
			}
		}
	}

	public static AnimationMatrix animationMatrixFactory1(){
		return new AnimationMatrix();
	}
	public static AnimationMatrix animationMatrixFactory2(AnimationMatrix src){
		return new AnimationMatrix(src);
	}

	public void set(int i, int j, int a) {
		if (a == -1 || a == 0 || a == +1) {
			mat[i][j] = a;
		}
	}

	public int get(int i, int j) {
		return mat[i][j];
	}

	public int perform(BoardInfo board, int animationSpeed) {
		int i, j, a, b, count = 0;
		for (i = 0; i < numberOfRow; i++) {
			for (j = 0; j < numberOfColumn; j++) {
				a = get(i, j);
				b = board.board[i][j];
				if (a == +1 && b < 31) {
					board.board[i][j] += a * animationSpeed;
				} else if (a == +1 && b == 31) {
					count++;
				} else if (a == -1 && b > 1) {
					board.board[i][j] += a * animationSpeed;
				} else if (a == -1 && b == 1) {
					count++;
				} else {
					count++;
				}
			}
		}
		return count;
	}

}
