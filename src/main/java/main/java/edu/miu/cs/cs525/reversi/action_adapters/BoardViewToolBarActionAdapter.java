package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;


public class BoardViewToolBarActionAdapter implements java.awt.event.ActionListener
{
    MainForm adaptee ;
    Abstract ab = new BoardViewTemplate();
    String tbItem ;

    public BoardViewToolBarActionAdapter( MainForm adaptee, String toolbarItem )
    {
        this.adaptee = adaptee ;
        tbItem = toolbarItem ;
    }

    public void actionPerformed( ActionEvent e )
    {
        if( tbItem.equals( "pauseButton" ) ) {
            ab.pauseButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "lastButton" ) ) {
            ab.lastButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "nextButton" ) ) {
            ab.nextButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "prevButton" ) ) {
            ab.prevButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "firstButton" ) ) {
            ab.firstButton_actionPerformed( e ) ;
        }
    }
}