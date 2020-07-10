package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;

public class ChoosePlayerOkActionAdapter implements java.awt.event.ActionListener
{
    ChoosePlayerType adaptee ;

    public ChoosePlayerOkActionAdapter( ChoosePlayerType adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.cmdOK_actionPerformed( e ) ;
    }
}