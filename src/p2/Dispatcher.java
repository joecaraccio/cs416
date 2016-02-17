package p2;
import src.*;

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
 * @author rdb
 * Last editted: 01/04/14 Format 
 * 
 */

import java.util.*;
import java.awt.*;

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
    
    //------------------ constructor -----------------------------
    public Dispatcher( 
                      ArrayList<Hospital> hospitals,
                      FrameTimer f1,
                      ArrayList<EmergencySite> Emergency,
                      EMTVehicle j,
                      JLine l1
                          // what else do you need or want?
                     )
    {
        line = l1;
        _hospitals = hospitals;
        _emergency = Emergency;
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
    /**
     * identify the next emergency site, if there is one
     */
    private EmergencySite getNextSite()
    {
        if( _emergency.size() > 0 ) {
            EmergencySite next = _emergency.get(0);
            return next;


        } else {
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

    public boolean delayer()
    {
        return delay;
    }

    public void setDelay( boolean t )
    {
        delay = t;
    }
    public int getMode()
    {
        return mode;
    }
    
    //++++++++++++++++++++++ Animated interface ++++++++++++++++++++++
    private boolean _animated = true;
    //---------------------- isAnimated() ----------------------------
    public boolean isAnimated()
    {
        return _animated;
    }
    //---------------------- setAnimated( boolean ) -----------------
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
        if( reset == false ) {
            car.travelTo(10, 10, EMTApp.normalSpeed);
            //System.out.println("Reseting...");
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

            //mode mode
            if( _emergency.size() > 0 )
            {
                reset = false;
                mode = 0;

            }
        }
        //////////////////////////////////////////////////////////////
        // If vehicle is moving, update its position (by calling its 
        //    newFrame method. 
        // Somehow, need to know if it has reached its goal position. 
        //    If so, figure out what the next goal should be.
        // 
        //    If previous goal was emergency site, new goal is hospital
        //    If previous goal was a hospital (or if it was at home,
        //       or if it was going home), new goal is the next
        //       emergency site, if there is one, or home if no more 
        //       emergencies.
        //////////////////////////////////////////////////////////////

        if( mode == 3 )
        {
            //System.out.println("Mode 3 lets go");
            Hospital h1 = this.getClosestHospital( car.getLocation() );
            line.setPoints( car.getLocation().x + car.getWidth()/2, car.getLocation().y + car.getHeight()/2, h1.getLocation().x, h1.getLocation().y );
            line.setVisible( true );
            car.travelTo( h1.getLocation().x, h1.getLocation().y, EMTApp.highSpeed );
            car.newFrame();
        }
        if( car.isTargetReached() == true )
        {
            //switch the mode
            //mode 0 is to go to an emergency site
            //mode 1 is to go a hospital

            if( mode == 0 )
            { //means we arrived at an emergency site

               _frametimer.restart();
                boolean justGoHome = false;
                if( _emergency.size() > 0 ) {
                    _emergency.get( 0 ).setColor( Color.BLUE );
                    _emergency.get(0).setVisible(false);
                    _emergency.remove(0); //removes the first object
                } else if ( _emergency.size() == 0 )
                {
                    justGoHome = true;
                    mode = 1;
                }

                mode = 3;

                    //now tells us what hopsital we need to go
               // if( !justGoHome ) {




                //}

            } else if ( mode == 1 )
            {

                _frametimer.restart();


                if( _emergency.size() > 0 )
                {

                    mode = 0;

                } else {
                    mode = 2;

                    //off mode basically
                }
            } else if ( mode == 3 )
            {


                if( _emergency.size() > 0 )
                {
                    _frametimer.restart();

                    mode = 0;

                } else {
                    _frametimer.restart();

                    mode = 2;

                    //off mode basically
                }
            }
        }

        //traveling to a emergency site
        if ( mode == 0 ) {

            if( this.getNextSite() != null )
            {
                EmergencySite e = this.getNextSite();
                currentSite = e;
            }
            currentSite.setNoDrag( true );

            int nextX = currentSite.getXLocation();
            int nextY = currentSite.getYLocation();
            //

            line.setPoints( car.getX() + car.getWidth()/2 , car.getY() + car.getHeight()/2 , nextX + currentSite.getWidth()/2  , nextY + currentSite.getHeight()/2 );
            line.setVisible( true );

            car.travelTo(currentSite.getXLocation()
                    , currentSite.getYLocation(), EMTApp.highSpeed
            );
            //mode = 1;
            car.newFrame();

        } else if( mode == 1 )
        {
            car.newFrame();
        }


    }
    //+++++++++++++++ end Animated interface +++++++++++++++++++++++++
}
