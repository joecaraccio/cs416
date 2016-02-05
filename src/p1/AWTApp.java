/**
 * AWTApp.java -- CS416 Programming assignment 1; a simple awt application
 * 
 * This is a Swing application based on the pattern described in Ch 7 of
 * Sanders and van Dam, Object-Oriented Programming in Java.
 * 
 * All the application-specific code is in the AWTPanel class
 * 
 * @author rdb
 * January 2010 based on Swing application from Sanders and van Dam
 * 01/01/14 rdb: indent at 4
 */
package p1;
import src.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class AWTApp extends JFrame
{
    //------------------ constructor ---------------------------------
    /**
     * Construct a JFrame for the application and put a JPanel in it.
     */
    Vector<AShape> _ashapes = null;   // objects that need to be displayed


    public AWTApp( String title, String[] args )
    {
        super( title );            // specify window title
        this.setSize( 700, 500 );  // define window size
        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


        _ashapes = new Vector<AShape>();

        AEllipse ellip = new AEllipse();
        ellip.setLocation(10,10);
        ellip.setColor(Color.GREEN );
        _ashapes.add(ellip);

        ARectangle rec1 = new ARectangle();
        rec1.setLocation(100, 100);
        rec1.setColor(Color.RED);
        _ashapes.add(rec1);

        ARoundRectangle round1 = new ARoundRectangle( 20, 20, 50, 50 , 5, 5);
        round1.setColor(Color.yellow);
        _ashapes.add(round1);

        ARoundRectangle round2 = new ARoundRectangle( 5, 5, 70, 70 , 15, 15);
        round2.setColor(Color.pink);
        _ashapes.add(round2);

        ALine aL = new ALine();
        aL.setColor(Color.cyan);
        aL.setLocation( 200 , 200 );
        aL.setLineWidth( 20 );
        aL.setLine( 200, 200, 300, 300 );
        _ashapes.add(aL);




        // All application specific code is in the AWTPanel constructor.
        // AWTPanel extends JPanel. 
        
        AWTPanel panel = new AWTPanel( args ); // create graphics in JPanel
        this.add( panel );                     // add it to the frame
        
        this.setVisible( true );
    }
    
    //------------------------ main -----------------------------------
    public static void main( String[] args )
    {
        AWTApp app = new AWTApp( "Drawing in AWT/Swing", args );
    }
}