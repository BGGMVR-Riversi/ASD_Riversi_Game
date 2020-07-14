package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.BoardView;

public class BoardView_Timer_Action implements ReversiActionEvent
{
    BoardView adaptee ;

	@Override
	public void actionPerformed(ActionEvent e) {
        adaptee.timer_actionPerformed( e );
	}

	@Override
	public ReversiActionEvent initializeInstance(Object o) {
		adaptee = (BoardView) o;
		return this;
	}
}