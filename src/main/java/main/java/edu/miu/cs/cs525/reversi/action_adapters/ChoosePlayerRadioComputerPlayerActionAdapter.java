package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;

public class ChoosePlayerRadioComputerPlayerActionAdapter implements java.awt.event.ActionListener
{
    ChoosePlayerType adaptee ;

    public ChoosePlayerRadioComputerPlayerActionAdapter( ChoosePlayerType adaptee )
    {
    	
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.radioComputerPlayer_actionPerformed( e ) ;
    }
    
    
}