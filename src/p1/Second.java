package p1;

import javax.swing.*;
import java.awt.*;

/**
 * @author Joe Caraccio
 * Created by Skipper on 2/4/2016.
 */
public class Second implements AShape {





    private static enum CoverageID {
        ctor_, ctor_ii, ctor_c, setColor_c, setLoc_ii, getX, getY, getColor
    }

    ;
    static boolean[] coverageFlag = // should be same length as enum size (8)
            {false, false, false, false, false, false, false, false};

    //---------------- instance variables ------------------------------
    ARegularPoly eye1;
    ARegularPoly eye2;
    ARoundRectangle bg;
    ARegularPoly nose;
    ARoundRectangle t1;
    ARoundRectangle t2;
    ARegularPoly b1;
    ARegularPoly b2;
    ARegularPoly c1;
    ARegularPoly c2;

    int x, y;
    Color bodyColor = java.awt.Color.BLUE;

    // ---------------------- AWTBot() ----------------------------------

    /**
     * The constructor creates all the robot parts, and sets a default
     * location and body color (BLUE).
     */
    public Second() {

        bg = new ARoundRectangle( 0 , 0 , 100 , 65 , 15 , 15 );
        bg.setColor( Color.GREEN );

        eye1 = new ARegularPoly( 0 , 0 , 8 , 10 );
        eye1.setColor( Color.RED );

        eye2 = new ARegularPoly( 0 , 0 , 8 , 10 );
        eye2.setColor( Color.RED );

        nose = new ARegularPoly( 0 , 0 , 10 , 15 );
        nose.setColor( Color.magenta );

        t1 = new ARoundRectangle( 0 , 0 , 15 , 50 , 10 , 10 );
        t1.setColor( Color.BLACK );

        t2 = new ARoundRectangle( 0 , 0 , 15, 50 , 10 , 10 );
        t2.setColor( Color.BLACK );

        b1 = new ARegularPoly( 0 , 0 , 4 , 15 );
        b1.setColor( Color.green );

        b2 = new ARegularPoly( 0 , 0 , 4 , 15 );
        b2.setColor( Color.green );

        c1 = new ARegularPoly( 0 , 0 , 6 , 10 );
        c1.setColor( Color.YELLOW );
        c1.setRotation( 45 );

        c2 = new ARegularPoly( 0 , 0, 6 , 10 );
        c2.setColor( Color.YELLOW );
        c2.setRotation( 45 );



        setLocation(0, 0);

        covered(CoverageID.ctor_);
    }

    // ------------------- AWTBot( int, int ) ---------------------------

    /**
     * Constructor includes an explicit location and default color.
     *
     * @param xLoc int x-location
     * @param yLoc int y-location
     */
    public Second(int xLoc, int yLoc) {
        this();
        setLocation(xLoc, yLoc);

        covered(CoverageID.ctor_ii);
    }

    // ---------------------- AWTBot( Color ) --------------------------

    /**
     * Constructor includes explicit color and default location ( 0,0 ).
     *
     * @param c Color body color
     */
    public Second(Color c) {
        this();
        setColor(c);

        covered(CoverageID.ctor_c);
    }

    //--------------------- setColor( Color ) -------------------------

    /**
     * set body color to parameter and default colors for all other parts.
     *
     * @param c Color body color
     */
    public void setColor(Color c) {
        bodyColor = c;

        covered(CoverageID.setColor_c);
    }

    //------------------- setLocation( int, int ) ---------------------

    /**
     * set the location of the robot to the paramters.
     *
     * @param xLoc int  x-location
     * @param yLoc int  y-locaation
     */
    public void setLocation(int xLoc, int yLoc) {
        this.x = xLoc;
        this.y = yLoc;

        //wheel1.setLocation( x, y + 60 );
        //wheel2.setLocation( x + 60, y + 60 );
        //caster.setLocation( x + 32, y );
        bg.setLocation( x + 20 , y + 10 );
        eye1.setLocation( x + 10 , y + 10 );
        eye2.setLocation( x + 110 , y + 10 );
        t1.setLocation( x + 25 , y + 60 );
        t2.setLocation( x + 90 , y + 60 );
        b1.setLocation( x + 25, y + 85 );
        b2.setLocation( x + 90, y + 85 );
        nose.setLocation( x + 50 , y + 25 );
        c1.setLocation( x + 25 , y + 55 );
        c2.setLocation( x + 100 , y + 55 );


        covered(CoverageID.setLoc_ii);
    }

