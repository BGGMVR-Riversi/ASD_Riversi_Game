package edu.miu.cs.cs525.reversi.monitor ;

import edu.miu.cs.cs525.reversi.common.*;
import edu.miu.cs.cs525.reversi.network.NetworkPlayer;

import java.awt.* ;
import javax.swing.* ;
import java.awt.event.* ;
import java.awt.datatransfer.* ;

public class MainForm extends JFrame
{
    JPanel contentPane ;
    JMenuBar menuBar = new JMenuBar() ;
    JMenu menuGame = new JMenu( "Game" ) ;
    JMenu menuOptions = new JMenu( "Options" ) ;
    JMenuItem menuGameNew = new JMenuItem( " New" ) ;
    JMenuItem menuGameExit = new JMenuItem( " Exit" ) ;
    JMenu menuPieceSet = new JMenu( " Piece Set" ) ; // Piece Set Menu
    JRadioButtonMenuItem menuPieceSetItems[] = new JRadioButtonMenuItem[7] ;
    ButtonGroup PSGroup = new ButtonGroup() ; // Piece Set Items Group
    JMenu menuPlayers = new JMenu( " Players" ) ;
    JMenu menuBlackPlayer = new JMenu( " Black " ) ;
    JMenu menuWhitePlayer = new JMenu( " White " ) ;
//    JRadioButtonMenuItem menuBlackPlayerHuman = new JRadioButtonMenuItem( "Human" ) ;
     JRadioButtonMenuItem menuBlackPlayerHuman = new JRadioButtonMenuItem( "Human" ) ;
//    JRadioButtonMenuItem menuBlackPlayerComputer = new JRadioButtonMenuItem( "Choose ..." ) ;
    JRadioButtonMenuItem menuBlackPlayerComputer= new JRadioButtonMenuItem("Computer");
    JRadioButtonMenuItem menuWhitePlayerHuman = new JRadioButtonMenuItem( "Human"  ) ;

//    JRadioButtonMenuItem menuWhitePlayerHuman = new JRadioButtonMenuItem( "Human" ) ;

//    JRadioButtonMenuItem menuWhitePlayerComputer = new JRadioButtonMenuItem( "Choose ..." ) ;
    JRadioButtonMenuItem menuWhitePlayerComputer = new JRadioButtonMenuItem( "Computer" ) ;

    ButtonGroup BGroup = new ButtonGroup() ; // Black Player Group
    ButtonGroup WGroup = new ButtonGroup() ; // White Player Group
    JMenu menuHelp = new JMenu( "Help" ) ;
    JMenuItem menuHelpAbout = new JMenuItem( " About" ) ;
    BorderLayout borderLayout1 = new BorderLayout() ;
    BoardView contents ;
    JMenu menuSpeed = new JMenu( " Speed" ) ;
    JRadioButtonMenuItem menuSpeedItems[] = new JRadioButtonMenuItem[6] ;
    ButtonGroup SGroup = new ButtonGroup() ; // Animation Speed Group
    JCheckBoxMenuItem menuShowGuides = new JCheckBoxMenuItem( " Show Guides" ) ;
    ImageIcon newGameIcon = new ImageIcon() ;
    ImageIcon exitGameIcon = new ImageIcon() ;
    ImageIcon aboutIcon = new ImageIcon() ;
    ImageIcon playersIcon = new ImageIcon() ;
    ImageIcon speedIcon = new ImageIcon() ;
    ImageIcon pieceSetIcon = new ImageIcon() ;
    ImageIcon copyIcon = new ImageIcon() ;
    ImageIcon pasteIcon = new ImageIcon() ;
    MoveList ml ;
    JCheckBoxMenuItem menuShowMoveList = new JCheckBoxMenuItem( " Show Move List" ) ;
    JMenuItem menuCopyGame = new JMenuItem( " Copy Game to ClipBoard" ) ;
    JMenuItem menuPasteGame = new JMenuItem( " Paste Game from ClipBoard" ) ;
    JButton firstButton = new JButton() ;
    JButton prevButton = new JButton() ;
    JButton pauseButton = new JButton() ;
    JButton nextButton = new JButton() ;
    JButton lastButton = new JButton() ;
    JButton newGameButton = new JButton() ;
    JButton aboutButton = new JButton() ;
    JButton exitGameButton = new JButton() ;
    JToolBar toolBar = new JToolBar() ;
    ImageIcon firstImg = new ImageIcon() ;
    ImageIcon prevImg = new ImageIcon() ;
    ImageIcon pauseImg = new ImageIcon() ;
    ImageIcon playImg = new ImageIcon() ;
    ImageIcon nextImg = new ImageIcon() ;
    ImageIcon lastImg = new ImageIcon() ;
    ImageIcon newGameImg = new ImageIcon() ;
    ImageIcon aboutImg = new ImageIcon() ;
    ImageIcon exitGameImg = new ImageIcon() ;

