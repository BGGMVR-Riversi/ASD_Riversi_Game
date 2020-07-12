package main.java.edu.miu.cs.cs525.reversi.monitor;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;

public class WhitePlayer implements ShowCurrentPlayer {

    @Override
    public void display() {
        ReversiSingleton.getWhiteTurn().setVisible(true);
        ReversiSingleton.getBlackTurn().setVisible(false);
    }
}
