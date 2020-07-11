package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;


public class BoardViewToolBarActionAdapter implements java.awt.event.ActionListener
{
    MainForm adaptee ;
    String tbItem ;

    public BoardViewToolBarActionAdapter( MainForm adaptee, String toolbarItem )
    {
        this.adaptee = adaptee ;
        tbItem = toolbarItem ;
    }

    public void actionPerformed( ActionEvent e )
    {
        if( tbItem.equals( "exitGameButton" ) ) {
            adaptee.exitGameButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "aboutButton" ) ) {
            adaptee.aboutButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "newGameButton" ) ) {
            adaptee.newGameButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "pauseButton" ) ) {
            adaptee.pauseButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "lastButton" ) ) {
            adaptee.lastButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "nextButton" ) ) {
            adaptee.nextButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "prevButton" ) ) {
            adaptee.prevButton_actionPerformed( e ) ;
        }
        else if( tbItem.equals( "firstButton" ) ) {
            adaptee.firstButton_actionPerformed( e ) ;
        }
    }
}