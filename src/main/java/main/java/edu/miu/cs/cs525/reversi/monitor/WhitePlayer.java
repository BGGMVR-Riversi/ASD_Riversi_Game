package main.java.edu.miu.cs.cs525.reversi.monitor;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;

public class WhitePlayer implements ShowCurrentPlayer {

    @Override
    public void display() {
        ReversiSingleton.getWhiteTurn().setVisible(true);
        ReversiSingleton.getBlackTurn().setVisible(false);
    }

	@Override
	public void winner() {
		ReversiSingleton.getWinner().setVisible(true);
		ReversiSingleton.getGameOverLabel().setVisible(true);
		ReversiSingleton.getWinner().setText("<html><body><p>White <br>is Winner !</p></body></html>");
		ReversiSingleton.getRightPane().add(ReversiSingleton.getWinner());
		ReversiSingleton.getLeftPane().add(ReversiSingleton.getGameOverLabel());
	}
	
	@Override
	public void notDisplay() {
		ReversiSingleton.getBlackTurn().setVisible(false);
        ReversiSingleton.getWhiteTurn().setVisible(false);		
	}
}