    //Construct the frame
    public MainForm()
    {
        enableEvents( AWTEvent.WINDOW_EVENT_MASK ) ;
        try {
            jbInit() ;
          ChoosePlayerType c= new ChoosePlayerType();
        }
        catch( Exception e ) {
            e.printStackTrace() ;
        }
    }

    //Component initialization
    private void jbInit() throws Exception
    {
        newGameIcon = new ImageIcon( MainForm.class.getResource( "../images/new.png" ) ) ;
        exitGameIcon = new ImageIcon( MainForm.class.getResource( "../images/quit.png" ) ) ;
        aboutIcon = new ImageIcon( MainForm.class.getResource( "../images/about.png" ) ) ;
        playersIcon = new ImageIcon( MainForm.class.getResource( "../images/players.png" ) ) ;
        speedIcon = new ImageIcon( MainForm.class.getResource( "../images/eyes.png" ) ) ;
        pieceSetIcon = new ImageIcon( MainForm.class.getResource( "../images/nibbles.png" ) ) ;
        copyIcon = new ImageIcon( MainForm.class.getResource( "../images/copy.png" ) ) ;
        pasteIcon = new ImageIcon( MainForm.class.getResource( "../images/paste.png" ) ) ;
        contentPane = ( JPanel )this.getContentPane() ;
        contentPane.setLayout( borderLayout1 ) ;
        this.setResizable( false ) ;
        this.setSize( new Dimension( 487 + 60, 562 + 95 ) ) ;
        this.setTitle( "Java Othello / Reversi" ) ;
        this.addWindowListener( new mainForm_this_windowAdapter( this ) ) ;
        menuGameNew.setIcon( newGameIcon ) ;
        menuGameNew.addActionListener( new mainForm_Menu_ActionAdapter( this, "GameNew" ) ) ;
        menuGameExit.setIcon( exitGameIcon ) ;
        menuGameExit.addActionListener( new mainForm_Menu_ActionAdapter( this, "GameExit" ) ) ;
        menuPieceSetItems[0] = new JRadioButtonMenuItem( "Blend           " ) ;
        menuPieceSetItems[1] = new JRadioButtonMenuItem( "Classic         " ) ;
        menuPieceSetItems[2] = new JRadioButtonMenuItem( "Flip Blend      " ) ;
        menuPieceSetItems[3] = new JRadioButtonMenuItem( "Shrink          " ) ;
        menuPieceSetItems[4] = new JRadioButtonMenuItem( "Standard I      ", true ) ;
        menuPieceSetItems[5] = new JRadioButtonMenuItem( "Standard II     " ) ;
        menuPieceSetItems[6] = new JRadioButtonMenuItem( "Sun & Moon      " ) ;
        menuBlackPlayerComputer.addActionListener( new mainForm_Menu_ActionAdapter( this,
            "BlackPlayerComputer" ) ) ;
        menuBlackPlayerHuman.addActionListener( new mainForm_Menu_ActionAdapter( this, "BlackPlayerHuman" ) ) ;
        menuWhitePlayerComputer.addActionListener( new mainForm_Menu_ActionAdapter( this,
            "WhitePlayerComputer" ) ) ;
        menuWhitePlayerHuman.addActionListener( new mainForm_Menu_ActionAdapter( this, "WhitePlayerHuman" ) ) ;
        menuHelpAbout.setIcon( aboutIcon ) ;
        menuShowGuides.addActionListener( new mainForm_Menu_ActionAdapter( this, "ShowGuides" ) ) ;
        menuShowGuides.setState( true ) ;
        menuShowMoveList.addActionListener( new mainForm_Menu_ActionAdapter( this, "ShowMoveList" ) ) ;
        menuShowMoveList.setState( true ) ;
        menuCopyGame.addActionListener( new mainForm_Menu_ActionAdapter( this, "CopyGame" ) ) ;
        menuCopyGame.setIcon( copyIcon ) ;
        menuPasteGame.addActionListener( new mainForm_Menu_ActionAdapter( this, "PasteGame" ) ) ;
        menuPasteGame.setIcon( pasteIcon ) ;
        WGroup.add( menuWhitePlayerComputer ) ;
        WGroup.add( menuWhitePlayerHuman ) ;
        menuWhitePlayerComputer.setSelected(true);
        menuBlackPlayerComputer.setSelected(true);
//        menuBlackPlayerHuman.setSelected( false ) ;
        BGroup.add( menuBlackPlayerComputer ) ;
        BGroup.add( menuBlackPlayerHuman ) ;
//        menuWhitePlayerHuman.setSelected( false ) ;
        menuSpeedItems[0] = new JRadioButtonMenuItem( "1.0 x" ) ;
        menuSpeedItems[1] = new JRadioButtonMenuItem( "2.0 x" ) ;
        menuSpeedItems[2] = new JRadioButtonMenuItem( "3.0 x" ) ;
        menuSpeedItems[3] = new JRadioButtonMenuItem( "5.0 x", true ) ;
        menuSpeedItems[4] = new JRadioButtonMenuItem( "10.0 x" ) ;
        menuSpeedItems[5] = new JRadioButtonMenuItem( "No Animation" ) ;
        menuSpeedItems[0].addActionListener( new mainForm_Menu_ActionAdapter( this, "SpeedItems", 1 ) ) ;
        menuSpeedItems[1].addActionListener( new mainForm_Menu_ActionAdapter( this, "SpeedItems", 2 ) ) ;
        menuSpeedItems[2].addActionListener( new mainForm_Menu_ActionAdapter( this, "SpeedItems", 3 ) ) ;
        menuSpeedItems[3].addActionListener( new mainForm_Menu_ActionAdapter( this, "SpeedItems", 5 ) ) ;
        menuSpeedItems[4].addActionListener( new mainForm_Menu_ActionAdapter( this, "SpeedItems", 10 ) ) ;
        menuSpeedItems[5].addActionListener( new mainForm_Menu_ActionAdapter( this, "SpeedItems", 30 ) ) ;
        menuHelpAbout.addActionListener( new mainForm_Menu_ActionAdapter( this, "HelpAbout" ) ) ;
        menuGame.add( menuGameNew ) ;
        menuGame.add( menuCopyGame ) ;
        menuGame.add( menuPasteGame ) ;
        menuGame.add( menuGameExit ) ;
        for( int i = 0 ; i < menuPieceSetItems.length ; i++ ) {
            menuPieceSetItems[i].addActionListener( new mainForm_Menu_ActionAdapter( this, "PieceSetItems", i ) ) ;
            menuPieceSet.add( menuPieceSetItems[i] ) ;
            PSGroup.add( menuPieceSetItems[i] ) ;
        }
        menuBlackPlayer.add( menuBlackPlayerComputer ) ;
        menuBlackPlayer.add( menuBlackPlayerHuman ) ;
        menuWhitePlayer.add( menuWhitePlayerComputer ) ;
        menuWhitePlayer.add( menuWhitePlayerHuman ) ;
        menuPlayers.add( menuBlackPlayer ) ;
        menuPlayers.add( menuWhitePlayer ) ;
        menuHelp.add( menuHelpAbout ) ;
        for( int i = 0 ; i < menuSpeedItems.length ; i++ ) {
            SGroup.add( menuSpeedItems[i] ) ;
            menuSpeed.add( menuSpeedItems[i] ) ;
        }
        menuPlayers.setIcon( playersIcon ) ;
        menuPieceSet.setIcon( pieceSetIcon ) ;
        menuSpeed.setIcon( speedIcon ) ;
        menuBar.add( menuGame ) ;
        menuOptions.add( menuPieceSet ) ;
        menuOptions.add( menuPlayers ) ;
        menuOptions.add( menuSpeed ) ;
        menuOptions.add( menuShowMoveList ) ;
        menuOptions.add( menuShowGuides ) ;
        menuBar.add( menuOptions ) ;
        menuBar.add( menuHelp ) ;
        this.setJMenuBar( menuBar ) ;
        firstImg = new ImageIcon( MainForm.class.getResource( "../images/first.png" ) ) ;
        prevImg = new ImageIcon( MainForm.class.getResource( "../images/prev.png" ) ) ;
        pauseImg = new ImageIcon( MainForm.class.getResource( "../images/pause.png" ) ) ;
        playImg = new ImageIcon( MainForm.class.getResource( "../images/play.png" ) ) ;
        nextImg = new ImageIcon( MainForm.class.getResource( "../images/next.png" ) ) ;
        lastImg = new ImageIcon( MainForm.class.getResource( "../images/last.png" ) ) ;
        newGameImg = new ImageIcon( MainForm.class.getResource( "../images/new.png" ) ) ;
        aboutImg = new ImageIcon( MainForm.class.getResource( "../images/about.png" ) ) ;
        exitGameImg = new ImageIcon( MainForm.class.getResource( "../images/quit.png" ) ) ;
        firstButton.setEnabled( false ) ;
        firstButton.setToolTipText( "First Move" ) ;
        firstButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "firstButton" ) ) ;
        prevButton.setEnabled( false ) ;
        prevButton.setToolTipText( "Previous Move" ) ;
        prevButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "prevButton" ) ) ;
        nextButton.setEnabled( false ) ;
        nextButton.setToolTipText( "Next Move" ) ;
        nextButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "nextButton" ) ) ;
        lastButton.setEnabled( false ) ;
        lastButton.setToolTipText( "Last Move" ) ;
        lastButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "lastButton" ) ) ;
        pauseButton.setEnabled( false ) ;
        pauseButton.setToolTipText( "Pause Game" ) ;
        pauseButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "pauseButton" ) ) ;
        newGameButton.setToolTipText( "New Game" ) ;
        newGameButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "newGameButton" ) ) ;
        aboutButton.setToolTipText( "About" ) ;
        aboutButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "aboutButton" ) ) ;
        exitGameButton.setToolTipText( "Exit" ) ;
        exitGameButton.addActionListener( new boardView_ToolBar_actionAdapter( this, "exitGameButton" ) ) ;
        firstButton.setIcon( firstImg ) ;
        prevButton.setIcon( prevImg ) ;
        pauseButton.setIcon( pauseImg ) ;
        nextButton.setIcon( nextImg ) ;
        lastButton.setIcon( lastImg ) ;
        newGameButton.setIcon( newGameImg ) ;
        aboutButton.setIcon( aboutImg ) ;
        exitGameButton.setIcon( exitGameImg ) ;
        toolBar.add( newGameButton ) ;
        toolBar.addSeparator() ;
        toolBar.add( firstButton ) ;
        toolBar.add( prevButton ) ;
        toolBar.add( pauseButton ) ;
        toolBar.add( nextButton ) ;
        toolBar.add( lastButton ) ;
        toolBar.addSeparator() ;
        toolBar.add( aboutButton ) ;
        toolBar.add( exitGameButton ) ;
        contentPane.add( toolBar, BorderLayout.NORTH ) ;
        ml = new MoveList( "Move List", this ) ;
        contents = new BoardView( ml, this, 1 ) ;
        contentPane.add( contents, BorderLayout.CENTER ) ;
    }

    public void continueGame()
    {
        pauseButton.setIcon( pauseImg ) ;
        firstButton.setEnabled( false ) ;
        prevButton.setEnabled( false ) ;
        nextButton.setEnabled( false ) ;
        lastButton.setEnabled( false ) ;
        pauseButton.setToolTipText( "Pause Game" ) ;
        contents.gamePaused = false ;
        contents.updateTurn() ;
    }

    public void pauseGame()
    {
        pauseButton.setIcon( playImg ) ;
        firstButton.setEnabled( true ) ;
        prevButton.setEnabled( true ) ;
        nextButton.setEnabled( true ) ;
        lastButton.setEnabled( true ) ;
        pauseButton.setToolTipText( "Continue Game" ) ;
        contents.gamePaused = true ;
    }

    public void newGame()
    {
        contents.board.initBoard() ;
        repaint() ;
        pauseButton.setEnabled( true ) ;
        continueGame() ;
    }

    void pauseButton_actionPerformed( ActionEvent e )
    {
        if( pauseButton.getToolTipText().equals( "Pause Game" ) ) {
            pauseGame() ;
        }
        else {
            continueGame() ;
        }
    }

    void newGameButton_actionPerformed( ActionEvent e )
    {
        menuGameNew_actionPerformed( e ) ;
    }

    void exitGameButton_actionPerformed( ActionEvent e )
    {
        menuGameExit_actionPerformed( e ) ;
    }

    void aboutButton_actionPerformed( ActionEvent e )
    {
        menuHelpAbout_actionPerformed( e ) ;
    }

    void prevButton_actionPerformed( ActionEvent e )
    {
        if( contents.timer.isRunning() ) {
            return ;
        }
        contents.board.takeBackOneMove() ;
        contents.updateTurn() ;
        repaint() ;
    }

    void nextButton_actionPerformed( ActionEvent e )
    {
        if( contents.timer.isRunning() ) {
            return ;
        }
        contents.board.redoOneMove() ;
        contents.updateTurn() ;
        repaint() ;
    }

    void firstButton_actionPerformed( ActionEvent e )
    {
        if( contents.timer.isRunning() ) {
            return ;
        }
        contents.board.takeBackAllMoves() ;
        contents.updateTurn() ;
        repaint() ;
    }

    void lastButton_actionPerformed( ActionEvent e )
    {
        if( contents.timer.isRunning() ) {
            return ;
        }
        contents.board.redoAllMoves() ;
        contents.updateTurn() ;
        repaint() ;
    }

    public void menuGameNew_actionPerformed( ActionEvent e )
    {
        newGame() ;
    }

    //File | Exit action performed
    public void menuGameExit_actionPerformed( ActionEvent e )
    {
        System.exit( 0 ) ;
    }

    public void menuPieceSetItems_actionPerformed( ActionEvent e, int n )
    {
        String ps = "" ;
        switch( n ) {
            case 0:
                ps = "blend.png" ;
                break ;
            case 1:
                ps = "classic.png" ;
                break ;
            case 2:
                ps = "flipblend.png" ;
                break ;
            case 3:
                ps = "shrink.png" ;
                break ;
            case 4:
                ps = "standard-1.png" ;
                break ;
            case 5:
                ps = "standard-2.png" ;
                break ;
            case 6:
                ps = "sunmoon.png" ;
                break ;
        }
        contents.pieces = new ImageIcon( edu.miu.cs.cs525.reversi.monitor.MainForm.class.getResource( "../images/" + ps ) ) ;
        repaint() ;
    }

    //Help | About action performed
    public void menuHelpAbout_actionPerformed( ActionEvent e )
    {
        About dlg = new About( this ) ;
        Dimension dlgSize = dlg.getPreferredSize() ;
        Dimension frmSize = getSize() ;
        Point loc = getLocation() ;
        dlg.setLocation( ( frmSize.width - dlgSize.width ) / 2 + loc.x,
                         ( frmSize.height - dlgSize.height ) / 2 + loc.y ) ;
        dlg.setModal( true ) ;
        dlg.pack() ;
        dlg.show() ;
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent( WindowEvent e )
    {
        super.processWindowEvent( e ) ;
        if( e.getID() == WindowEvent.WINDOW_CLOSING ) {
            menuGameExit_actionPerformed( null ) ;
        }
    }

    void menuBlackPlayerComputer_actionPerformed( ActionEvent e )
    {
        ChoosePlayerType dlg = new ChoosePlayerType( this, "Choose Black Player", true ) ;
        Dimension dlgSize = dlg.getPreferredSize() ;
        Dimension frmSize = getSize() ;
        Point loc = getLocation() ;
        dlg.setLocation( ( frmSize.width - dlgSize.width ) / 2 + loc.x,
                         ( frmSize.height - dlgSize.height ) / 2 + loc.y ) ;
        dlg.show() ;
        if( dlg.playerType == dlg.COMPUTER_PLAYER ) {
            contents.playerBPointer = new ComputerPlayer() ;
            menuBlackPlayerComputer.setText( "Computer" ) ;
            
        }
        else if( dlg.playerType == dlg.NET_PLAYER ) {
            contents.playerBPointer = new NetworkPlayer( dlg.hostAddress, dlg.portNumber , dlg.portNumber2) ;
            try {
                String id = ( ( NetworkPlayer ) contents.playerBPointer ).identify() ;
                menuBlackPlayerComputer.setText( id + " @ ( " + dlg.hostAddress + ":" + dlg.portNumber + " )" ) ;
            }
            catch( Exception exc ) {
            }
        }
        else if( contents.playerBPointer == null ) {
//          menuBlackPlayerHuman.setSelected( true ) ;
            menuBlackPlayerComputer.setSelected(true);
            menuBlackPlayerComputer.setEnabled(true);
            contents.playerBPointer = new ComputerPlayer() ;
            dlg.radioComputerPlayer_actionPerformed(e);
            dlg.radioComputerPlayer.setSelected(true);

           dlg.radioComputerPlayer.addActionListener( new choosePlayerDLG_radioComputerPlayer_actionAdapter(dlg) ) ;
            dlg.radioComputerPlayer.doClick();

            //dlg.radioCocmdOK_actionPerformed(e);
//            dlg. radioComputerPlayer.addActionListener( new choosePlayerDLG_cmdOK_actionAdapter( dlg.COMPUTER_PLAYER ) ) ;
            


        }
        contents.updateTurn() ;
    }

    void menuBlackPlayerHuman_actionPerformed( ActionEvent e )
    {
        //contents.playerBPointer = null ;
        //menuBlackPlayerComputer.setText( "Choose ..." ) ;
        menuBlackPlayerHuman.setText("Human" );
    }

    void menuWhitePlayerComputer_actionPerformed( ActionEvent e )
    {
        ChoosePlayerType dlg = new ChoosePlayerType( this, "Choose White Player", true ) ;
        Dimension dlgSize = dlg.getPreferredSize() ;
        Dimension frmSize = getSize() ;
        Point loc = getLocation() ;
        dlg.setLocation( ( frmSize.width - dlgSize.width ) / 2 + loc.x,
                         ( frmSize.height - dlgSize.height ) / 2 + loc.y ) ;
        dlg.show() ;
        if( dlg.playerType == dlg.COMPUTER_PLAYER ) {
            contents.playerWPointer = new ComputerPlayer() ;
            menuWhitePlayerComputer.setText( "Computer" ) ;
           
        }
        else if( dlg.playerType == dlg.NET_PLAYER ) {
            contents.playerWPointer = new NetworkPlayer( dlg.hostAddress, dlg.portNumber, dlg.portNumber2 ) ;
            try {
                String id = ( ( NetworkPlayer ) contents.playerWPointer ).identify() ;
                menuWhitePlayerComputer.setText( id + " @ ( " + dlg.hostAddress + ":" + dlg.portNumber + " )" ) ;
            }
            catch( Exception exc ) {
            }
        }
        else if( contents.playerWPointer == null ) {
            //menuWhitePlayerHuman.setSelected( true ) ;
        	menuWhitePlayerComputer.setSelected(true);
            menuWhitePlayerComputer.setEnabled(true);
            contents.playerWPointer = new ComputerPlayer() ;
            dlg.radioComputerPlayer_actionPerformed(e);

            dlg.radioComputerPlayer.setSelected(true);
           
           dlg. radioComputerPlayer.addActionListener( new choosePlayerDLG_radioComputerPlayer_actionAdapter( dlg ) ) ;
           dlg.radioComputerPlayer.doClick();


        }
        contents.updateTurn() ;
    }

    void menuWhitePlayerHuman_actionPerformed( ActionEvent e )
    {
               //contents.playerWPointer = null ;
        //menuWhitePlayerComputer.setText( "Choose ..." ) ;
        menuWhitePlayerHuman.setText("Human" );
    }

    void menuSpeedItems_actionPerformed( ActionEvent e, int n )
    {
        contents.animationSpeed = n ;
    }

    void menuShowGuides_actionPerformed( ActionEvent e )
    {
        contents.showGuides = menuShowGuides.getState() ;
        repaint() ;
    }

    void menuCopyGame_actionPerformed( ActionEvent e )
    {
        StringSelection s = new StringSelection( contents.board.getStandardFormGame() ) ;
        getToolkit().getSystemClipboard().setContents( s, null ) ;
    }

    void menuPasteGame_actionPerformed( ActionEvent e )
    {
        // ***************************************
        String d = "" ;
        Transferable s = getToolkit().getSystemClipboard().getContents( null ) ;
        try {
            d = ( String ) s.getTransferData( DataFlavor.stringFlavor ) ;
        }
        catch( Exception exc ) {
            System.err.println( this.getClass().getName() + " : " + exc.getMessage() ) ;
        }
        pauseButton.setEnabled( true ) ;
        pauseGame() ;
        contents.timer.stop() ;
        contents.board.setStandardFormGame( d ) ;
        contents.repaint() ;
        contents.updateTurn() ;
        // ***************************************
    }

    void menuShowMoveList_actionPerformed( ActionEvent e )
    {
        ml.setVisible( menuShowMoveList.getState() ) ;
        repaint() ;
    }

    void this_windowOpened( WindowEvent e )
    {
        Dimension frmSize = getSize() ;
        Point l = this.getLocationOnScreen() ;
        ml.setLocation( l.x + frmSize.width, l.y ) ;
        ml.show() ;
        this.toFront() ;
    }
}

