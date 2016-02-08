package p1;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Skipper on 2/4/2016.
 */
public class First implements AShape {

    //AEllipse
    //ARectangle
    //ALine
    //2 instances of ARoundedRectangle w/ different sizes and arc parameters


    private static enum CoverageID {
        ctor_, ctor_ii, ctor_c, setColor_c, setLoc_ii, getX, getY, getColor
    }

    ;
    static boolean[] coverageFlag = // should be same length as enum size (8)
            {false, false, false, false, false, false, false, false};

    //---------------- instance variables ------------------------------
    ARectangle body;
    AEllipse head;
    AEllipse head2;
    AEllipse eye1;
    AEllipse eye2;
    ALine line1;
    ARoundRectangle tongue;
    ARoundRectangle blackPart;
    ARoundRectangle eyebrow;
    ARoundRectangle white;
    ARoundRectangle random;
    ARectangle ran2;

    int x, y;
    Color bodyColor = java.awt.Color.BLUE;

    // ---------------------- AWTBot() ----------------------------------

    /**
     * The constructor creates all the robot parts, and sets a default
     * location and body color (BLUE).
     */
    public First() {
        head = new AEllipse( Color.orange );
        head.setSize( 25, 25 );

        head2 = new AEllipse(Color.orange);
        head2.setSize( 25, 25 );

        eye1 = new AEllipse( Color.BLACK );
        eye1.setSize( 10, 10 );

        eye2 = new AEllipse( Color.BLACK );
        eye2.setSize( 10, 10 );

        line1 = new ALine( Color.BLACK );
        line1.setLineWidth( 5 );
        line1.setLine( 0, 70, 72, 70 );

        tongue = new ARoundRectangle(20, 50, 35, 80, 10, 10 );
        tongue.setColor( Color.RED );

        blackPart = new ARoundRectangle( 20 , 35 , 25, 60, 15, 15 );
        blackPart.setColor( Color.BLACK );


        eyebrow = new ARoundRectangle( 20 , 30, 25, 10, 5, 5 );
        eyebrow.setColor( Color.BLACK );

        white = new ARoundRectangle( 20 , 20 , 15 , 40 , 15 , 15 );
        white.setColor(Color.white );
        white.setArcSize( 10 ,5  );



        body = new ARectangle( Color.cyan );
        body.setSize( 75, 35 );

        random = new ARoundRectangle( 0 , 0 , 20 , 10 , 10 , 10 );
        random.setColor( Color.green );
        random.setArcSize( 8 , 7 );

        ran2 = new ARectangle( Color.magenta );
        ran2.setSize( 5 , 5 );

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
    public First(int xLoc, int yLoc) {
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
    public First(Color c) {
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
        body.setFillColor(c);

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
        body.setLocation(x + 13, y + 13);
        head.setLocation(x, y);
        head2.setLocation(x + 72, y);
        eye2.setLocation(x + 5, y);
        eye1.setLocation(x + 77, y);
        line1.setLocation(x + 13, y + 35);
        tongue.setLocation( x + 35 , y + 35 );
        blackPart.setLocation( x + 35 + 5, y + 40 );
        eyebrow.setLocation( x + 35 , y + 10 );
        white.setLocation( x + 45 , y + 45 );
        random.setLocation( x + 43 , y + 100 );
        ran2.setLocation( x +  46 , y + 102 );
        //sensor.setLocation( x + 35, y + 30 );
        //antenna.setPoints( x + 40, y + 30 , x + 40, y - 20 );

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
        body.display( brush2D );
        //wheel1.display(brush2D);
        //wheel2.display(brush2D);
        // caster.display(brush2D);
        tongue.display( brush2D );
        blackPart.display( brush2D );
        white.display( brush2D );
        head.display( brush2D );
        head2.display( brush2D );
        line1.display( brush2D );
        eye1.display( brush2D );
        eye2.display( brush2D );
        eyebrow.display( brush2D );
        random.display( brush2D );
        ran2.display( brush2D );



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
        final First bot1 = new First();
        bot1.setColor( Color.BLUE );
        bot1.setLocation( 10 , 10 );
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

        System.out.println("AWTBOT coverage info: " + First.coverageInfo());
    }
}
