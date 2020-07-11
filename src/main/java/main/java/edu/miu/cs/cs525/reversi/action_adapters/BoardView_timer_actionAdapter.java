package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.BoardView;

public class BoardView_timer_actionAdapter implements java.awt.event.ActionListener
{
    BoardView adaptee ;

    public BoardView_timer_actionAdapter( BoardView adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.timer_actionPerformed( e ) ;
    }
}