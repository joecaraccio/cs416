
import java.util.*;
import java.awt.*;
/**
 * Dispatcher.java -- Controls the activities of the EMTVehicle
 *    Resonsibilities:
 *    1. Know the current state of the vehicle: is it at home,
 *       heading to an emergency, heading for hospital, heading home.
 *    2. Tell the vehicle what to do next. Normally, this will be
 *       after it gets to its next destination. However, if the
 *       vehicle is heading home and an emergency site gets added,
 *       it should get re-directed to the site.
 *    3. It must know about or be able to get information from
 *       a. the emergency sites collection
 *       b. the hospital collection
 *       c. the timer (because it needs to know when that 
 *          vehicle needs to wait for 2 seconds. The easiest
 *          way to do this is to simply "restart()" the timer.
 *          This works because there is nothing else moving in
 *          this application except one vehicle. If that weren't
 *          the case, the timer would have to keep running and
 *          the code would have to count events in order to figure
 *          out when 2 seconds had elapsed.)
 * 
 * Framework:
 *    This class should implement the Animated interface, and get
 *    called (by EMTPanel) on every frame event. For example, 
 *    if the vehicle is home or going home and there is an entry 
 *    in the sites array, the dispatcher needs to tell the vehicle 
 *    to go to the next site in the array. There are of course 
 *    several other cases.
 * 
 * @author Joe Caraccio
 * Last editted: 01/04/14 Format 
 * 
 */
public class Dispatcher implements Animated
{
    //-------------------- instance variables ---------------------
    private ArrayList<Hospital> _hospitals;
    private ArrayList<EmergencySite> _emergency;
    private FrameTimer _frametimer;
    private EMTVehicle car;
    private JLine line;
    private int mode;
    private EmergencySite currentSite;
    private boolean delay;
    private boolean reset;
    
//---------------------- Dispatcher() -------------------------
    /** .
      * constructor.
      * @param hospitals ArrayList<Hospital>.
      * @param f1 FrameTimer.
      * @param emergency1 ArrayList<EmergencySite>.
      * @param j EMTVehicle.
      * @param l1 Jline.
      */
    public Dispatcher( ArrayList<Hospital> hospitals, FrameTimer f1,
                      ArrayList<EmergencySite> emergency1,
                      EMTVehicle j,
                      JLine l1 )
    {
        line = l1;
        _hospitals = hospitals;
        _emergency = emergency1;
        _frametimer = f1;
        car = j;
        delay = false;
        // most/all of what you get will be saved in instance variables
        car.setAnimated( true );
        mode = 0;
        //mode 0 is traveling  to emergency site
        //mode 1 is traveling to hospital
        reset = false;
    }
    //---------------------- setNextSite() -------------------------
    /** .
      * identify the next emergency site, if there is one
      * @return next EmergencySite.
      */
    private EmergencySite getNextSite()
    {
        if( _emergency.size() > 0 ) 
        {
            EmergencySite next = _emergency.get( 0 );
            return next;
            
            
        } else 
        {
            return null;
        }
        //////////////////////////////////////////////////////////////
        // get next site from list of sites or from someone who has
        //    list of sites.
        //////////////////////////////////////////////////////////////
        
        
        
    }
    //--------------- getClosestHospital( Point ) --------------------
    /**
     * find the closest hospital site to the parameter
     *    This method is complete.
     * Note that we don't bother to compute the correct distance,
     *    we make the decision based on the square of the distance
     *    which saves the computation of the square root, a reasonably
     *    costly operation that serves no purpose for this test.
     * 
     * @param  loc  Point  location of emt vehicle
     * @return      Hospital closest hospital to vehicle at loc
     */
    private Hospital getClosestHospital( Point loc )
    {
        double distanceSq = Float.MAX_VALUE;
        Hospital   closest    = null;
        for ( Hospital h: _hospitals )
        {
            double d2 = loc.distanceSq( h.getLocation() );
            if ( d2 < distanceSq )
            {
                distanceSq = d2;
                closest = h;
            }
        }
        return closest;
    }
    //---------------------- delayer() -----------------
    /**
     * return delay.
     * @return delay boolean.
     */
    public boolean delayer()
    {
        return delay;
    }
    
