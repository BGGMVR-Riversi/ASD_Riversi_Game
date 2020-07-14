package main.java.edu.miu.cs.cs525.reversi.action_adapters;

public interface ReversiActionEvent extends java.awt.event.ActionListener {
	
	ReversiActionEvent initializeInstance(Object o);

	void actionPerformed(java.awt.event.ActionEvent e);
}
