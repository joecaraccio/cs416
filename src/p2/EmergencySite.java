

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
/**
 * EmergencySite -- represents an emergency site. 
 *   Dragging (mousePressed followed by mouseDragged) repositions it
 *     Not allowed to drag if its the current EmergencySite, easiest
 *     way to do that is make that site not draggable
 *  MouseClicked -- if clicked, this site is no longer an emergency 
 *     site; remove it from the set of sites or somehow make it known
 *     that it is not real emergency site any more.
 * 
 * @author Joe Caraccio
 * Last edited: 01/28/14  
 */
public class EmergencySite extends JRectangle
    implements MouseListener, MouseMotionListener, Draggable
{
    //--------------------- class variables -------------------------
    private int    size = 10;
    
    //--------------------- instance variables ----------------------
    private boolean     _visited = false;
    
    //--------------------- constants -------------------------------
    private Color  fillColor = Color.RED;
    private Color  lineColor = Color.BLACK;
    private ArrayList<EmergencySite> list;
    private Dispatcher dis;
    private boolean solo;
    private boolean noDrag;
    
    //---------------------- constructors ---------------------------
    /**
     * Generate a site at the specified location.
     * 
     * @param p Point   location of emergency
     */
    public EmergencySite( Point p )
    {
        super( p.x, p.y );
        setFillColor( fillColor );
        setFrameColor( lineColor );
        setSize( size, size );
        solo = false;
        noDrag = false;
        
        addMouseListener( this );
        addMouseMotionListener( this );
    }
    //+++++++++++++++++++ Draggable interface methods ++++++++++++++
    private boolean   _draggable = true; // true if obj can be dragged
    
    //---------------- setDraggable ----------------------------------
    /** .
      * countains method.
      * @param onOff boolean.
      */
    public void setDraggable( boolean onOff )
    {
        _draggable = onOff;
    }
    
    //---------------- isDraggable ----------------------------------
    /** .
      * is draggable.
      * @return _draggable boolean.
      */
    public boolean isDraggable()
    {
        return _draggable;
    }
    
    //---------------- contains ----------------------------------
    /** .
      * countains method.
      * @param point java.awt.geom.Point2D;
      * @return getBounds.Countains(Point) boolean .
      */
    public boolean contains( java.awt.geom.Point2D point )
    {
        return getBounds().contains( point );
    }
    
    //++++++++++++++++ mouse methods / instance variables ++++++++++++
    private Point _saveMouse;   // last mouse position
    //   used for dragging      
    //+++++++++++++++++++++++++ mouseListener methods +++++++++++++++
    //-------------- mousePressed -----------------------------------
    //---------------- mousePressed ----------------------------------
    /** .
      * mouse pressed
      * @param me MouseEvent.
      */
    public void mousePressed( MouseEvent me )
    {
        /////////////////////////////////////////////////////////////
        // me.getPoint(), which we've used before is the location of 
        // mouse "inside" the JComponent; this won't work.
        //
        // We need position of mouse in the container that holds the
        // JComponent: getParent().getMousePosition()
        //
        // Assign it to the instance variable, _saveMouse
        /////////////////////////////////////////////////////////////
        
        _saveMouse = getParent().getMousePosition();
        
        
    }
    private EMTPanel em2;
    
    //---------------- soloSet ----------------------------------
    /** .
      * solo setter
      * @param t boolean.
      */
    public void soloSet( boolean t )
    {
        solo = t;
    }
    //-------------- mouseClicked -----------------------------------
    //---------------- mouseClicked ----------------------------------
    /** .
      * mouse clicked.
      * @param me MouseEvent.
      */
    public void mouseClicked( MouseEvent me )
    {
        //////////////////////////////////////////////////////////////
        // On mouse click, this site should be removed from the
        //    sites array.
        // Figure out a way for this class to 
        //    - know about the sites array (so it can remove itself), OR
        //    - for this class to be able to call somebody that it 
        //      knows about who knows about the sites array, OR
        //    - to put information into this object so that some other 
        //      object will know this site is no longer a real site
        //      and should not be visited by the emt vehicle.
        //////////////////////////////////////////////////////////////
        if( solo == false ) 
        {
            list.remove( this );
        }
        this.setColor( Color.BLUE );
        //this.setColor( Color.green );
        //getParent().repaint();
    }
    
    //---------------- dispatch ----------------------------------
    /** .
      * dispatcher.
      * @param d Dispatcher.
      */
    public void dispatch( Dispatcher d )
    {
        dis = d;
    }
    
    //---------------- setArray ----------------------------------
    /** .
      * SetArray
      * @param site ArrayList<EmergencySite>.
      */
    public void setArray( ArrayList<EmergencySite> site )
    {
        list = site;
    }
    //--------------- unimplemented mouse listener methods -----------
    //---------------- mouseReleased ----------------------------------
    /** .
      * Mouse Released
      * @param me MouseEvent.
      */
    public void mouseReleased( MouseEvent me )
    {
    }
    
    //---------------- mouseEntered ----------------------------------
    /** .
      * Mouse Entered
      * @param me MouseEvent.
      */
    public void mouseEntered( MouseEvent me )
    {
    }
    
    //---------------- mouseExited ----------------------------------
    /** .
      * mouse exited
      * @param me MouseEvent.
      */
    public void mouseExited( MouseEvent me )
    {
    }
    
    //---------------- setNoDrag ----------------------------------
    /** .
      * set no drag
      * @param t boolean.
      */
    public void setNoDrag ( boolean t )
    {
        noDrag = t;
    }
    
    //+++++++++++++++++++ mouseMotionListener methods ++++++++++++++++
    //---------------- mouseDragged ----------------------------------
    /** .
      * mouse dragged
      * @param me MouseEvent.
      */
    public void mouseDragged( MouseEvent me )
    {
        if( noDrag == false ) 
        {
            
            //////////////////////////////////////////////////////////////
            //  IF this object is draggable
            //     Get new position of mouse:
            //         getParent().getMousePosition()
            //     For each of x and y coordinates, compute
            //       dX = newX - oldX (stored in _saveMouse.x)
            //       dY = newY - oldY (stored in _saveMouse.y)
            //     invoke moveBy( dX, dY )
            //     Save new position in _saveMouse
            //     getParent().repaint()
            //////////////////////////////////////////////////////////////
            if ( solo == false ) 
            {
                boolean nodrag = false;
                if ( list.size() > 0 ) 
                {
                    if ( list.get( 0 ) == this ) 
                    {
                        nodrag = true;
                    }
                }
                if ( dis.getMode() != 0 && nodrag == true ) 
                {
                    //do nothing
                } else 
                {
                    int dX = getParent().getMousePosition().x - _saveMouse.x;
                    int dY = getParent().getMousePosition().y - _saveMouse.y;
                    this.moveBy( dX, dY );
                    _saveMouse = getParent().getMousePosition();
                    
                    getParent().repaint();
                }
            } else if ( solo == true ) 
            {
                int dX = getParent().getMousePosition().x - _saveMouse.x;
                int dY = getParent().getMousePosition().y - _saveMouse.y;
                this.moveBy( dX, dY );
                _saveMouse = getParent().getMousePosition();
                
                getParent().repaint();
            }
            
        }
    }
    //----------------- mouseMoved not implemented -------------------
    /** .
      * mouse moved.
      * @param me MouseEvent.
      */
    public void mouseMoved( MouseEvent me )
    {
    }
    //+++++++++++++++++ end MouseMotionListeners +++++++++++++++++++++
    //------------- paintComponent( Graphics ) ----------------------
    
    //--------------------- setList -----------------------------------
    /** .
      * list setter.
      * @param e ArrayList<EmergencySite> e.
      */
    public void setList( ArrayList<EmergencySite> e )
    {
        list = e;
    }
    
    //--------------------- main -----------------------------------
    /** .
      * unit test
      * @param args String[].
      */
    public static void main( String[] args )
    {     
        JFrame testFrame = new JFrame();
        testFrame.setSize( 700, 500 );  // define window size
        
        testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel testPanel = new JPanel( ( LayoutManager ) null );
        testFrame.add( testPanel );
        
        EmergencySite s1 = new EmergencySite( new Point( 200, 200 ) );
        s1.soloSet( true );
        testPanel.add( s1 );
        
        EmergencySite s2 = new EmergencySite( new Point( 200, 100 ) );
        s2.soloSet( true );
        testPanel.add( s2 );
        
        testFrame.setVisible( true );       
    }
}