package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.WindowEvent;

import edu.miu.cs.cs525.reversi.monitor.MainForm;


public class MainFormWindowAdapter extends java.awt.event.WindowAdapter {
	MainForm adaptee;

	public MainFormWindowAdapter(MainForm adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}