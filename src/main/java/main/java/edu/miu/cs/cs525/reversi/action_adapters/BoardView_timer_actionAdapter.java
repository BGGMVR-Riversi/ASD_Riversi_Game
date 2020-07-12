package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.BoardView;

public class BoardView_timer_actionAdapter implements main.java.edu.miu.cs.cs525.reversi.action_adapters.ActionEvent
{
    BoardView adaptee ;

	@Override
	public void actionPerformed(ActionEvent e) {
        adaptee.timer_actionPerformed( e );
	}

	@Override
	public main.java.edu.miu.cs.cs525.reversi.action_adapters.ActionEvent initializeInstance(Object o) {
		adaptee = (BoardView) o;
		return this;
	}
}