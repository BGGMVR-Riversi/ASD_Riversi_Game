package main.java.edu.miu.cs.cs525.reversi.monitor;

import main.java.edu.miu.cs.cs525.reversi.algorithm.EndStrategy;
import main.java.edu.miu.cs.cs525.reversi.algorithm.MidStrategy;
import main.java.edu.miu.cs.cs525.reversi.algorithm.MoveStrategy;
import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.GeneralPlayer;
import main.java.edu.miu.cs.cs525.reversi.common.Location;
import main.java.edu.miu.cs.cs525.reversi.memento.Board;
import main.java.edu.miu.cs.cs525.reversi.memento.History;

public class ComputerPlayer extends GeneralPlayer {

    public static int counterComputer =0;

    public Location getMove(BoardInfo b) {
		MoveStrategy strategy;
		Location move = Location.locationFactory3(-1, -1); // Illegal Move

		// if( b.moveCount < 10 ) {
		// Opening moves
		// Trying to take most Strategic Square ( with maximum heuristic value )
		// strategy = new OpeningStrategy();
		// strategy.move(b, move);
		// }
		// else
		if (b.moveCount < 50) {
			// Mid Game moves
			// Using non-Perfect MiniMax of Depth 5
			strategy = new MidStrategy();
			strategy.move(b, move);
		} else {
			// End Game moves
			// Using Perfect MiniMax of Depth 10
			strategy = new EndStrategy();
			strategy.move(b, move);
		}
		System.out.println(" => Selected Move for " + b.getTurnString() + " : " + move.getStandardForm());
		Board.getInstance().setMove(move);
		History.getInstance().pushToUndoList(Board.getInstance().createState());
		System.out.println("Selected move from Memento => " + Board.getInstance().getMove().getStandardForm());

           counterComputer++;
		System.out.println("movecount " + b.validMoveCount);
		return move;
	}


	public String identify() throws Exception {
		return this.getClass().getName();
	}
}
