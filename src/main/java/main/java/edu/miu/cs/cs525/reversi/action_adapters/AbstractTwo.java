package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

public abstract class AbstractTwo {
	
	abstract void menuGameNew_actionPerformed(ActionEvent e);
	
	abstract void menuGameExit_actionPerformed(ActionEvent e);
	
	abstract void menuHelpAbout_actionPerformed(ActionEvent e);
	
	abstract void processWindowEvent(ActionEvent e);
	
	abstract void menuBlackPlayerComputer_actionPerformed(ActionEvent e);
	
	abstract void menuBlackPlayerHuman_actionPerformed(ActionEvent e);
	
	abstract void menuWhitePlayerComputer_actionPerformed(ActionEvent e);
	
	abstract void menuWhitePlayerHuman_actionPerformed(ActionEvent e);
	
	abstract void menuSpeedItems_actionPerformed(ActionEvent e, int n);
	
	abstract void menuShowMoveList_actionPerformed(ActionEvent e);
}
