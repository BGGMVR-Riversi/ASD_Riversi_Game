package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;

public class MainFormMenuActionAdapter implements ActionListener {
	MainForm adaptee;
	String menuItem;
	int n;

	private MainFormMenuActionAdapter(MainForm adaptee, String menuItem) {
		this.adaptee = adaptee;
		this.menuItem = menuItem;
	}

	private MainFormMenuActionAdapter(MainForm adaptee, String menuItem, int i) {
		this.adaptee = adaptee;
		this.menuItem = menuItem;
		n = i;
	}

	public static MainFormMenuActionAdapter mainFormMenuActionAdapterFactory1(MainForm adaptee, String menuItem){
		return new MainFormMenuActionAdapter(adaptee,menuItem);

	}

	public static MainFormMenuActionAdapter mainFormMenuActionAdapterFactory2(MainForm adaptee, String menuItem, int i){
		return new MainFormMenuActionAdapter(adaptee,menuItem, i);

	}

	public void actionPerformed(ActionEvent e) {
		if (menuItem.equals("GameNew")) {
			adaptee.menuGameNew_actionPerformed(e);
		} else if (menuItem.equals("GameExit")) {
			adaptee.menuGameExit_actionPerformed(e);
		} else if (menuItem.equals("BlackPlayerHuman")) {
			adaptee.menuBlackPlayerHuman_actionPerformed(e);
		} else if (menuItem.equals("BlackPlayerComputer")) {
			adaptee.menuBlackPlayerComputer_actionPerformed(e);
		} else if (menuItem.equals("WhitePlayerHuman")) {
			adaptee.menuWhitePlayerHuman_actionPerformed(e);
		} else if (menuItem.equals("WhitePlayerComputer")) {
			adaptee.menuWhitePlayerComputer_actionPerformed(e);
		} else if (menuItem.equals("HelpAbout")) {
			adaptee.menuHelpAbout_actionPerformed(e);
		} else if (menuItem.equals("SpeedItems")) {
			adaptee.menuSpeedItems_actionPerformed(e, n);
		}else if (menuItem.equals("ShowMoveList")) {
			adaptee.menuShowMoveList_actionPerformed(e);
		}
	}
}