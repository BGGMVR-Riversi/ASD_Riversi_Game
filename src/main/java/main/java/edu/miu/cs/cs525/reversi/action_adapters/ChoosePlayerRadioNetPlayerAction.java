package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;

public class ChoosePlayerRadioNetPlayerAction implements main.java.edu.miu.cs.cs525.reversi.action_adapters.ActionEvent
{
    ChoosePlayerType adaptee ;
    
    public void actionPerformed( ActionEvent e )
    {
        adaptee.radioNetPlayer_actionPerformed( e ) ;
    }

	@Override
	public main.java.edu.miu.cs.cs525.reversi.action_adapters.ActionEvent initializeInstance(Object o) {
		adaptee = (ChoosePlayerType) o;
		return this;
	}
}