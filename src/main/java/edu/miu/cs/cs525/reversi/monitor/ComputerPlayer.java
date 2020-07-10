package edu.miu.cs.cs525.reversi.monitor;

import edu.miu.cs.cs525.reversi.algorithm.MiniMax;
import edu.miu.cs.cs525.reversi.common.*;
import edu.miu.cs.cs525.reversi.network.NetworkPlayer;

public class ComputerPlayer extends GeneralPlayer {
	static int[][] heuristic = { { 9, 2, 7, 8, 8, 7, 2, 9 }, { 2, 1, 3, 4, 4, 3, 1, 2 }, { 7, 3, 6, 5, 5, 6, 3, 7 },
			{ 8, 4, 5, 1, 1, 5, 4, 8 }, { 8, 4, 5, 1, 1, 5, 4, 8 }, { 7, 3, 6, 5, 5, 6, 3, 7 },
			{ 2, 1, 3, 4, 4, 3, 1, 2 }, { 9, 2, 7, 8, 8, 7, 2, 9 } };
	static int heuristicSum = 300;
	public Location getMove(BoardInfo b) {
		Location move = new Location(-1, -1); // Illegal Move

		// if( b.moveCount < 10 ) {
		// Opening moves
		// Trying to take most Strategic Square ( with maximum heuristic value )
		// matrix h = new matrix( heuristic ) ;
		// matrix m = b.getPossibleMovesMatrix() ;
		// matrix t = m.multiply( h ) ;
		// move = t.getMaxLoc() ;
		// }
		// else
		if (b.moveCount < 50) {
			// Mid Game moves
			// Using non-Perfect MiniMax of Depth 5
			String poss;
			String[] pl;
			poss = b.getPossibleMoves();
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
		} else {
			// End Game moves
			// Using Perfect MiniMax of Depth 10
			String poss;
			String[] pl;
			poss = b.getPossibleMoves();
			System.out.println("A " + poss);
			if (!poss.isEmpty()) {
				pl = poss.split(",");
				move.set(pl[0]); // I have at least one Move !
				if (pl.length > 1) {
					MiniMax alg = new MiniMax(10, b.turn, b, pl);
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
			}else {
				NetworkPlayer.getMove1();
			}
		}
		System.out.println(" => Selected Move for " + b.getTurnString() + " : " + move.getStandardForm());

		return move;
	}

	public String identify() throws Exception {
		return this.getClass().getName();
	}
}
