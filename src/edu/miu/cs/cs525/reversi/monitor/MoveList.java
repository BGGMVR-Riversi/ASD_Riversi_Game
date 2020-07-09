package edu.miu.cs.cs525.reversi.monitor ;

import java.awt.* ;
import javax.swing.* ;

import edu.miu.cs.cs525.reversi.common.*;

import java.awt.event.* ;

public class MoveList extends JFrame
{
    JScrollPane jScrollPane1 = new JScrollPane() ;
    JTextArea txtList = new JTextArea() ;
    JFrame parent ;

    public MoveList( String title, JFrame iParent )
    {
        super( title ) ;
        parent = iParent ;
        try {
            jbInit() ;
            pack() ;
        }
        catch( Exception ex ) {
            ex.printStackTrace() ;
        }
    }

    public MoveList()
    {
        this( "", null ) ;
    }

    private void jbInit() throws Exception
    {
        txtList.setBackground( new Color( 41, 149, 206 ) ) ;
        txtList.setFont( new java.awt.Font( "Default", 0, 14 ) ) ;
        txtList.setForeground( Color.white ) ;
        txtList.setEditable( false ) ;
        txtList.setMargin( new Insets( 15, 22, 0, 0 ) ) ;
        txtList.setText( "" ) ;
        jScrollPane1.setPreferredSize( new Dimension( 180, 600 ) ) ;
        this.addWindowListener( new moveList_this_windowAdapter( this ) ) ;
        this.setResizable( false ) ;
        this.getContentPane().add( jScrollPane1, BorderLayout.CENTER ) ;
        jScrollPane1.getViewport().add( txtList, null ) ;
    }

    public void updateMoveList( BoardInfo board )
    {
        String s = "" ;
        int i ;
        for( i = 0 ; i <board.moveCount ; i++ ) {
            if( i < 9 ) {
                s = s + " " + ( i + 1 ) + "." + board.moveHistory[i].getStandardForm() ;
            }
            else {
                s = s + ( i + 1 ) + "." + board.moveHistory[i].getStandardForm() ;
            }
            if( i % 2 == 1 ) {
                if( i <=board.MAX_MOVES) {
                    s = s + "\n" ;
                }
            }
            else {
                s = s + "\t" ;
            }
        }
        txtList.setText( s ) ;
    }

    void this_windowClosing( WindowEvent e )
    {
        ( ( MainForm ) parent ).menuShowMoveList.setState( false ) ;
    }

}

class moveList_this_windowAdapter extends java.awt.event.WindowAdapter
{
    MoveList adaptee ;

    moveList_this_windowAdapter( MoveList adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void windowClosing( WindowEvent e )
    {
        adaptee.this_windowClosing( e ) ;
    }
}
