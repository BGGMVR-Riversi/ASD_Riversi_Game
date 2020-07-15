package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.WindowEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.MoveList;

public 
class MoveListWindowAdapter extends java.awt.event.WindowAdapter
{
    MoveList adaptee ;

    private MoveListWindowAdapter( MoveList adaptee )
    {
        this.adaptee = adaptee ;
    }

    public static MoveListWindowAdapter MoveListWindowAdapter(MoveList adaptee ){
        return new MoveListWindowAdapter(adaptee);
    }

    public void windowClosing( WindowEvent e )
    {
        adaptee.this_windowClosing( e ) ;
    }
}