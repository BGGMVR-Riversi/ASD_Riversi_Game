package main.java.edu.miu.cs.cs525.reversi.monitor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.AboutFormOkButtonActionAdapter;

public class BlackPlayer implements ShowCurrentPlayer {

	 @Override
	    public void display() {
	        ReversiSingleton.getBlackTurn().setVisible(true);
	        ReversiSingleton.getWhiteTurn().setVisible(false);
	    }

	@Override
	public void winner() {
		ReversiSingleton.getWinner().setVisible(true);
		ReversiSingleton.getGameOverLabel().setVisible(true);
		ReversiSingleton.getWinner().setText("<html><body><p>Black <br>is Winner !</p></body></html>");
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getWinner());
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getGameOverLabel());
	}

	@Override
	public void notDisplay() {
		ReversiSingleton.getBlackTurn().setVisible(false);
        ReversiSingleton.getWhiteTurn().setVisible(false);		
	}
}
