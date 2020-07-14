package main.java.edu.miu.cs.cs525.reversi.algorithm;

import java.util.logging.Logger;

import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.Location;

public class MidStrategy implements MoveStrategy {
	Logger logger = Logger.getLogger(MidStrategy.class.getSimpleName());

	@Override
	public Location move(BoardInfo b, Location move) {
		String poss;
		String[] pl;
		MiniMax miniMax = new MiniMax(b);
		poss = miniMax.getPossibleMoves();
		pl = poss.split(",");
		move.set(pl[0]); // I have at least one Move !
		if (pl.length > 1) {
			MiniMax alg = new MiniMax(5 + (b.moveCount > 43 ? b.moveCount - 43 : 0), b.turn, b, pl);
			alg.run();
			int i;
			float max = -1000;
			for (i = 0; i < alg.moves.length; i++) {
				if (alg.moves[i] > max) {
					max = alg.moves[i];
					move.set(pl[i]);
				}
			}
		}
		// ************************************************************************************
		// Trying to take most Gaining Strategic Square ( with maximum heuristic * gain
		// value )
		// matrix h = new matrix( heuristic ) ;
		// matrix m = b.getGainMatrix() ;
		// matrix t = m.multiply( h ) ;
		// move = t.getMaxLoc() ;
		// ************************************************************************************
		return move;
	}
}
