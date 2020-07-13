package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

public abstract class Abstract{

	public abstract void pauseGame();
	
	public abstract void newGame();
	
	public abstract void continueGame();
	
	abstract void pauseButton_actionPerformed(ActionEvent e);
	
	abstract void prevButton_actionPerformed(ActionEvent e);
	
	abstract void nextButton_actionPerformed(ActionEvent e);
	
	abstract void firstButton_actionPerformed(ActionEvent e);
	
	abstract void lastButton_actionPerformed(ActionEvent e);
}
