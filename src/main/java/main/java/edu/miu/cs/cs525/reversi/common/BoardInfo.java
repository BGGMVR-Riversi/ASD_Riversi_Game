package main.java.edu.miu.cs.cs525.reversi.common;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;
import main.java.edu.miu.cs.cs525.reversi.mediator.MediatorService;

public class BoardInfo implements MediatorService {

	// Board :
	public int[][] board = new int[BoardEnum.ROW_COUNT.value()][BoardEnum.COL_COUNT.value()];

	// Which Player has Turn ? ( PLAYER_BLACK , PLAYER_WHITE , NO_GAME , GAME_OVER )
	public int turn;

	// Move History :
	public Location[] moveHistory = new Location[BoardEnum.MAX_MOVES.value() + 2];
	public int[] turnHistory = new int[BoardEnum.MAX_MOVES.value() + 2];
	public int[][][] boardHistory = new int[BoardEnum.MAX_MOVES.value()][BoardEnum.ROW_COUNT
			.value()][BoardEnum.COL_COUNT.value()];
	public int moveCount;
	public int validMoveCount;

	private BoardInfo() {
		int i;
		for (i = 0; i < BoardEnum.MAX_MOVES.value(); i++) {
			moveHistory[i] = Location.locationFactory1();
		}
		makeEmpty();
	}

	public static BoardInfo boardInfoFactory1() {
		return new BoardInfo();
	}

	private BoardInfo(BoardInfo src) {
		this();
		copyBoard(src.board, this.board);
		turn = src.turn;
		int i;
		for (i = 0; i < BoardEnum.MAX_MOVES.value(); i++) {
			moveHistory[i].set(src.moveHistory[i]);
			turnHistory[i] = src.turnHistory[i];
			copyBoard(src.boardHistory[i], this.boardHistory[i]);
		}
		moveCount = src.moveCount;
		validMoveCount = src.validMoveCount;
	}

	public static BoardInfo boardInfoFactory2(BoardInfo src) {
		return new BoardInfo(src);
	}

	@Override
	public void makeEmpty() {
		int i, j;
		for (i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			for (j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				board[i][j] = 0;
			}
		}
		moveCount = 0;
		validMoveCount = 0;
		turn = BoardEnum.NO_GAME.value();
	}

	@Override
	public void initBoard() {
		makeEmpty();
		board[3][3] = BoardEnum.PLAYER_WHITE.value();
		board[3][4] = BoardEnum.PLAYER_BLACK.value();
		board[4][4] = BoardEnum.PLAYER_WHITE.value();
		board[4][3] = BoardEnum.PLAYER_BLACK.value();
		turn = BoardEnum.PLAYER_BLACK.value();
	}

	private void goInADir(AnimationMatrix am, int rS, int cS, int rD, int cD) {
		int a = (turn == BoardEnum.PLAYER_BLACK.value() ? -1 : +1);
		int rN = rS + rD;
		int cN = cS + cD;
		if (rN < 0 || rN >= BoardEnum.ROW_COUNT.value() || cN < 0 || cN >= BoardEnum.COL_COUNT.value()
				|| board[rN][cN] == turn || board[rN][cN] == BoardEnum.EMPTY.value()) {
			return;
		}
		am.set(rN, cN, a);
		goInADir(am, rN, cN, rD, cD);
	}

	private boolean checkInADir(int rS, int cS, int rD, int cD) {
		int rN = rS + rD;
		int cN = cS + cD;
		if (rN < 0 || rN >= BoardEnum.ROW_COUNT.value() || cN < 0 || cN >= BoardEnum.COL_COUNT.value()
				|| board[rN][cN] == BoardEnum.EMPTY.value()) {
			return false;
		} else if (board[rN][cN] == turn && board[rS][cS] == turn) {
			return false;
		} else if (board[rN][cN] == turn) {
			return true;
		} else {
			return checkInADir(rN, cN, rD, cD);
		}
	}

	@Override
	public void changeTurn() {
		if (turn == BoardEnum.PLAYER_BLACK.value()) {
			turn = BoardEnum.PLAYER_WHITE.value();
		} else {
			turn = BoardEnum.PLAYER_BLACK.value();
		}
	}

