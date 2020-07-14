package main.java.edu.miu.cs.cs525.reversi.memento;

import main.java.edu.miu.cs.cs525.reversi.common.Location;

public class BoardState {
	private final Location move;
	
	public BoardState(Location move) {
		this.move = move;
	}
	
	public Location getMove() {
		return move;
	}
}
