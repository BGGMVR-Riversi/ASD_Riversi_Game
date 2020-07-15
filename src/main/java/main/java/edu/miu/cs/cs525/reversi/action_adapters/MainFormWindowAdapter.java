package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.WindowEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;

public class MainFormWindowAdapter extends java.awt.event.WindowAdapter {
	MainForm adaptee;

	private MainFormWindowAdapter(MainForm adaptee) {
		this.adaptee = adaptee;
	}
	public static MainFormWindowAdapter mainFormWindowAdapterFactory(MainForm adaptee){
		return new MainFormWindowAdapter(adaptee);
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}
