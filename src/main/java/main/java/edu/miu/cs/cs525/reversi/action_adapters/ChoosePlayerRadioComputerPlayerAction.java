package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;

public class ChoosePlayerRadioComputerPlayerAction implements ReversiActionEvent
{
    ChoosePlayerType adaptee ;

    public void actionPerformed( ActionEvent e )
    {
        adaptee.radioComputerPlayer_actionPerformed( e ) ;
    }

	@Override
	public ReversiActionEvent initializeInstance(Object o) {
		adaptee = (ChoosePlayerType) o;
		return null;
	}
    
    
}