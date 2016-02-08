/**
 * RoundTest.java -- a skeleton for a comprehensive test of
 *    ARoundRectangle. This should be expanded sufficiently that
 *    it is clear from looking at the output that you have tested
 *    the ARoundRectangle thoroughly and understand its parameters.
 * 
 */
package p1;
import src.*;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class RoundTest extends JPanel
{
    //-------------- instance variables ------------------------------
    Vector<AShape> _ashapes;
    //------------------ constructor ---------------------------------
    public RoundTest( ) {
        _ashapes = new Vector<AShape>();
        ARoundRectangle rr0 = new ARoundRectangle(90, 90, 140, 140, 180, 180);
        _ashapes.add(rr0);

        //should basically be a smaller circle
        //roughly half the size
        ARoundRectangle rr2 = new ARoundRectangle(245, 100, 70, 70, 180, 180);
        rr2.setColor(Color.red);
        //lets test location, location should return 245,100
        System.out.println("Location for RR2: " + rr2.getXLocation() + " , " + rr2.getYLocation());
        _ashapes.add(rr2);

        //this should just be a square
        ARoundRectangle rr3 = new ARoundRectangle(245, 5, 10, 10, 0, 0);
        rr3.setColor(Color.green);
        System.out.println("Color for RR3: " + rr3.getColor() );
        _ashapes.add(rr3);

        //basic one
        ARoundRectangle rr4 = new ARoundRectangle( 300 , 5 , 100 , 100 , 15 ,15 );
        _ashapes.add( rr4 );

        //basic round rectangle
        //with adjusted parameters
        //should appear more rounded than one in the color above
        ARoundRectangle rr5 = new ARoundRectangle( 300 , 180 , 100 , 100 , 15 ,15 );
        //if we don't adjust it would probably just be 15,15 for arc width/height
        //we adjust it here
        rr5.setArcSize( 80 , 80 );
        _ashapes.add( rr5 );

        //lets try a round rectangle with more drastic values for arc
        ARoundRectangle rr6 = new ARoundRectangle( 10 , 180 , 100 , 100 , 15 , 15 );
        rr6.setColor( Color.YELLOW ); //should be green
        rr6.setArcSize( 200 , 200 );
        _ashapes.add( rr6 );

        //try adjusting location
        ARoundRectangle rr7 = new ARoundRectangle( 300 , 300 , 30 , 30 , 10, 10 );
        //if location was not adjusted it would be in bottom right corner
        rr7.setColor( Color.cyan );
        rr7.setLocation( 10 , 10 ); //should be closer to the top left
        _ashapes.add( rr7 );

        //lets try adjusting line thinkness
        ARoundRectangle rr8 = new ARoundRectangle( 70 ,  10 , 20 , 20 , 10 , 10 );
        rr8.setColor( Color.red );
        rr8.setSize( 5 , 5 ); //should be barely visible
        _ashapes.add( rr8 );

        ARoundRectangle rr9 = new ARoundRectangle( 100 , 10 , 10 , 10 , 10 , 10 );
        rr9.setColor( Color.cyan );
        rr9.setSize( 25 , 25 ); //should be decently visible

        _ashapes.add( rr9 );

        //should be pretty long
        ARoundRectangle rr10 = new ARoundRectangle( 10 , 295, 10 , 90, 20, 20 );
        rr10.setColor( Color.red );
        _ashapes.add( rr10 );

        //should not even be visible
        //unles the frame size is larger

        ARoundRectangle rr11 = new ARoundRectangle( 600, 100, 50 , 90, 20, 26 );
        _ashapes.add( rr11 );

        ARoundRectangle rr12 = new ARoundRectangle( 200 , 280 , 50 , 50 , 20 , 20);
        //trying to set arc width to different parameters
        //one should be really rounded and one not at all
        rr12.setArcSize( 5 , 190 );
        _ashapes.add( rr12 );

        //testing lenght parameters
        //this should go off the page because tis such a long rectangle
        ARoundRectangle rr13 = new ARoundRectangle( 250 , 260 , 25 , 120 , 20, 20 );
        rr13.setColor( rr2.getColor() ); //lets try getting color from the other rectangle
        _ashapes.add( rr13 );

        ARoundRectangle rr14 = new ARoundRectangle( 200 , 50 , 10 , 10 , 2 ,2 );
        rr14.setColor( Color.green );
        _ashapes.add( rr14 );

        ARoundRectangle rr15 = new ARoundRectangle( 240 , 50 , 10 , 10 , 5 , 5 );
        rr15.setArcSize( 2 , 2 );
        rr15.setColor( rr14.getColor()  );
        _ashapes.add( rr15 );

        ARoundRectangle rr16 = new ARoundRectangle( 10 , 50 , 50, 90 , 15 ,15 );
        rr16.setColor( Color.black );
        _ashapes.add( rr16 );

        ARoundRectangle rr17 = new ARoundRectangle( 60 , 50 , 10, 30 , 15 , 15  );
        rr17.setColor( Color.red );
        rr17.setSize( 10 , 20 );
        rr17.setArcSize( 15 , 10 );
        _ashapes.add( rr17 );





        //Thats it, every method has been tested multiple times


    }
    //------------- paintComponent( Graphics ) ----------------------
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
    
    //------------------------ main -----------------------------------
    public static void  main( String[] args )
    {
        JFrame f = new JFrame( "ARoundRectangle test" );
        f.setSize( 500, 400 );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new RoundTest();
        f.add( panel );
        f.setVisible( true ); 
    }            
}