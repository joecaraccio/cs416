
import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * EMTVehicle.java.
 * Creates a Vehichle like object.
 * @author Joe Caraccio
 * Last editted: 01/04/14 Format
 */
public class EMTVehicle extends JComponent implements Animated
{ 
    //---------------------- instance variables ---------------------
    private int     _goalX;     // if moving, this is target location
    private int     _goalY;
    private int     _speed;     // current speed
    private double  _locX;
    private double  _locY;
    private double  _stepX;
    private double  _stepY;
    private int     _nSteps;
    
    private int     _homeX;
    private int     _homeY;
    
    // EMTVehicle display variables
    private JRectangle body;
    private int bodyW = 40, bodyH = 20;
    private int bodyX = 0;
    private int bodyY = 0;
    
    private int wheelY = 15;
    private int wheelDiam = 10;
    private JEllipse wheel1;
    private int wheel1X = 5;
    
    private JEllipse wheel2;
    private int wheel2X = 25;

    private boolean targetReached;
    
    //------------------------- constructor -----------------------
    /** Main Constructor.
     * Main constructor
     * @param x  int x-coord of location
     * @param y  int y-coord of location
     * @param bodyColor  main vehicle color
     */
    public EMTVehicle ( int x, int y, Color bodyColor ) 
    {
        /////////////////////////////////////////////////////////
        // Save the location as "home"
        // Create 2 or more J-objects to represent the vehicle
        //    Don't make it too big, less than 50x50 would be good
        // Add the J-objects using the add method
        // set the location
        ///////////////////////////////////////////////////////////
        _homeX = x;
        _homeY = y;

        wheel1 = new JEllipse( Color.BLACK );
        wheel1.setSize( wheelDiam, wheelDiam );
        wheel1.setLocation( wheel1X , wheelY  );
        this.add( wheel1 );
        wheel2 = new JEllipse( Color.black );
        wheel2.setLocation( wheel2X , wheelY );
        wheel2.setSize( wheelDiam , wheelDiam );
        this.add( wheel2 );
        body = new JRectangle( bodyColor );
        body.setSize( bodyW , bodyH );
        body.setLocation( bodyX, bodyY );
        this.add( body );
        setLocation( _homeX, _homeY );
        targetReached = false;
    }
    //------------------------- constructor ------------------------
     /** Another constructor.
     * override add method to compute and set bounds information as 
     * components are added to the object.
     * @param loc Point.
     * @param col Color.
     */
    public EMTVehicle ( Point loc, Color col ) 
    {  
        this( loc.x, loc.y, col );
    }
   
    private Rectangle  _bounds = null; 
     //---------------- add( JComponent ) ---------------------------
    /** .
     * override add method to compute and set bounds information as 
     * components are added to the object.
     * @param comp JComponent  component to be added
     */
    public void add( JComponent comp )
    {
        super.add( comp );
        if ( _bounds == null )
            _bounds = new Rectangle( comp.getBounds() );
        else
            _bounds = _bounds.union( comp.getBounds() );
        super.setBounds( _bounds ); // update location/size     
    }
    //------------------ travelTo( int, int, int ) -------------------
    /** .
     * travel to the specified location at the specified speed.
     * @param x int  x-pos of target location
     * @param y int  y-pos of target location
     * @param speed int  steps per frame to move
     * 
     */
    public void travelTo( int x, int y, int speed )
    {
        //////////////////////////////////////////////////////////////
        // Enable animation (setAnimated method)
        // compute distance from cur location to target
        // divide distance by speed to get n, # of "complete" steps
        // compute dx, dy (as float or double): 
        //                          step to take at each frame
        //  Hint: vehicle must travel from curX to goalX in n steps,
        //          so dx is ( goalX - curX ) / n; same for dy
        // define instance variables for cur location as float|double
        // save all this information in instance variables, to be used
        //     in newFrame method.
        //////////////////////////////////////////////////////////////
        targetReached = false;
        //stuff
        _goalX = x;
        _goalY = y;
        _speed = speed;
        _locX = this.getX();
        _locY = this.getY();

        double distanceX = _goalX - _locX;
        double distanceY = _goalY - _locY;
        double disX = distanceX * distanceX;
        double disY = distanceY * distanceY;
        double disH = disX + disY;
        double dist = Math.sqrt( disH );
        float d1 = (float) dist;
        int rounded = Math.round( d1 );
        int steps = (int) d1 / speed;
        double dx = distanceX / steps;
        double dy = distanceY / steps;
        int estimated = (int) dx;


        _nSteps = steps;

        _stepX = dx;
        _stepY = dy;


//test methods
        /*

        System.out.println("            ");
        System.out.println("Travel to on");
        System.out.println("Location is " + "(" + _locX +"," + _locY + ")");
        System.out.println("Speed is " + speed );
        System.out.println("Goal is " + distanceX + "," + distanceY );
        System.out.println("Squared: " + disX + "," + disY );
        System.out.println("Distance unsquared : " + disH );
        System.out.println("Dist simplified: " + dist );
        System.out.println("Lets see what that loks like as a float " + d1);
        System.out.println("Rounded that would be: " + rounded );
        System.out.println("Lets look at what we need for steps:" + steps );
        System.out.println("Our DX = " + _stepX);
        System.out.println("Our DY = " + _stepY );
        System.out.println("Esteimated Step! = " + estimated );
*/

/*
        private int     _goalX;     // if moving, this is target location
        private int     _goalY;
        private int     _speed;     // current speed
        private double  _locX;
        private double  _locY;
        private double  _stepX;
        private double  _stepY;
        private int     _nSteps;
*/





    }
    //++++++++++++++++++++++ Animated interface ++++++++++++++++++++++
    private boolean _animated = false;
    //---------------------- isAnimated() ----------------------------
      //---------------------- isAnimated() ------------------------------
    /** .
     * returns info about being animated; 
     * @return _animated boolean.
     */
    public boolean isAnimated()
    {
        return _animated;
    }

