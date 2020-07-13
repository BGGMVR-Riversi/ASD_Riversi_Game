package main.java.edu.miu.cs.cs525.reversi.command;

import main.java.edu.miu.cs.cs525.reversi.action_adapters.BoardViewTemplate;
import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;

public class NewGameCommand implements Command {
	private BoardViewTemplate b;
	
	public NewGameCommand(BoardViewTemplate b) {
		this.b = b;
	}

	@Override
	public void execute() {
		b.newGame();
	}

}
