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

/** Tests Round Polygon.
 * @author Joe Caraccio.
 */
public class RegularPolyTest extends JPanel
{
    //-------------- instance variables ------------------------------
    ARegularPoly r1;
    ARegularPoly r24;
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

        r1 = new ARegularPoly( 12, 10, 5, 100 );
        r1.setRotation( 360 );
        r1.setRadius( 40 );
        _ashapes.add( r1 );

        ARegularPoly r2 = new ARegularPoly(200, 10, 8, 50);
        r2.setColor( Color.green );
        r2.setSize( 2, 100 );
        _ashapes.add( r2 );

        ARegularPoly r3 = new ARegularPoly( 200, 200, 5, 30 );
        r3.setColor( Color.BLACK );
        _ashapes.add(r3);

        ARegularPoly r4 = new ARegularPoly(400, 200, 10, 40);
        r4.setColor( Color.pink );
        _ashapes.add(r4);

        ARegularPoly r5 = new ARegularPoly( 500, 510, 20 , 40 );
        r5.setColor( Color.RED );
        r5.setSize( 45 , 10 ); //size will obivously be 10
        r5.setRotation( 32 );
        _ashapes.add( r5 );

        //these two should be identicale except for color
        ARegularPoly r6 = new ARegularPoly( 100 , 400 , 5 , 15 );
        r6.setColor( Color.green );
        _ashapes.add( r6 );
        ARegularPoly r7 = new ARegularPoly( 10 , 300 , 5 , 15 );
        r7.setColor( Color.YELLOW );
        _ashapes.add( r7 );

        // should be pretty much the same thing  but bigger
        //same sides
        ARegularPoly r8 = new ARegularPoly( 60 , 300 , 5 ,  50 );
        r8.setColor( Color.YELLOW );
        _ashapes.add( r8 );

        //this one should be even bigger
        ARegularPoly r9 = new ARegularPoly( 160 , 300 , 5 , 100 );
        r9.setColor( Color.YELLOW );
        r9.setLineWidth( 3 );
        r9.moveBy( 10 , 10 );
        r9.setThickness( 10 );
        r9.setFrameColor(Color.black );

        _ashapes.add( r9 );

        ARegularPoly r10 = new ARegularPoly( 400 , 420 , 3 , 30 );
        r10.setColor( Color.magenta );
        _ashapes.add( r10 );

        //this one should be the opposite of this one
        ARegularPoly r11 = new ARegularPoly( 465 , 420 , 3, 30 );
        r11.setColor( Color.MAGENTA );
        r11.setRotation( 180 );
        _ashapes.add( r11 );

        //this one should be identical to the first one (this is the third one)
        ARegularPoly r12 = new ARegularPoly( 525 , 420 , 3 , 30 );
        r12.setColor( Color.MAGENTA );
        r12.setRotation( 360 );// should be no difference
        _ashapes.add( r12 );

        //random style set roatation methods
        ARegularPoly r13 = new ARegularPoly( 525, 10 , 5 , 40 );
        r13.setColor( Color.cyan );
        r13.setRotation( 32 );
        _ashapes.add( r13 );

        //a less messy value 90
        ARegularPoly r14 = new ARegularPoly( 525, 60 , 7 , 20 );
        r14.setRotation( 90 );
        _ashapes.add( r14 );

        //should pretty much look like a circle
        ARegularPoly r15 = new ARegularPoly( 525 , 130 , 18 , 25 );
        r15.setRotation( 110 );
        r15.setColor( Color.magenta );
        System.out.println("Lets Test its Cordinates");
        System.out.println("Should be located at 525, 130");
        System.out.println("Located at: " + r15.getXLocation() + " , " + r15.getYLocation() );
        _ashapes.add( r15 );

        ARegularPoly r16 = new ARegularPoly( 525 , 210 , 6 , 300000 );
        //if set size dosent work, this will make this polygon absolutely massive
        //we know set size works if the raduis is not huge
        r16.setRadius( 15 ); //fairly small now
        r16.setColor( Color.blue );
        _ashapes.add( r16 );


        //we know our set size method works if this isnt massive
        ARegularPoly r17 = new ARegularPoly( 525 , 300 , 6 , 3000040 );
        r17.setRadius( 30 );
        r17.setColor( Color.green );
        r17.setSize( 10 , 20 ); //should be similar  to r16 above
        //actually exactly the same size wise
        _ashapes.add( r17 );

        ARegularPoly r18 = new ARegularPoly( 200 , 200 , 4 , 20 );
        r18.setSize( 40 , 40 ); //raduis will now be 40
        r18.setLocation(  200 , 200 ); //should remain at 200 200
        r18.setColor( Color.GREEN );
        r18.setLineWidth( 0 );
        _ashapes.add( r18 );

        ARegularPoly r19 = new ARegularPoly( 10 , 200 , 7 , 40 );
        r19.setFillColor( Color.green );
        r19.setFrameColor( Color.BLACK );
        _ashapes.add( r19 );

        //make sure radius is not huge
        ARegularPoly r20 = new ARegularPoly( 10, 150 , 9 , 2002020202 );
        //will be hgue if actually drawn like that
        r20.setFrameColor( Color.MAGENTA );
        r20.setFillColor( Color.CYAN );
        r20.setSize( 10 , 20 );
        _ashapes.add( r20 );

        ARegularPoly r21 = new ARegularPoly( 0 , 0 , 3 , 20 );
        r21.setLocation( 300 , 300 );
        _ashapes.add( r21 );

        ARegularPoly r22 = new ARegularPoly( 0 , 0 , 5, 34 );
        r22.setRotation( 278 );
        r22.setFrameColor( Color.yellow );
        _ashapes.add( r22 );

        ARegularPoly r23 = new ARegularPoly( 300 , 0 , 6 , 30 );
        r23.setFillColor( Color.CYAN );
        r23.setFrameColor( Color.yellow );
        _ashapes.add( r23 );
        System.out.println("Location should be 300,0 + " + r23.getXLocation() + " , " + r23.getYLocation());

        //lets call a majority of the methods right here
         r24 = new ARegularPoly( 350 , 0 , 7 , 30 );
        r24.setFrameColor( Color.black );
        r24.setFillColor( Color.RED );
        r24.setRotation( 590 );
        r24.setRadius( 32 );
        r24.setThickness( 3 );
        //_ashapes.add( r24 );
        //all methods have been tested
    }
    //------------- paintComponent( Graphics ) ----------------------
    public void paintComponent( Graphics aBrush )
    {
        super.paintComponent( aBrush );
        //////////////////////////////////////////////////////////
        // draw the objects you created in the constructor
        //////////////////////////////////////////////////////////
        Graphics2D brush2D = ( Graphics2D ) aBrush;

        r24.display( brush2D );
        r24.draw( brush2D );
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