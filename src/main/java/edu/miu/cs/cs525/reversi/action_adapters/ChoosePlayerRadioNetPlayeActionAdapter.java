package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;

public class ChoosePlayerRadioNetPlayeActionAdapter implements java.awt.event.ActionListener
{
    ChoosePlayerType adaptee ;

    public ChoosePlayerRadioNetPlayeActionAdapter( ChoosePlayerType adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.radioNetPlayer_actionPerformed( e ) ;
    }
}