    //--------------------- int getXLocation() ------------------------

    /**
     * return the x location of the robot.
     *
     * @return int x location
     */
    public int getXLocation() {
        covered(CoverageID.getX);
        return x;
    }

    //--------------------- int getYLocation() ------------------------

    /**
     * return the y location of the robot.
     *
     * @return int y location
     */
    public int getYLocation() {
        covered(CoverageID.getY);
        return y;
    }

    //--------------------- int getColor() ----------------------------

    /**
     * return the body color of the robot.
     *
     * @return Color body color
     */
    public Color getColor() {
        covered(CoverageID.getColor);
        return bodyColor;
    }

    //----------------------- display( Graphics2D ) -------------------

    /**
     * display - calls draw and fill awt methods (this is an rdb method).
     *
     * @param brush2D java.awt.Graphics2D
     */
    public void display(java.awt.Graphics2D brush2D) {
        ///////////////////////////////////////////////////////////////
        // For every A-object created in the AWTBot constructor (7)
        //   invoke that object's display method with "brush2D" as the
        //   the parameter:
        //   body, wheel1, wheel2, caster, head, sensor, antenna.
        ///////////////////////////////////////////////////////////////
        //wheel1.display(brush2D);
        //wheel2.display(brush2D);
        // caster.display(brush2D);
        bg.display( brush2D );
        eye1.display( brush2D );
        eye2.display( brush2D );
        t1.display( brush2D );
        t2.display( brush2D );
        b1.display( brush2D );
        b2.display( brush2D );
        nose.display( brush2D );
        c1.display( brush2D );
        c2.display( brush2D );




    }
    //---------------------- covered( String ) ---------------------------

    /**
     * Confirm that a method has been called, but finding its code in the
     * coverage string and capitalizing it.
     *
     * @param mId CoverageID
     */
    private static void covered(CoverageID mId) {
        coverageFlag[mId.ordinal()] = true;
    }
    //---------------------- coverageInfo() ---------------------------

    /**
     * Return a string describing the current state of coverage information.
     *
     * @return String
     */
    private static String coverageInfo() {
        int methodCount = coverageFlag.length;
        int count = 0;
        for (int i = 0; i < coverageFlag.length; i++) {
            if (coverageFlag[i])
                count++;
        }
        System.out.println();

        return count + " of " + methodCount + " methods called.";
    }

    //------------------------  main ----------------------------------

    /**
     * Unit test for AWTBot.
     *
     * @param args String[] Command line araguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("AWTBot test");
        frame.setSize(400, 300);  // define window size

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // AWTBot variables need to be "final" in order to be accessed
        //   inside the implicitly declared anonymous class that extends
        //   JPanel below.

        // Minimal test: use each constructor

        final Second bot1 = new Second();
        bot1.setColor( Color.BLUE );
        bot1.setLocation( 10 , 10 );
        bot1.setLocation( 15 , 15 );
        System.out.println( "Color = " + bot1.getColor() );
        System.out.println( "X Loc = " + bot1.getXLocation() + " Y Loc = " + bot1.getYLocation());


        // Create anonymous JPanel child w/ override for paintComponent
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                bot1.display((Graphics2D) g);
                ///////////////////////////////////////////////////
                // invoke display method of the 2nd and 3rd AWTBot
                //       objects.
                ///////////////////////////////////////////////////


            }
        };
        frame.add(panel);        // add it to the frame
        frame.setVisible(true);  // make it visible.

        System.out.println("Second coverage info: " + Second.coverageInfo());
    }
}
