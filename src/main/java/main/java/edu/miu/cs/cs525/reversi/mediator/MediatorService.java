package main.java.edu.miu.cs.cs525.reversi.mediator;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.common.AnimationMatrix;
import main.java.edu.miu.cs.cs525.reversi.common.BoardMatrix;
import main.java.edu.miu.cs.cs525.reversi.common.Location;
import main.java.edu.miu.cs.cs525.reversi.monitor.ShowCurrentPlayer;

public interface MediatorService {
	public void makeEmpty();
	public void initBoard();
	public void changeTurn();
	public boolean isValidMove(int r, int c);
	public AnimationMatrix calculateMoveAnimation(int r, int c);
	public int performMove(Location l);
	public String getTurnString();
	public int getPieceCount(int player);
	public BoardMatrix getGainMatrix();
	public BoardMatrix getPossibleMovesMatrix();
	public int correctTurn();
		
	public int countStablePieces(int player);
	public int countFrontier(int player);
	
	
	
	default void copyBoard(int[][] src, int[][] dest) {
		int i, j;
		for (i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			for (j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				dest[i][j] = src[i][j];
			}
		}
	}
	
	default void changePlayer(ShowCurrentPlayer showCurrentPlayer) {
		ReversiSingleton.setCurrentPlayer(showCurrentPlayer);
	}
}
