package main.java.edu.miu.cs.cs525.reversi.action_adapters;

public interface ActionEvent extends java.awt.event.ActionListener {
	
	ActionEvent initializeInstance(Object o);

	void actionPerformed(java.awt.event.ActionEvent e);
}
