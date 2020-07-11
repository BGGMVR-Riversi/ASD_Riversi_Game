package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;

public class ChoosePlayerCancelActionAdapter implements java.awt.event.ActionListener
{
    ChoosePlayerType adaptee ;

    ChoosePlayerCancelActionAdapter( ChoosePlayerType adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.cmdCancel_actionPerformed( e ) ;
    }
}