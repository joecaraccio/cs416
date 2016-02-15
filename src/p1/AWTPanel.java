/** 
 * AWTPanel.java: Swing panel for AWT draw assignment.
 * 
 * This is a framework for the main display window for an 
 * awt application. 
 *      
 * @author rdb
 * 01/10/08 
 * 01/22/11 rdb modified for start code for P1, Spring 2011
 */
package p1;
import src.*;
import java.util.ArrayList;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class AWTPanel extends JPanel
{ 
    //-------------------- instance variables ----------------------
    //
    // declare here (among other things) the variables that reference
    //   AWT display objects (ellipses, rectangles, lines, etc.)
    //private House     robot1, robot2;
    Vector<AShape> _ashapes = null;   // objects that need to be displayed

    //------------- Constructor ---------------------------------
    /**
     * Create awt objects to be displayed.
     */
    public AWTPanel( String [] args ) 
    {
        // Create objects you want to display using awt graphical objects.
        // Use wheels-like "wrapper" classes, ARectangle, AEllipse, ALine
        // The ARectangle and AEllipse classes are minor variations of
        // SmartRectangle and SmartEllipse from the Sanders and van Dam.
        //
        // References to the objects you create need to be saved in an
        //   ArrayList or Vector of AShape objects.
        //

        _ashapes = new Vector<AShape>();

        AEllipse ellip = new AEllipse();
        ellip.setLocation( 10,10 );
        ellip.setColor( Color.GREEN );
        _ashapes.add( ellip );

        AEllipse el1 = new AEllipse();
        el1.setColor( Color.green );
        el1.setLocation( 200 , 10 );
        _ashapes.add( el1 );

        AEllipse el2 = new AEllipse();
        el2.setColor( Color.red );
        el2.setLocation( 200 , 60 );
        _ashapes.add( el2 );

        AEllipse el3 = new AEllipse();
        el3.setColor( Color.cyan );
        el3.setLocation( 300, 320 );
        _ashapes.add( el3 );

        ARectangle rec1 = new ARectangle();
        rec1.setLocation( 250, 100 );
        rec1.setColor( Color.RED );
        _ashapes.add(rec1);

        ARectangle rec2 = new ARectangle( Color.red );
        rec2.setLocation( 175 , 400 );
        _ashapes.add( rec2 );

        ARoundRectangle r0 = new ARoundRectangle( 400 , 100 );
        r0.setColor( Color.BLUE );
        r0.setSize( 20, 20 );
        r0.setArcSize( 10, 10 );
        _ashapes.add( r0 );

        ARoundRectangle r8 = new ARoundRectangle( 400 , 300 );
        r8.setSize( 40, 40);
        r8.setArcSize( 5, 5);
        r8.setColor( Color.green );
        _ashapes.add( r8 );

        ARoundRectangle r1 = new ARoundRectangle( 300 , 300 , 50 , 60 , 15 ,16 );
        r1.setColor( Color.red );
        r1.setSize( 60 , 50 );
        r1.setArcSize( 16 , 15 );
        _ashapes.add( r1 );


        ARoundRectangle round1 = new ARoundRectangle( 20, 20, 50, 50 , 5, 5);
        round1.setColor(Color.yellow);
        _ashapes.add(round1);

        ARoundRectangle round2 = new ARoundRectangle( 5, 5, 70, 70 , 15, 15 );
        round2.setColor( Color.pink );
        _ashapes.add( round2 );

        //should be kinda traianglur looking
        ARegularPoly poly1 = new ARegularPoly( 400 , 100 , 3 , 10 );
        _ashapes.add( poly1 );

        ARegularPoly poly2 = new ARegularPoly( 400 , 130 , 6 , 10 );
        _ashapes.add( poly2 );

        ARegularPoly poly23 = new ARegularPoly( 400 , 130 , 6 , 10 );
        poly23.setSize( 44, 44 );
        _ashapes.add( poly23 );

        ARegularPoly poly3 = new ARegularPoly( 200 , 200 , 9 , 20 );
        poly3.setColor( Color.green );
        poly3.setSize( 20 , 30 );
        _ashapes.add( poly3 );

        ALine aL = new ALine();
        aL.setColor( Color.cyan );
        aL.setLocation( 240 , 200 );
        aL.setLineWidth( 20 );
        aL.setLine( 200, 200, 300, 300 );
        _ashapes.add( aL );

        ALine AL2 = new ALine();
        AL2.setColor( Color.red );
        AL2.setLineWidth( 20 );
        AL2.setLine( 300 , 300 , 200 , 200 );
        AL2.setLocation( 200, 260 );
        _ashapes.add( AL2 );

        ALine a1 = new ALine();
        a1.setColor( Color.cyan );
        a1.setLineWidth( 10 );
        a1.setLine( 400 , 400 , 310 , 310 );
        _ashapes.add( a1 );


        First f1 = new First();
        f1.setLocation( 10 , 10 );
        _ashapes.add( f1 );

        First f2 = new First();
        f2.setLocation( 100 , 100 );
        _ashapes.add( f2 );

        Second s1 = new Second();
        s1.setLocation( 59, 400 );
        _ashapes.add( s1 );

        Second s2 = new Second();
        s2.setLocation( 400 , 150 );
        _ashapes.add( s2 );

        Second s3 = new Second();
        s3.setLocation( 400 , 350 );
        s3.setColor( Color.RED );
        _ashapes.add( s3 );

        Second s4 = new Second();
        s4.setLocation( 400 , 5 );
        _ashapes.add( s4 );

        First f3 = new First();
        f3.setLocation( 10 , 200 );
        _ashapes.add( f3 );
    }  
    //------------- paintComponent ---------------------------------------
    /**
     * This method is called from the Java environment when it determines
     * that the JPanel display should be updated; you need to 
     * make appropriate calls here to re-draw the graphical images on
     * the display. Each object created in the constructor above should 
     * have its "fill" and/or "draw" methods invoked with a Graphics2D 
     * object. The Graphics object passed to paintComponent will be a 
     * a Graphics2D object, so it can be coerced to that type and
     * passed along to the "display" method of the objects you created.
     * 
     * Note that the "display" method is not an awt method; it is a
     * convenience method defined by the "wrapper" classes. 
     * The display method usually passes the graphical objects to both 
     * the Graphics2D.fill and Graphics2D.draw methods, except in the 
     * case of ALine graphical objects which cannot be "filled".
     */
    public void paintComponent( Graphics aBrush )
    {
        super.paintComponent( aBrush );
        Graphics2D brush2D = (Graphics2D) aBrush; // coerce to Graphics2D
        
        //////////////////////////////////////////////////////////////
        // invoke display( newBrush ) for all A-objects in scene
        //////////////////////////////////////////////////////////////

        for( int i = 0; i < _ashapes.size(); i++ ) {
            _ashapes.get( i ).display( brush2D );
        }

    }
    //-------------------- main ------------------------------------------
    /**
     * Invoke AWTApp.main
     */
    public static void main( String[] args )
    {
        AWTApp.main( args );
    }
}
