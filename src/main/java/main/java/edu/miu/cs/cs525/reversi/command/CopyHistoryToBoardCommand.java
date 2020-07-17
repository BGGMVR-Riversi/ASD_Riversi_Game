package main.java.edu.miu.cs.cs525.reversi.command;

import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;

public class CopyHistoryToBoardCommand implements Command {
	private BoardInfo b;
	private int k;
	
	public CopyHistoryToBoardCommand(BoardInfo b) {
		this.b = b;
	}
	
	public int getK() {
		return k;
	}
	
	public void setK(int k) {
		this.k = k;
	}

	@Override
	public void execute() {
		b.copyBoardToHistory(k);
	}
}
