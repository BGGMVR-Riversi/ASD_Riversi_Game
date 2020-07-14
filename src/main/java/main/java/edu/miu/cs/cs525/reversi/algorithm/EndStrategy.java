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
//            if(pl.length==2){
//                NetworkPlayer.getMove1(pl[1]);
//                System.out.println("last of move"+ pl[1]);
//
//            }

            else if (pl.length <= 2) {
                for(int i=0;i<pl.length;i++) {
//                    move.set(pl[i]);
                    NetworkPlayer.getMove1(pl[i]);
                    System.out.println("last+moves "+ pl[i]);
                }

            }
        }
        else {
            NetworkPlayer.getEndMove();
        }
        return move;
    }
}