    //---------------------- targetReachedReset() ------------------------------
    /** .
     * returns info about target being reached reset; 
     */
    public void targetReachedReset()
    {
        targetReached = false;
    }

     //---------------------- isTargetReached() ------------------------------
    /** .
     * returns info about target being reached;
     * @return targetReached boolean.
     */
    public boolean isTargetReached()
    {
        return targetReached;
    }


    //---------------------- setAnimated( boolean ) ------------------
    /** .
     * turns animation on/off; 
     * update the position of the vehicle; check if it has reached the
     * @param onOff boolean.
     */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }
    //---------------------- newFrame() ------------------------------
    /**
     * invoked for each frame of animation; 
     * update the position of the vehicle; check if it has reached the
     * goal position -- if it has, turn off animation.
     */
    public void newFrame() 
    {
        if ( !_animated )
            return;
        //////////////////////////////////////////////////////////////
        // If we've reached the target, turn off animation and/or set 
        //    something so dispatcher knows that.
        // else if we still have full steps to take
        //    compute and set next floating point x,y location
        // else this is the last step
        //    set the location to the goal location
        //////////////////////////////////////////////////////////////

        if( _nSteps > 0 ) 
        {

            this.setLocation( this.getX() + (int) _stepX, this.getY() + 
                             (int) _stepY );  
            //System.out.println("We are on Step#:" + _nSteps);
            _nSteps = _nSteps - 1;
        }
        if( _nSteps == 0 )
        {
            targetReached = true;
     
            _nSteps = -1;
        }
        this.repaint();
        
        
    }
    //+++++++++++++++ end Animated interface ++++++++++++++++++++++++
    
    //--------------------- main -----------------------------------
    /** .
     * unit test.
     * @param args String[].
     */
    public static void main( String[] args )
    {     
        JFrame testFrame = new JFrame();
        testFrame.setSize( 700, 500 );  // define window size
        
        testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel testPanel = new JPanel( (LayoutManager) null );
        testFrame.add( testPanel );
        /*
        EMTVehicle d1 = new EMTVehicle( 400, 200, Color.GREEN );
        testPanel.add( d1 );
        System.out.println( d1.getLocation() );
        d1.setAnimated(true);
        System.out.println("Value is true" + d1.isAnimated() );
        d1.travelTo( 210 , 410, 6 );
*/
        EMTVehicle d2 = new EMTVehicle( new Point( 300, 300 ),
                                        Color.BLUE );
        d2.travelTo( 300 , 300 , 10 );
        d2.setAnimated( true );
        testPanel.add( d2 );
        
        testFrame.setVisible( true ); 
    }
}
