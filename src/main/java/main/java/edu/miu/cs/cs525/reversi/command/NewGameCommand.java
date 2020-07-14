package main.java.edu.miu.cs.cs525.reversi.command;

import main.java.edu.miu.cs.cs525.reversi.action_adapters.AbstractBoardView;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.BoardViewTemplate;

public class NewGameCommand implements Command {
	AbstractBoardView ab = new BoardViewTemplate();
	
	@Override
	public void execute() {
//		if(s == "pauseButton_actionPerformed") {
//			
//		}
	}

}
