package main.java.edu.miu.cs.cs525.reversi.algorithm;

import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.Location;
import main.java.edu.miu.cs.cs525.reversi.network.NetworkPlayer;

public class EndStrategy implements MoveStrategy {
    @Override
    public Location move(BoardInfo b, Location move) {
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
            NetworkPlayer.getEndMove();
        }
        return move;
    }
}
