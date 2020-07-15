package main.java.edu.miu.cs.cs525.reversi.monitor ;

import java.awt.* ;
import javax.swing.* ;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.MoveListWindowAdapter;
import main.java.edu.miu.cs.cs525.reversi.common.*;
import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;

import java.awt.event.* ;

public class MoveList extends JFrame
{

	private static final long serialVersionUID = 1L;
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
        txtList.setBackground( ReversiSingleton.getDarkGray() ) ;
        txtList.setFont( new java.awt.Font( "Default", 0, 14 ) ) ;
        txtList.setForeground( ReversiSingleton.getAqua()) ;
        txtList.setEditable( false ) ;
        txtList.setMargin( new Insets( 15, 22, 0, 0 ) ) ;
        txtList.setText( "" ) ;
        jScrollPane1.setPreferredSize( new Dimension( 180, 600 ) ) ;
        this.addWindowListener(MoveListWindowAdapter.moveListWindowAdapter( this ) ) ;
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
                if( i <=BoardEnum.MAX_MOVES.value()) {
                    s = s + "\n" ;
                }
            }
            else {
                s = s + "\t" ;
            }
        }
        txtList.setText( s ) ;
    }

    public void this_windowClosing( WindowEvent e )
    {
    	 ReversiSingleton.getMenuShowMoveList().setState( false ) ;
    }

}


