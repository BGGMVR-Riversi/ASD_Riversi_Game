package main.java.edu.miu.cs.cs525.reversi.algorithm;

import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.Location;

public interface MoveStrategy {
	public Location move(BoardInfo b, Location move);
}
