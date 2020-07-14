package main.java.edu.miu.cs.cs525.reversi.memento;

public class BoardState {
	private final String move;
	
	public BoardState(String move) {
		this.move = move;
	}
	
	public String getMove() {
		return move;
	}
}
