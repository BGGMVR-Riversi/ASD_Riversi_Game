package main.java.edu.miu.cs.cs525.reversi;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import main.java.edu.miu.cs.cs525.reversi.monitor.BlackPlayer;
import main.java.edu.miu.cs.cs525.reversi.monitor.BoardView;
import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;
import main.java.edu.miu.cs.cs525.reversi.monitor.MoveList;
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
    
    // Buttons
    private static JButton firstButton;
    private static JButton prevButton;
    private static JButton pauseButton;
    private static JButton nextButton;
    private static JButton lastButton;
    
    // BoardView
    private static BoardView b;
    
    // Main form and move list
    private static MainForm m;
    private static MoveList ml;
    
    // Radio Buttons
    private static JRadioButtonMenuItem menuBlackPlayerComputer;
    private static JRadioButtonMenuItem menuBlackPlayerHuman;
    private static JRadioButtonMenuItem menuWhitePlayerHuman;
	private static JRadioButtonMenuItem menuWhitePlayerComputer;
	
	// Check Box
	private static JCheckBoxMenuItem menuShowMoveList;
	
	// Players Label
	private static JLabel lblBlackPlayer;
	private static JLabel lblOrForBlackPlayer;
	private static JLabel lblWhitePlayer;
	private static JLabel lblOrForWhitePlayer;

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

                    blackTurn = new JLabel("Black Turn");
                    blackTurn.setForeground(aqua);
                    blackTurn.setVisible(false);
                    whiteTurn = new JLabel("White Turn");
                    whiteTurn.setForeground(aqua);
                    whiteTurn.setVisible(false);
                    
                    // Create object for each buttons
                    firstButton = new JButton();
                    prevButton = new JButton();
                    pauseButton = new JButton();
                    nextButton = new JButton();
                    lastButton = new JButton();    
                    
                    // Create object for radio buttons
                    menuBlackPlayerComputer = new JRadioButtonMenuItem("Computer");
                    menuBlackPlayerHuman = new JRadioButtonMenuItem("Human");
                    menuWhitePlayerComputer = new JRadioButtonMenuItem("Computer");
                    menuWhitePlayerHuman = new JRadioButtonMenuItem("Human");
                    
                    // Create object for check box
                    menuShowMoveList = new JCheckBoxMenuItem(" Show Move List");
                    
                    // Move list
                    ml = new MoveList("Move List", m);
                    
                    // Create object board view
                    b = new BoardView(ml, m, 1);
                    
                    // Main Form
                    m = new MainForm();
                    
                    // Player Label
                    lblBlackPlayer = new JLabel();
                    lblBlackPlayer.setForeground(aqua);
                    lblOrForBlackPlayer = new JLabel("or");
                    lblOrForBlackPlayer.setForeground(aqua);
                    lblWhitePlayer = new JLabel();
                    lblWhitePlayer.setForeground(aqua);
                    lblOrForWhitePlayer = new JLabel("or");
                    lblOrForWhitePlayer.setForeground(aqua);                    
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
    
    public static JButton getFirstButton() {
    	return firstButton;
    }
    
    public static JButton getPrevButton() {
    	return prevButton;
    }
    
    public static JButton getPauseButton() {
    	return pauseButton;
    }
    
    public static JButton getNextButton() {
    	return nextButton;
    }
    
    public static JButton getLastButton() {
    	return lastButton;
    }
    
    public static BoardView getBoardView() {
    	return b;
    }
    
    public static MoveList getMoveList() {
    	return ml;
    }
//    
    public static MainForm getMainForm() {
    	return m;
    }
    
    public static JRadioButtonMenuItem getMenuBlackPlayerComputer() {
    	return menuBlackPlayerComputer;
    }
    
    public static JRadioButtonMenuItem getMenuBlackPlayerHuman() {
    	return menuBlackPlayerHuman;
    }
    
    public static JRadioButtonMenuItem getMenuWhitePlayerComputer() {
    	return menuWhitePlayerComputer;
    }
    
    public static JRadioButtonMenuItem getMenuWhitePlayerHuman() {
    	return menuWhitePlayerHuman;
    }
    
    public static JCheckBoxMenuItem getMenuShowMoveList() {
    	return menuShowMoveList;
    }
    
    public static JLabel getLabelBlackPlayer() {
    	return lblBlackPlayer;
    }
    
    public static JLabel getLabelOrForBlackPlayer() {
    	return lblOrForBlackPlayer;
    }
    
    public static JLabel getLabelWhitePlayer() {
    	return lblWhitePlayer;
    }
    
    public static JLabel getLabelOrForWhitePlayer() {
    	return lblOrForWhitePlayer;
    }
}
