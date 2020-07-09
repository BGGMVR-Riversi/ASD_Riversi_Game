package edu.miu.cs.cs525.reversi.monitor ;

import javax.swing.UIManager ;
import java.awt.* ;

public class Launch
{
    boolean packFrame = false ;

    //Construct the application
    public Launch()
    {
        MainForm frame = new MainForm() ;
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if( packFrame ) {
            frame.pack() ;
        }
        else {
            frame.validate() ;
        }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize() ;
        Dimension frameSize = frame.getSize() ;
        if( frameSize.height > screenSize.height ) {
            frameSize.height = screenSize.height ;
        }
        if( frameSize.width > screenSize.width ) {
            frameSize.width = screenSize.width ;
        }
        frame.setLocation( ( screenSize.width - frameSize.width - 185 ),
                           ( screenSize.height - frameSize.height ) / 2 ) ;
        frame.setVisible( true ) ;
    }

    //Main method
    public static void main( String[] args )
    {
        try {
            UIManager.setLookAndFeel( "javax.swing.plaf.metal.MetalLookAndFeel" ) ;
        }
        catch( Exception e ) {
            e.printStackTrace() ;
        }
        new Launch() ;
    }
}
