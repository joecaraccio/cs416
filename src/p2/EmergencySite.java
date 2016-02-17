package p2;
import src.*;
/**
 * EmergencySite -- represents an emergency site. 
 *   Dragging (mousePressed followed by mouseDragged) repositions it
 *     Not allowed to drag if its the current EmergencySite, easiest
 *     way to do that is make that site not draggable
 *  MouseClicked -- if clicked, this site is no longer an emergency 
 *     site; remove it from the set of sites or somehow make it known
 *     that it is not real emergency site any more.
 * 
 * @author rdb
 * Last edited: 01/28/14  
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class EmergencySite extends JRectangle
    implements MouseListener, MouseMotionListener, Draggable
{
    //--------------------- class variables -------------------------
    public static int    size = 10;
    
    //--------------------- instance variables ----------------------
    private boolean     _visited = false;
    
    //--------------------- constants -------------------------------
    private Color  fillColor = Color.RED;
    private Color  lineColor = Color.BLACK;
    private ArrayList<EmergencySite> list;
    private Dispatcher dis;
    private boolean solo;
    
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

        addMouseListener( this );
        addMouseMotionListener( this );
    }
    //+++++++++++++++++++ Draggable interface methods ++++++++++++++
    private boolean   _draggable = true; // true if obj can be dragged
    public void setDraggable( boolean onOff )
    {
        _draggable = onOff;
    }
    public boolean isDraggable()
    {
        return _draggable;
    }
    public boolean contains( java.awt.geom.Point2D point )
    {
        return getBounds().contains( point );
    }
    
    //++++++++++++++++ mouse methods / instance variables ++++++++++++
    private Point _saveMouse;   // last mouse position
    //   used for dragging      
    //+++++++++++++++++++++++++ mouseListener methods +++++++++++++++
    //-------------- mousePressed -----------------------------------
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

    public void soloSet( boolean t )
    {
        solo = t;
    }
    //-------------- mouseClicked -----------------------------------
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
        list.remove( this );
        this.setColor( Color.BLUE );
        //this.setColor( Color.green );
        //getParent().repaint();
    }

    public void dispatch( Dispatcher d )
    {
        dis = d;
    }

    public void setArray( ArrayList<EmergencySite> site )
    {
        list = site;
    }
    //--------------- unimplemented mouse listener methods -----------
    public void mouseReleased( MouseEvent me ){}
    public void mouseEntered( MouseEvent me ){}
    public void mouseExited( MouseEvent me ){}
    
    //+++++++++++++++++++ mouseMotionListener methods ++++++++++++++++
    //---------------- mouseDragged ----------------------------------
    public void mouseDragged( MouseEvent me )
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
        if( solo == false ) {
            boolean nodrag = false;
            if (list.size() > 0) {
                if (list.get(0) == this) {
                    nodrag = true;
                }
            }
            if (dis.getMode() != 0 && nodrag == true) {
                //do nothing
            } else {
                int dX = getParent().getMousePosition().x - _saveMouse.x;
                int dY = getParent().getMousePosition().y - _saveMouse.y;
                this.moveBy(dX, dY);
                _saveMouse = getParent().getMousePosition();

                getParent().repaint();
            }
        } else if ( solo == true )
        {
            int dX = getParent().getMousePosition().x - _saveMouse.x;
            int dY = getParent().getMousePosition().y - _saveMouse.y;
            this.moveBy(dX, dY);
            _saveMouse = getParent().getMousePosition();

            getParent().repaint();
        }
        
        
    }
    //----------------- mouseMoved not implemented -------------------
    public void mouseMoved( MouseEvent me ){}
    //+++++++++++++++++ end MouseMotionListeners +++++++++++++++++++++
    //------------- paintComponent( Graphics ) ----------------------

    public void setList( ArrayList<EmergencySite> e )
    {
        list = e;
    }

    //--------------------- main -----------------------------------
    /**
     * unit test
     */
    public static void main( String[] args )
    {     
        JFrame testFrame = new JFrame();
        testFrame.setSize( 700, 500 );  // define window size
        
        testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel testPanel = new JPanel( (LayoutManager) null );
        testFrame.add( testPanel );
        
        EmergencySite s1 = new EmergencySite( new Point( 200, 200 ));
        s1.soloSet( true );
        testPanel.add( s1 );
        
        EmergencySite s2 = new EmergencySite( new Point( 200, 100 ));
        s2.soloSet( true );
        testPanel.add( s2 );
        
        testFrame.setVisible( true );       
    }
}