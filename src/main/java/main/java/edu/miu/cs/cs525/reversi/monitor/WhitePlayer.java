package main.java.edu.miu.cs.cs525.reversi.monitor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;

public class WhitePlayer implements ShowCurrentPlayer {

    @Override
    public void displayCurrentTurn() {
        ReversiSingleton.getWhiteTurn().setVisible(true);
        ReversiSingleton.getBlackTurn().setVisible(false);
    }

	@Override
	public void displayWinner() {
		ReversiSingleton.getWinner().setVisible(true);
		ReversiSingleton.getGameOverLabel().setVisible(true);
		ReversiSingleton.getWinner().setText("<html><body><p>White <br>is Winner !</p></body></html>");
		ReversiSingleton.getRightPane().add(ReversiSingleton.getWinner());
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getGameOverLabel());
	}
	
	@Override
	public void notDisplayCurrentTurn() {
		ReversiSingleton.getBlackTurn().setVisible(false);
        ReversiSingleton.getWhiteTurn().setVisible(false);		
	}

	@Override
	public void showPlayer(String s) {
		ReversiSingleton.getLabelWhitePlayer().setText(s);
		ReversiSingleton.getRightPane().add(ReversiSingleton.getLabelOrForWhitePlayer());
		ReversiSingleton.getRightPane().add(ReversiSingleton.getLabelWhitePlayer());
	}
}
