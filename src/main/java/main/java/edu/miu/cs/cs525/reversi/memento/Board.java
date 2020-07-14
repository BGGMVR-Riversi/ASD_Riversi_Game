package main.java.edu.miu.cs.cs525.reversi.memento;

public class Board {
	private String move;
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
		
	public String getMove() {
		return move;
	}
		
	public void setMove(String move) {
		this.move = move;
	}
}