class mainForm_Menu_ActionAdapter implements ActionListener
{
    MainForm adaptee ;
    String mItem ;
    int n ;

    mainForm_Menu_ActionAdapter( MainForm adaptee, String menuItem )
    {
        this.adaptee = adaptee ;
        mItem = menuItem ;
    }

    mainForm_Menu_ActionAdapter( MainForm adaptee, String menuItem, int i )
    {
        this.adaptee = adaptee ;
        mItem = menuItem ;
        n = i ;
    }

    public void actionPerformed( ActionEvent e )
    {
        if( mItem.equals( "GameNew" ) ) {
            adaptee.menuGameNew_actionPerformed( e ) ;
        }
        else if( mItem.equals( "GameExit" ) ) {
            adaptee.menuGameExit_actionPerformed( e ) ;
        }
        else if( mItem.equals( "PieceSetItems" ) ) {
            adaptee.menuPieceSetItems_actionPerformed( e, n ) ;
        }
        else if( mItem.equals( "BlackPlayerHuman" ) ) {
            adaptee.menuBlackPlayerHuman_actionPerformed( e ) ;
        }
        else if( mItem.equals( "BlackPlayerComputer" ) ) {
            adaptee.menuBlackPlayerComputer_actionPerformed( e ) ;
        }
        else if( mItem.equals( "WhitePlayerHuman" ) ) {
            adaptee.menuWhitePlayerHuman_actionPerformed( e ) ;
        }
        else if( mItem.equals( "WhitePlayerComputer" ) ) {
            adaptee.menuWhitePlayerComputer_actionPerformed( e ) ;
        }
        else if( mItem.equals( "HelpAbout" ) ) {
            adaptee.menuHelpAbout_actionPerformed( e ) ;
        }
        else if( mItem.equals( "SpeedItems" ) ) {
            adaptee.menuSpeedItems_actionPerformed( e, n ) ;
        }
        else if( mItem.equals( "ShowGuides" ) ) {
            adaptee.menuShowGuides_actionPerformed( e ) ;
        }
        else if( mItem.equals( "ShowMoveList" ) ) {
            adaptee.menuShowMoveList_actionPerformed( e ) ;
        }
        else if( mItem.equals( "CopyGame" ) ) {
            adaptee.menuCopyGame_actionPerformed( e ) ;
        }
        else if( mItem.equals( "PasteGame" ) ) {
            adaptee.menuPasteGame_actionPerformed( e ) ;
        }
    }
}

class mainForm_this_windowAdapter extends java.awt.event.WindowAdapter
{
    MainForm adaptee ;

    mainForm_this_windowAdapter( MainForm adaptee )
    {
        this.adaptee = adaptee ;
    }

    public void windowOpened( WindowEvent e )
    {
        adaptee.this_windowOpened( e ) ;
    }
}

class boardView_ToolBar_actionAdapter implements java.awt.event.ActionListener
{
    MainForm adaptee ;
    String tbItem ;

    boardView_ToolBar_actionAdapter( MainForm adaptee, String toolbarItem )
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
