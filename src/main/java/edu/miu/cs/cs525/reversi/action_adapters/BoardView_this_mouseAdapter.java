package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.MouseEvent;

import edu.miu.cs.cs525.reversi.monitor.BoardView;

public class BoardView_this_mouseAdapter extends java.awt.event.MouseAdapter
{
    BoardView adaptee ;

    public BoardView_this_mouseAdapter( BoardView adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void mouseClicked( MouseEvent e )
    {
        adaptee.this_mouseClicked( e ) ;
    }
}