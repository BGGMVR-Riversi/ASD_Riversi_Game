package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;

public class MainFormMenuActionAdapter implements ActionListener {
	MainForm adaptee;
	AbstractTwo abTwo = new MenuTemplate();
	String menuItem;
	int n;

	public MainFormMenuActionAdapter(MainForm adaptee, String menuItem) {
		this.adaptee = adaptee;
		this.menuItem = menuItem;
	}

	public MainFormMenuActionAdapter(MainForm adaptee, String menuItem, int i) {
		this.adaptee = adaptee;
		this.menuItem = menuItem;
		n = i;
	}

	public void actionPerformed(ActionEvent e) {
		if (menuItem.equals("GameNew")) {
			abTwo.menuGameNew_actionPerformed(e);
		} else if (menuItem.equals("GameExit")) {
			abTwo.menuGameExit_actionPerformed(e);
		} else if (menuItem.equals("BlackPlayerHuman")) {
			abTwo.menuBlackPlayerHuman_actionPerformed(e);
		} else if (menuItem.equals("BlackPlayerComputer")) {
			abTwo.menuBlackPlayerComputer_actionPerformed(e);
		} else if (menuItem.equals("WhitePlayerHuman")) {
			abTwo.menuWhitePlayerHuman_actionPerformed(e);
		} else if (menuItem.equals("WhitePlayerComputer")) {
			abTwo.menuWhitePlayerComputer_actionPerformed(e);
		} else if (menuItem.equals("HelpAbout")) {
			abTwo.menuHelpAbout_actionPerformed(e);
		} else if (menuItem.equals("SpeedItems")) {
			abTwo.menuSpeedItems_actionPerformed(e, n);
		}else if (menuItem.equals("ShowMoveList")) {
			abTwo.menuShowMoveList_actionPerformed(e);
		}
	}
}