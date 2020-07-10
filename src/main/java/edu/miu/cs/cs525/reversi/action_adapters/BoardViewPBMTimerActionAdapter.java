package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import edu.miu.cs.cs525.reversi.monitor.BoardView;

public 
class BoardViewPBMTimerActionAdapter implements java.awt.event.ActionListener
{
    BoardView adaptee ;

    public BoardViewPBMTimerActionAdapter( BoardView adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.PBMTimer_actionPerformed( e ) ;
    }
}