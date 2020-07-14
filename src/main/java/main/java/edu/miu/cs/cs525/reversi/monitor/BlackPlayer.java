package main.java.edu.miu.cs.cs525.reversi.monitor;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;

public class BlackPlayer implements ShowCurrentPlayer {

	@Override
	public void displayCurrentTurn() {
		ReversiSingleton.getBlackTurn().setVisible(true);
		ReversiSingleton.getWhiteTurn().setVisible(false);
	}

	@Override
	public void displayWinner() {
		ReversiSingleton.getWinner().setVisible(true);
		ReversiSingleton.getGameOverLabel().setVisible(true);
		ReversiSingleton.getWinner().setText("<html><body><p>Black <br>is Winner !</p></body></html>");
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getWinner());
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getGameOverLabel());
	}

	@Override
	public void notDisplayCurrentTurn() {
		ReversiSingleton.getBlackTurn().setVisible(false);
		ReversiSingleton.getWhiteTurn().setVisible(false);
	}

	@Override
	public void showPlayer(String s) {
		ReversiSingleton.getLabelBlackPlayer().setText(s);
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getLabelOrForBlackPlayer());
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getLabelBlackPlayer());
	}
}
