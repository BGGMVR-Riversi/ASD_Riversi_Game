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
    public static ShowCurrentPlayer showCurrentPlayer;

    // Create Colors
    public static Color darkGray;
    public static Color aqua;
    
    public static String txtTeam4;
    
    // Left Side Pane
    public static JPanel leftSidePane;
    public static JLabel blackTurn;
    public static BlackPlayer blackPlayer;

    // Right Side Pane
    public static JPanel rightSidePane;
    public static JLabel whiteTurn;
    public static WhitePlayer whitePlayer;

    private ReversiSingleton(){}

    public static ReversiSingleton getInstance(){
        if(reversiSingletonObj == null){
            synchronized (ReversiSingleton.class){
                if(reversiSingletonObj == null){
                    reversiSingletonObj = new ReversiSingleton();
                    showCurrentPlayer = new BlackPlayer();
                    txtTeam4 = "Team 4";

                    darkGray = new Color(47,79,79);
                    aqua = new Color(0, 255, 255);

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
                }
            }
        }
        return reversiSingletonObj;
    }

}
