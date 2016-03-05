
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * EMTPanel.java: Swing panel for EMT motion assignment.
 *     This class is responsible for 
 *     1. Creating and storing the hospitals (already finished).
 *     2. Creating and storing the emergency sites; the sites are 
 *        created in the JPanel, in response to a MousePressed event
 *        at the site location.
 *     3. Creating the EMTVehicle that must do the traversal
 *     4. Creating the line object that shows where the vehicle
 *        is going. It might be responsible for updating this line;
 *        or that responsibility could be delegated to another class.
 *      
 * @author Joe Caraccio
 * 02/02/11 Skeleton class created
 */
public class EMTPanel extends JPanel implements Animated, MouseListener
{ 
    //-------------------- class variables -------------------------
    
    //-------------------- instance variables ----------------------
    private JFrame               _frame;   
    private ArrayList<Hospital>  _hospitals;
    private ArrayList<EmergencySite> esite;
    private EMTVehicle j;
    private Dispatcher _dispatch;
    private FrameTimer timer;
    
    
    //--------------- constants -------------------------------------
    
    private Color[] hospColors = { Color.WHITE, Color.RED, Color.PINK };
    private int[]   hospitalX = { 50, 500, 500, 250, 300 };
    private int[]   hospitalY = { 400, 100, 350, 400, 50 };
    private int     hospitalW = 80;
    private int     hospitalH = 60;
    
    //------------- Constructor ---------------------------------
    /**
     * Create and manage an EMTVehicle using Swing.
     * Create the hospital objects
     * 
     * @param frame JFrame   window for app
     */
    public EMTPanel( JFrame frame ) 
    {
        super();
        _frame = frame;
        
        this.setLayout( null );
        this.addMouseListener( this );
        this.setBackground( Color.LIGHT_GRAY );
        
        Color emtColor = new Color( 0, 255, 0, 128 ); 
        // semitransparent green
        
        EMTApp.numHospitals = Math.min( EMTApp.numHospitals, 
                                       hospitalX.length );
        
        //////////////////////////////////////////////////////////////
        // Create a line object that shows where the vehicle is going
        //   if it is going to an emergency site or a hospital.
        // Add it to the panel.
        // This object could be created in another class, but it does
        //    need to be added to this panel.
        //////////////////////////////////////////////////////////////
        JLine l1 = new JLine( Color.red );
        l1.setVisible( false );
        this.add( l1 );
        
        /////////////////////////////////////////////////////////////
        // Create an EMTVehicle and add it to the panel.
        // This also could be created in another class and added to
        //    this panel.
        /////////////////////////////////////////////////////////////
        j = new EMTVehicle( 10, 10, Color.red );
        //j.setAnimated(true);
        
        this.add( j );
        
        /////////////////////////////////////////////////////////////
        // Create a FrameTimer with an initial delay of 2000 millisecs
        //   and a delay of 100 millisecs.
        // Start the timer.
        /////////////////////////////////////////////////////////////
        
        
        
        //////////////////////////////////////////////////////////////
        // Create a collection object to hold the emergency sites.
        //////////////////////////////////////////////////////////////
        esite = new ArrayList<EmergencySite>();
        
        Point p1 = new Point( 300 , 300 );
        //EmergencySite e1 = new EmergencySite( p1 );
        //e1.setLocation( 400 , 400 );
        //e1.setArray( esite );
        //esite.add( e1 );
        //this.add( e1 );
        
        
        
        //----------- make the hospitals 
        makeHospitals();
        
        ///////////////////////////////////////////////////////////////
        // Create a Dispatcher, who is responsible for controlling the
        //    travels of the vehicle. 
        // The Dispatcher has to know about or have access to (at least)
        //    -- the collection of emergency sites, 
        //    -- the hospitals array list
        //    -- the timer
        ///////////////////////////////////////////////////////////////
        timer = new FrameTimer( 100, this );
        timer.setInitialDelay( 2000 );
        timer.start();
        
        _dispatch = new Dispatcher( _hospitals, timer,  esite ,  j , l1 );
        _dispatch.setAnimated( true );
        
        //e1.dispatch( _dispatch );
        
    } 
    //---------------------- makeHospitals() -------------------------
    /**
     * create the desired number of hospitals
     * This method is complete.
     */
    private void makeHospitals()
    {
        _hospitals = new ArrayList<Hospital>();
        for ( int s = 0; s < EMTApp.numHospitals; s++ )
        {
            int hx = hospitalX[ s ];
            int hy = hospitalY[ s ];
            Color hColor = hospColors[ s % hospColors.length ];
            Hospital h = new Hospital( hColor, hx, hy, hospitalW, hospitalH );
            
            _hospitals.add( h );
        }
    }
    
    
    
    
    
