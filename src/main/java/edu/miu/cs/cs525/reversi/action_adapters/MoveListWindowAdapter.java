package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.WindowEvent;

import edu.miu.cs.cs525.reversi.monitor.MoveList;

public 
class MoveListWindowAdapter extends java.awt.event.WindowAdapter
{
    MoveList adaptee ;

    public MoveListWindowAdapter( MoveList adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void windowClosing( WindowEvent e )
    {
        adaptee.this_windowClosing( e ) ;
    }
}