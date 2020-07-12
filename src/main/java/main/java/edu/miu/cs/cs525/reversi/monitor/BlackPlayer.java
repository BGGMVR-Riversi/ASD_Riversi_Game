package main.java.edu.miu.cs.cs525.reversi.monitor;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;

public class BlackPlayer implements ShowCurrentPlayer {

	 @Override
	    public void display() {
	        ReversiSingleton.getBlackTurn().setVisible(true);
	        ReversiSingleton.getWhiteTurn().setVisible(false);
	    }
}
