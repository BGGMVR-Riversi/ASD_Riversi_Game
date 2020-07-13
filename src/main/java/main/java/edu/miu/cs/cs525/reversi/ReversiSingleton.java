package main.java.edu.miu.cs.cs525.reversi;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.edu.miu.cs.cs525.reversi.monitor.BlackPlayer;
import main.java.edu.miu.cs.cs525.reversi.monitor.ShowCurrentPlayer;
import main.java.edu.miu.cs.cs525.reversi.monitor.WhitePlayer;

public class ReversiSingleton {
	
	private static ReversiSingleton reversiSingletonObj;
    private static ShowCurrentPlayer showCurrentPlayer;
    private static JLabel winner;
    private static JLabel gameOver;

    // Create Colors
    private static Color darkGray;
    private static Color aqua;
    
//    private static String txtTeam4;
    
    // Left Side Pane
    private static JPanel leftSidePane;
    private static JLabel blackTurn;
    private static BlackPlayer blackPlayer;

    // Right Side Pane
    private static JPanel rightSidePane;
    private static JLabel whiteTurn;
    private static WhitePlayer whitePlayer;

    private ReversiSingleton(){}

    public static ReversiSingleton getInstance(){
        if(reversiSingletonObj == null){
            synchronized (ReversiSingleton.class){
                if(reversiSingletonObj == null){
                    reversiSingletonObj = new ReversiSingleton();
                    showCurrentPlayer = new BlackPlayer();
                    winner = new JLabel();
                    gameOver = new JLabel();
//                    txtTeam4 = "Team 4";

                    darkGray = new Color(47,79,79);
                    aqua = new Color(0, 255, 255);
                    winner.setForeground(aqua);
                    gameOver.setForeground(aqua);
                    gameOver.setText("Game Over");

                    leftSidePane = new JPanel(new GridLayout(10,1));
                    leftSidePane.setBackground(darkGray);
                    blackPlayer = new BlackPlayer();

                    rightSidePane = new JPanel(new GridLayout(10,1));
                    rightSidePane.setBackground(darkGray);
                    whitePlayer = new WhitePlayer();

                    blackTurn = new JLabel("Black's Turn");
                    blackTurn.setForeground(aqua);
                    blackTurn.setVisible(false);
                    whiteTurn = new JLabel("White's Turn");
                    whiteTurn.setForeground(aqua);
                    whiteTurn.setVisible(false);
                }
            }
        }
        return reversiSingletonObj;
    }
    
    public static ShowCurrentPlayer getCurrentPlayer() {
    	return showCurrentPlayer;
    }
    
    public static void setCurrentPlayer(ShowCurrentPlayer showCurrentPlayer) {
    	ReversiSingleton.showCurrentPlayer = showCurrentPlayer;
    }
    
    public static BlackPlayer getBlackPlayer() {
    	return blackPlayer;
    }
    
    public static WhitePlayer getWhitePlayer() {
    	return whitePlayer;
    }
    
    public static Color getDarkGray() {
    	return darkGray;
    }
    
    public static Color getAqua() {
    	return aqua;
    }
    
    public static JPanel getLeftPane() {
    	return leftSidePane;
    }
    
    public static JLabel getBlackTurn() {
    	return blackTurn;
    }
    
    public static JPanel getRightPane() {
    	return rightSidePane;
    }
    
    public static JLabel getWhiteTurn() {
    	return whiteTurn;
    }
    
    public static JLabel getWinner() {
    	return winner;
    }
    
    public static JLabel getGameOverLabel() {
    	return gameOver;
    }
}
