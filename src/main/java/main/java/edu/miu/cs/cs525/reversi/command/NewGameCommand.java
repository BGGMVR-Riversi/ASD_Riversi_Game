package main.java.edu.miu.cs.cs525.reversi.command;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;

public class NewGameCommand implements Command {
	private MainForm service;
	
	public NewGameCommand(MainForm service) {
		this.service = service;
	}

	@Override
	public void execute() {
//		service
	}

}