	@Override
	public boolean isValidMove(int r, int c) {
		int iD, jD, dCount = 0;
		if (turn == BoardEnum.NO_GAME.value() || turn == BoardEnum.GAME_OVER.value()
				|| board[r][c] != BoardEnum.EMPTY.value()) {
			return false;
		}
		board[r][c] = turn;
		for (iD = -1; iD <= +1; iD++) {
			for (jD = -1; jD <= +1; jD++) {
				if (iD == 0 && jD == 0) {
					continue;
				}
				if (checkInADir(r, c, iD, jD)) {
					dCount++;
				}
			}
		}
		board[r][c] = BoardEnum.EMPTY.value();
		if (dCount == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public AnimationMatrix calculateMoveAnimation(int r, int c) {
		AnimationMatrix anim = AnimationMatrix.animationMatrixFactory1();
		int iD, jD, dCount = 0;

		if (turn == BoardEnum.NO_GAME.value() || turn == BoardEnum.GAME_OVER.value()
				|| board[r][c] != BoardEnum.EMPTY.value()) {
			return anim;
		}
		copyBoardToHistory(moveCount);
		board[r][c] = turn;
		for (iD = -1; iD <= +1; iD++) {
			for (jD = -1; jD <= +1; jD++) {
				if (iD == 0 && jD == 0) {
					continue;
				}
				if (checkInADir(r, c, iD, jD)) {
					dCount++;
					goInADir(anim, r, c, iD, jD);
				}
			}
		}
		if (dCount == 0) {
			board[r][c] = BoardEnum.EMPTY.value();
			return AnimationMatrix.animationMatrixFactory1();
		}
		moveHistory[moveCount].column = c;
		moveHistory[moveCount].row = r;
		turnHistory[moveCount] = turn;
		moveCount++;
		validMoveCount = moveCount;
		changeTurn();
		return anim;
	}

	private int performMove(int r, int c) {
		// Performs a move with a trick :
		// calculates move animation & then performs animation at once !
		AnimationMatrix am = calculateMoveAnimation(r, c);
		am.perform(this, 30);
		return correctTurn();
	}

	@Override
	public int performMove(Location l) {
		return performMove(l.row, l.column);
	}

	@Override
	public String getTurnString() {
		if (turn == BoardEnum.PLAYER_BLACK.value()) {
			changePlayer(ReversiSingleton.getBlackPlayer());
			ReversiSingleton.getCurrentPlayer().displayCurrentTurn();
			return "Black's Turn";
		} else if (turn == BoardEnum.PLAYER_WHITE.value()) {
			changePlayer(ReversiSingleton.getWhitePlayer());
			ReversiSingleton.getCurrentPlayer().displayCurrentTurn();
			return "White's Turn";
		} else if (turn == BoardEnum.NO_GAME.value()) {
			return "";
		} else if (turn == BoardEnum.GAME_OVER.value()) {
			ReversiSingleton.getCurrentPlayer().notDisplayCurrentTurn();
			return "Game Over !!";
		} else {
			return "";
		}
	}

	@Override
	public int getPieceCount(int player) {
		int c = 0;
		int i, j;
		for (i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			for (j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				if (board[i][j] == player) {
					c++;
				}
			}
		}
		return c;
	}

	private int countInADir(int rS, int cS, int rD, int cD) {
		int rN = rS + rD;
		int cN = cS + cD;
		if (rN < 0 || rN >= BoardEnum.ROW_COUNT.value() || cN < 0 || cN >= BoardEnum.COL_COUNT.value()
				|| board[rN][cN] == BoardEnum.EMPTY.value()) {
			return 0;
		} else if (board[rN][cN] == turn && board[rS][cS] == turn) {
			return 0;
		} else if (board[rN][cN] == turn) {
			return 0;
		} else {
			return 1 + countInADir(rN, cN, rD, cD);
		}
	}

	@Override
	public BoardMatrix getGainMatrix() {
		BoardMatrix m = BoardMatrix.boardMatrixFactory1();
		int r, c, iD, jD;

		if (turn == BoardEnum.NO_GAME.value() || turn == BoardEnum.GAME_OVER.value()) {
			return m; // No Game
		}
		for (r = 0; r < BoardEnum.ROW_COUNT.value(); r++) {
			for (c = 0; c < BoardEnum.COL_COUNT.value(); c++) {
				if (board[r][c] != BoardEnum.EMPTY.value()) {
					m.set(r, c, -1);
				} else {
					board[r][c] = turn;
					for (iD = -1; iD <= +1; iD++) {
						for (jD = -1; jD <= +1; jD++) {
							if (iD == 0 && jD == 0) {
								continue;
							}
							if (checkInADir(r, c, iD, jD)) {
								m.set(r, c, m.get(r, c) + countInADir(r, c, iD, jD));
							}
						}
					}
					board[r][c] = BoardEnum.EMPTY.value();
				}
			}
		}
		return m;
	}

	@Override
	public BoardMatrix getPossibleMovesMatrix() {
		BoardMatrix m = getGainMatrix();
		int i, j;
		for (i = 0; i < m.numberOfRow; i++) {
			for (j = 0; j < m.numberOfColumn; j++) {
				if (m.get(i, j) > 0) {
					m.set(i, j, 1);
				} else {
					m.set(i, j, 0);
				}
			}
		}
		return m;
	}

	@Override
	public int correctTurn() {
		BoardMatrix m = getGainMatrix();
		if (m.getMax() > 0) {
			return 0; // No Correction
		}
		changeTurn();
		m = getGainMatrix();
		if (m.getMax() > 0) {
			return 1; // 1 Correction
		}
		turn = BoardEnum.GAME_OVER.value();
		return 2; // Game is Over
	}

	// Move History methods :

	private void copyBoardToHistory(int k) {
		copyBoard(this.board, this.boardHistory[k]);
	}

	private void copyHistoryToBoard(int k) {
		copyBoard(this.boardHistory[k], this.board);
	}
	@Override
	public boolean takeBackOneMove() {
		if (moveCount <= 0) {
			return false;
		}
		moveCount--;
		copyHistoryToBoard(moveCount);
		turn = turnHistory[moveCount];
		return true;
	}
	@Override
	public void takeBackAllMoves() {
		if (validMoveCount <= 0) {
			return;
		}
		moveCount = 0;
		copyHistoryToBoard(moveCount);
		turn = turnHistory[moveCount];
	}
	@Override
	public boolean redoOneMove() {
		if (validMoveCount <= 0) {
			return false;
		}
		if (moveCount == validMoveCount) {
			return false;
		}
		if (moveCount == validMoveCount - 1) {
			performMove(moveHistory[moveCount].row, moveHistory[moveCount].column);
		} else {
			moveCount++;
			copyHistoryToBoard(moveCount);
			turn = turnHistory[moveCount];
		}
		return true;
	}
	@Override
	public void redoAllMoves() {
		if (validMoveCount <= 0) {
			return;
		}
		moveCount = validMoveCount - 1;
		copyHistoryToBoard(moveCount);
		turn = turnHistory[moveCount];
		redoOneMove();
	}
	@Override
	public String getStandardFormGame() {
		String s = "";
		int i;
		for (i = 0; i < moveCount; i++) {
			s = s + moveHistory[i].getStandardForm();
			s = s + " ";
		}
		return s;
	}
	@Override
	public void setStandardFormGame(String s) {
		String[] sa = s.split(" ");
		int i;
		initBoard();
		for (i = 0; i < sa.length; i++) {
			if (sa[i].length() == 2) {
				performMove(new Location(sa[i].toUpperCase()));
			}
		}
	}

	@Override
	public void showBoard() {
		System.out.println("Current Board Position :");
		System.out.println("  A B C D E F G H");
		int i, j;
		for (i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			System.out.print((i + 1) + " ");
			for (j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				if (board[i][j] == BoardEnum.EMPTY.value()) {
					System.out.print("- ");
				} else if (board[i][j] == BoardEnum.PLAYER_BLACK.value()) {
					System.out.print("X ");
				} else if (board[i][j] == BoardEnum.PLAYER_WHITE.value()) {
					System.out.print("O ");
				} else {
					System.out.print("? ");
				}
			}
			System.out.println();
		}
	}

	private boolean isEmpty(int i, int j) {
		if (i < 0 || i >= BoardEnum.ROW_COUNT.value() || j < 0 || j >= BoardEnum.COL_COUNT.value()) {
			return false;
		} else if (board[i][j] == BoardEnum.EMPTY.value()) {
			return true;
		} else {
			return false;
		}
	}

	private int countCornerStablePieces(int player, Location corner, int rMAx, int cMax) {
		int i, j, c = 0, rm = 0, cm = 0, rec = 0;
		boolean e;
		Location dir = Location.locationFactory3((corner.row <= BoardEnum.ROW_COUNT.value() / 2 ? +1 : -1),
				(corner.column <= BoardEnum.COL_COUNT.value() / 2 ? +1 : -1));
		if (board[corner.row][corner.column] == player) {
			c++;
			i = corner.row + dir.row;
			e = false;
			while (!e) {
				if (dir.row == +1 && i >= rMAx) {
					break;
				}
				if (dir.row == -1 && i <= rMAx) {
					break;
				}
				if (board[i][corner.column] == player) {
					c++;
					rec = 1;
					rm = i;
				} else {
					e = true;
				}
				i = i + dir.row;
			}
			j = corner.column + dir.column;
			e = false;
			while (!e) {
				if (dir.column == +1 && j >= cMax) {
					break;
				}
				if (dir.column == -1 && j <= cMax) {
					break;
				}
				if (board[corner.row][j] == player) {
					c++;
					rec++;
					cm = j;
				} else {
					e = true;
				}
				j = j + dir.column;
			}
			if (rec > 1) {
				Location newCorner = Location.locationFactory1();
				newCorner.row = corner.row + dir.row;
				newCorner.column = corner.column + dir.column;
				c += countCornerStablePieces(player, newCorner, rm, cm);
			}
		}
		return c;
	}

	private int countRepetitions(int player, Location corner1, Location corner2, int n) {
		int c = 0;
		Location dir = Location.locationFactory1();
		Location iter = Location.locationFactory1();
		if (corner1.row == corner2.row) {
			dir.set(0, 1);
		} else {
			dir.set(1, 0);
		}
		for (iter.set(corner1); iter.row <= corner2.row
				&& iter.column <= corner2.column; iter = Location.add(iter, dir)) {
			if (board[iter.row][iter.column] == player) {
				c++;
			} else {
				return 0;
			}
		}
		if (n < 3) {
			int tmp = dir.row;
			dir.row = dir.column;
			dir.column = tmp;
			if (corner2.row == 7 && corner2.column == 7) {
				dir.row = -dir.row;
				dir.column = -dir.column;
			}
			tmp = countRepetitions(player, Location.add(corner1, dir), Location.add(corner2, dir), n + 1);
			if (tmp > 0) {
				c += tmp - 2 * n;
			}
		}
		return c;
	}

	@Override
	public int countStablePieces(int player) {
		int sum = 0;
		sum += countCornerStablePieces(player, Location.locationFactory3(0, 0), BoardEnum.ROW_COUNT.value(),
				BoardEnum.COL_COUNT.value());
		sum += countCornerStablePieces(player, Location.locationFactory3(0, 7), BoardEnum.ROW_COUNT.value(), -1);
		sum += countCornerStablePieces(player, Location.locationFactory3(7, 0), -1, BoardEnum.COL_COUNT.value());
		sum += countCornerStablePieces(player, Location.locationFactory3(7, 7), -1, -1);
		sum -= countRepetitions(player, Location.locationFactory3(0, 0), Location.locationFactory3(0, 7), 1);
		sum -= countRepetitions(player, Location.locationFactory3(0, 0), Location.locationFactory3(7, 0), 1);
		sum -= countRepetitions(player, Location.locationFactory3(0, 7), Location.locationFactory3(7, 7), 1);
		sum -= countRepetitions(player, Location.locationFactory3(7, 0), Location.locationFactory3(7, 7), 1);
		return sum;
	}

	@Override
	public int countFrontier(int player) {
		int f = 0;
		int i, j;
		for (i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			for (j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				if (board[i][j] == player) {
					if (isEmpty(i + 1, j)) {
						f++;
					}
					if (isEmpty(i - 1, j)) {
						f++;
					}
					if (isEmpty(i, j + 1)) {
						f++;
					}
					if (isEmpty(i, j - 1)) {
						f++;
					}
					if (isEmpty(i + 1, j + 1)) {
						f++;
					}
					if (isEmpty(i + 1, j - 1)) {
						f++;
					}
					if (isEmpty(i - 1, j + 1)) {
						f++;
					}
					if (isEmpty(i - 1, j - 1)) {
						f++;
					}
				}
			}
		}
		return f;
	}

}