    //+++++++++++++++++++ Animated methods +++++++++++++++++++++++
    private boolean _animated = true;
    /**
     * return true always. Program makes no sense with animation off
     * @return _animated boolean.
     */
    public boolean isAnimated()
    {
        return _animated; // can't turn off animation
    }
    /**
     * cannot turn off animation, so this method is a no-op.
     * 
     * @param onOff boolean   turns animation on or off
     */
    public void setAnimated( boolean onOff )
    {
    }
    //---------------------- newFrame ------------------------------
    /**
     * move emt for each new frame; this is called by the FrameTimer 
     * listener; it  gets invoked when the time interval elapses and awt 
     * creates an event.
     */
    public void newFrame()
    {
        _dispatch.newFrame();
        
        
        //////////////////////////////////////////////////////////////////
        // Your Dispatcher objects newFrame needs to be called; 
        //   depending on your design decisions, other things might
        //   also be done here.
        //////////////////////////////////////////////////////////////////
        
        repaint();
    }
    
    //++++++++++++++++++ MouseListener methods +++++++++++++++++++++++++++
    // You need to implement mousePressed; the others must be there but
    //   will remain "empty".
    //
    //------------------- mousePressed( MouseEvent ) ----------------------
    /**
     * On mousePressed, replace current site with a site at mouse position.
     *
     * @param me  MouseEvent  event data
     */
    public void mousePressed( MouseEvent me )
    {      
        //System.out.println("New site: " + me.getPoint());
        ////////////////////////////////////////////////////////////////
        // Need to add a new Emergency site here
        ////////////////////////////////////////////////////////////////
        EmergencySite e1 = new EmergencySite( me.getPoint() );
        e1.setArray( esite );
        e1.dispatch( _dispatch );
        esite.add( e1 );
        this.add( e1 );
        repaint();      
    }
    //------------- unused interface methods -----------------
    
    //----------------- mouseDragged() -----------------------
    /** .
      * Mouse Function
      * 
      * @param me MouseEvent mouse event.
      */
    public void mouseDragged( MouseEvent me )
    { }
    //----------------- mouseClicked() -----------------------
    /** .
      * Mouse Function
      * 
      * @param me MouseEvent mouse event.
      */
    public void mouseClicked( MouseEvent me ) 
    {
        
        
    }
    //----------------- mouseEntered() -----------------------
    /** .
      * Mouse Function
      * 
      * @param me MouseEvent mouse event.
      */
    public void mouseEntered( MouseEvent me ) 
    { }
    //----------------- mouseExited() -----------------------
    /** .
      * Mouse Function
      * 
      * @param me MouseEvent mouse event.
      */
    public void mouseExited( MouseEvent me ) 
    { }
    //----------------- mouseMoved() -----------------------
    /** .
      * Mouse Function
      * 
      * @param me MouseEvent mouse event.
      */
    public void mouseMoved( MouseEvent me ) 
    { }
    //----------------- mouseReleased() -----------------------
    /** .
      * Mouse Function
      * 
      * @param me MouseEvent mouse event.
      */
    public void mouseReleased( MouseEvent me )
    { }
    //++++++++++++++++++++++ end Mouse Listener methods +++++++++++++++++
    
    //----------------- paintComponent( Graphics) -----------------------
    /** .
      * Call AWT object display methods
      * 
      * @param g Graphics  graphics context
      */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        for ( Hospital h: _hospitals )
            h.display( (Graphics2D ) g );
        
    }
    
    //++++++++++++++++++++++ convenience system test ++++++++++++++++++++
    //------------------------ main -----------------------------------
    /** .
      * Call AWT object display methods
      * 
      * @param args String[]  .
      */
    public static void main( String[] args )
    {
        EMTApp.main( args );
        
        
    }
}
