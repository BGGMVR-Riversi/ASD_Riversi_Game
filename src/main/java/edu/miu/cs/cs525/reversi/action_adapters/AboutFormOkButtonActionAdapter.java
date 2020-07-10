package edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import edu.miu.cs.cs525.reversi.monitor.About;

public class AboutFormOkButtonActionAdapter implements java.awt.event.ActionListener
{
    About adaptee ;

    public AboutFormOkButtonActionAdapter( About adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.btnOK_actionPerformed( e ) ;
    }
}