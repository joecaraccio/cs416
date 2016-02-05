/**
 * RegularPolyTest.java -- this is a skeleton for a program to 
 *             thoroughly test the ARegularPoly class.
 * 
 *  It needs to be significantly expanded. 
 *       1. At the very least, every public method of ARegularPoly needs 
 *          to be invoked at least once -- even methods you didn't edit! 
 *          You must verify that you didn't break them. 
 *       2. And, every option of every method you did write needs to be
 *          tested thoroughly.
 *          
 * @author rdb
 * Last edited: 01/01/14
 */
package p1;
import src.*;
import src.AShape;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RegularPolyTest extends JPanel
{
    //-------------- instance variables ------------------------------
    ARegularPoly r1;
    Vector<ARegularPoly>    _ashapes = null;   // objects that need to be displayed

    //------------------ constructor ---------------------------------
    public RegularPolyTest( )
    {
        _ashapes = new Vector<ARegularPoly>();
        ////////////////////////////////////////////////////////
        // Create enough ARegularPoly objects to show that you 
        //   have thoroughly tested this code. At the very least,
        //   your tests should cause the execution of every public method
        //   in the class (not just the ones you had to write).
        ////////////////////////////////////////////////////////
        r1 = new ARegularPoly(10, 10, 5, 100);
        r1.setRotation(360);
        r1.setRadius(10);
        _ashapes.add(r1);

        ARegularPoly r2 = new ARegularPoly(200, 10, 8, 50);
        r2.setColor( Color.green );
        r2.setSize( 300, 100 );
        _ashapes.add( r2 );

        ARegularPoly r3 = new ARegularPoly( 200, 200, 5, 30 );
        r3.setColor( Color.BLACK );
        _ashapes.add(r3);

        ARegularPoly r4 = new ARegularPoly(400, 200, 10, 40);
        r4.setColor( Color.pink );
        _ashapes.add(r4);

        ARegularPoly r5 = new ARegularPoly( 500, 510, 20 , 40 );
        r5.setColor( Color.RED );
        r5.setSize( 10 , 10 ); //size will obivously be 10
        r5.setRotation( 32 );
        _ashapes.add( r5 );


    }
    //------------- paintComponent( Graphics ) ----------------------
    public void paintComponent( Graphics aBrush )
    {
        super.paintComponent( aBrush );
        //////////////////////////////////////////////////////////
        // draw the objects you created in the constructor
        //////////////////////////////////////////////////////////
        Graphics2D brush2D = ( Graphics2D ) aBrush;

        Iterator<ARegularPoly> iter = _ashapes.iterator();
        while ( iter.hasNext() )
            iter.next().display( brush2D );
    }
    
    //------------------------ main -----------------------------------
    public static void  main( String[] args )
    {
        JFrame f = new JFrame( "ARegularPoly test" );
        f.setSize( 700, 600 );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new RegularPolyTest();
        f.add( panel );
        f.setVisible( true ); 
    }            
}