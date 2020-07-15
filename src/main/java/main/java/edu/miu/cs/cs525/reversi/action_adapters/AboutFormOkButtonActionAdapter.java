package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.About;

public class AboutFormOkButtonActionAdapter implements java.awt.event.ActionListener
{
    About adaptee ;

    private AboutFormOkButtonActionAdapter( About adaptee )
    {
        this.adaptee = adaptee ;
    }

    public static AboutFormOkButtonActionAdapter aboutFormOkButtonActionAdapterFactory(About adaptee){
        return new AboutFormOkButtonActionAdapter(adaptee);
    }

    public void actionPerformed( ActionEvent e )
    {
        adaptee.btnOK_actionPerformed( e ) ;
    }
}