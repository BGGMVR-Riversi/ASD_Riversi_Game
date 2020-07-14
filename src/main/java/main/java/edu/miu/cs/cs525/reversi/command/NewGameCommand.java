package main.java.edu.miu.cs.cs525.reversi.command;

import main.java.edu.miu.cs.cs525.reversi.action_adapters.Abstract;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.BoardViewTemplate;

public class NewGameCommand implements Command {
	Abstract ab = new BoardViewTemplate();
	
	@Override
	public void execute() {
//		if(s == "pauseButton_actionPerformed") {
//			
//		}
	}

}
