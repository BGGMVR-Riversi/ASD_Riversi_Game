package main.java.edu.miu.cs.cs525.reversi.monitor ;

import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;

import main.java.edu.miu.cs.cs525.reversi.action_adapters.AboutFormOkButtonActionAdapter;

public class About extends JDialog
{

	private static final long serialVersionUID = 1L;
	JPanel mainPanel = new JPanel() ;
    ImageIcon iagnoImg = new ImageIcon() ;
    GridBagLayout gridBagLayout1 = new GridBagLayout() ;
    JButton btnOK = new JButton() ;
    JLabel lblTitle = new JLabel() ;
    JTextArea jTextArea1 = new JTextArea() ;

    public About( Frame parent )
    {
        super( parent ) ;
        enableEvents( AWTEvent.WINDOW_EVENT_MASK ) ;
        try {
            jbInit() ;
        }
        catch( Exception e ) {
            e.printStackTrace() ;
        }
    }

    About()
    {
        this( null ) ;
    }

    //Component initialization
    private void jbInit() throws Exception
    {
        iagnoImg = new ImageIcon( MainForm.class.getResource( "../images/iagno.png" ) ) ;
        this.setTitle( "About" ) ;

        setResizable( false ) ;
        mainPanel.setLayout( gridBagLayout1 ) ;
        btnOK.setText( "OK" ) ;
        btnOK.addActionListener( AboutFormOkButtonActionAdapter.aboutFormOkButtonActionAdapterFactory( this ) ) ;
        lblTitle.setFont( new java.awt.Font( "Default", 1, 30 ) ) ;
        lblTitle.setIcon( iagnoImg ) ;
        lblTitle.setIconTextGap( 15 ) ;
        lblTitle.setText( "Othello/Riversi" ) ;
        jTextArea1.setBackground( new Color( 255, 194, 159 ) ) ;
        jTextArea1.setEnabled( false ) ;
        jTextArea1.setFont( new java.awt.Font( "Default", 1, 16 ) ) ;
        jTextArea1.setDisabledTextColor( Color.darkGray ) ;
        jTextArea1.setEditable( false ) ;
        jTextArea1.setMargin( new Insets( 10, 20, 10, 20 ) ) ;
        jTextArea1.setText( "Java Othello/Riversi \n" + "Version 1.0\n" + "By : Team 4\n" +
                            "Advanced Software Design course project" ) ;
        this.getContentPane().add( mainPanel, BorderLayout.CENTER ) ;
        mainPanel.add( lblTitle, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 25, 15, 10, 15 ), 0, 0 ) ) ;
        mainPanel.add( btnOK, new GridBagConstraints( 0, 2, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 20, 0, 30, 0 ), 62, 9 ) ) ;
        mainPanel.add( jTextArea1, new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 0, 15, 0, 15 ), 0, 0 ) ) ;
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent( WindowEvent e )
    {
        if( e.getID() == WindowEvent.WINDOW_CLOSING ) {
            cancel() ;
        }
        super.processWindowEvent( e ) ;
    }

    //Close the dialog
    void cancel()
    {
        dispose() ;
    }

    public void btnOK_actionPerformed( ActionEvent e )
    {
        cancel() ;

    }

}
