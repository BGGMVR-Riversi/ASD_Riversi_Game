package main.java.edu.miu.cs.cs525.reversi.memento;

import main.java.edu.miu.cs.cs525.reversi.common.Location;

public class Board {
	private Location move;
	private static Board boardInstance;
	
	private Board(){}
	
    public static Board getInstance(){
        if(boardInstance == null){
            synchronized (Board.class){
                if(boardInstance == null){
                	boardInstance = new Board();
                }
            }
        }
        return boardInstance;
    }

	public BoardState createState() {
		return new BoardState(move);
	}
		
	public void restore(BoardState state) {
		move = state.getMove();
	}
		
	public Location getMove() {
		return move;
	}
		
	public void setMove(Location move) {
		this.move = move;
	}
}
