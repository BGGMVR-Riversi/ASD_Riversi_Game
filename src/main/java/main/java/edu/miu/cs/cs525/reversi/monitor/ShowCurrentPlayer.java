package main.java.edu.miu.cs.cs525.reversi.monitor;

public interface ShowCurrentPlayer {

    void displayCurrentTurn();
    
    void displayWinner();
    
    void notDisplayCurrentTurn();
    
    void showPlayer(String s);
}
