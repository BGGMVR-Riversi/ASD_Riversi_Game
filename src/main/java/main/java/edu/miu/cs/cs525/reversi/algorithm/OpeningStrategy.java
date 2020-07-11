package main.java.edu.miu.cs.cs525.reversi.algorithm;

import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.BoardMatrix;
import main.java.edu.miu.cs.cs525.reversi.common.Location;

public class OpeningStrategy implements MoveStrategy {
    static int[][] heuristic = { { 9, 2, 7, 8, 8, 7, 2, 9 }, { 2, 1, 3, 4, 4, 3, 1, 2 }, { 7, 3, 6, 5, 5, 6, 3, 7 },
            { 8, 4, 5, 1, 1, 5, 4, 8 }, { 8, 4, 5, 1, 1, 5, 4, 8 }, { 7, 3, 6, 5, 5, 6, 3, 7 },
            { 2, 1, 3, 4, 4, 3, 1, 2 }, { 9, 2, 7, 8, 8, 7, 2, 9 } };
    static int heuristicSum = 300;

    @Override
    public Location move(BoardInfo b, Location move) {
        BoardMatrix h = new BoardMatrix( heuristic );
        BoardMatrix m = b.getPossibleMovesMatrix();
        BoardMatrix t = m.multiply( h );
        move = t.getMaxLoc();
        return move;
    }
}