    //---------------------- setDelay() -----------------
    /**
     * starts the delay.
     * @param t boolean.
     */
    public void setDelay( boolean t )
    {
        delay = t;
    }
    //---------------------- getMode() -----------------
    /**
     * gets the mode.
     * @return mode int.
     */
    public int getMode()
    {
        return mode;
    }
    
    //++++++++++++++++++++++ Animated interface ++++++++++++++++++++++
    private boolean _animated = true;
    //---------------------- isAnimated() -----------------
    /**
     * sets the animated option.
     * @return _animated boolean.
     */
    public boolean isAnimated()
    {
        return _animated;
    }
    //---------------------- setAnimated( boolean ) -----------------
    /**
     * sets the animated option.
     * 
     * @param  onOff  boolean  setAnimated.
     */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }
    //---------------------- newFrame ------------------------------
    /**
     * invoked for each frame of animation; figure out what to do with
     *    the vehicle at this frame.
     */
    public void newFrame()
    {
        if( mode == 2 )
        {
            line.setVisible( false );
            if( reset == false ) 
            { 
                car.travelTo( 10, 10, EMTApp.normalSpeed );
                if( car.getLocation().x == 10 )
                {
                    if( car.getLocation().y == 10 )
                    {
                        reset = true;
                    }
                }
            }
            car.targetReachedReset();
            car.newFrame();
            if( _emergency.size() > 0 )
            {
                reset = false;
                mode = 0;   
            }
        }
        if( mode == 3 )
        {
            Hospital h1 = this.getClosestHospital( car.getLocation() );
            line.setPoints( car.getLocation().x + car.getWidth() / 2, 
                           car.getLocation().y + car.getHeight() / 2, 
                           h1.getLocation().x, h1.getLocation().y  );
            line.setVisible( true );
            car.travelTo( h1.getLocation().x, 
                         h1.getLocation().y , EMTApp.highSpeed );
            car.newFrame();
        }
        if( car.isTargetReached() == true )
        {
            if( mode == 0 )
            {    
                _frametimer.restart();
                boolean justGoHome = false;
                if( _emergency.size() > 0 ) 
                {
                    _emergency.get( 0 ).setColor( Color.BLUE );
                    _emergency.get( 0 ).setVisible( false );
                    _emergency.remove( 0 ); //removes the first object
                } else if ( _emergency.size() == 0 )
                {
                    justGoHome = true;
                    mode = 1;
                }
                mode = 3;               
            } else if ( mode == 1 )
            {     
                _frametimer.restart();          
                if( _emergency.size() > 0 )
                {                  
                    mode = 0;               
                } else 
                {
                    mode = 2;
                }
            } else if ( mode == 3 )
            {
                if( _emergency.size() > 0 )
                {
                    _frametimer.restart();
                    mode = 0;
                } else 
                {
                    _frametimer.restart();
                    mode = 2;
                }
            }
        }
        if ( mode == 0 ) 
        {
            if( this.getNextSite() != null )
            {
                EmergencySite e = this.getNextSite();
                currentSite = e;
            }
            currentSite.setNoDrag( true );
            
            int nextX = currentSite.getXLocation();
            int nextY = currentSite.getYLocation();
            line.setPoints( car.getX() + car.getWidth() / 2 , 
                           car.getY() + car.getHeight() / 2 , 
                           nextX + currentSite.getWidth() / 2  , 
                           nextY + currentSite.getHeight() / 2 );
            line.setVisible( true );
            
            int tmpx = currentSite.getXLocation();
            car.travelTo( tmpx, currentSite.getYLocation(), EMTApp.highSpeed );
            car.newFrame();
            
        } else if( mode == 1 )
        {
            car.newFrame();
        }    
    }
    //+++++++++++++++ end Animated interface +++++++++++++++++++++++++
}