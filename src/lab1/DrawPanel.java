//+++++++++++++++++++ DrawPanel.java ++++++++++++++++++++++++++++
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/** 
 * DrawPanel.java: Swing panel for AWT draw assignment.
 * 
 * This is a skeleton for the main display window for an awt application. 
 *      
 * @author rdb
 * 01/10/08 
 * Last edit: 01/12/15 make checkstyle compliant
 */

public class DrawPanel extends JPanel
{ 
    //-------------------- instance variables ----------------------
    /////////////////////////////////////////////////////////////
    // Define an ArrayList or Vector variable to store references
    //   to all objects to be displayed.
    /////////////////////////////////////////////////////////////
    private ArrayList<AShape> list;
    
    
    
    //------------- Constructor ---------------------------------
    /**
     * Create awt objects to be displayed.
     * 
     * @param args String[]  command line arguments
     */
    public DrawPanel( String [ ] args ) 
    {  
        ////////////////////////////////////////////////////////////////////
        // initialize the ArrayList<AShape> defined as an instance variable
        //   then call makeScene() to create graphical objects in the scene.
        ///////////////////////////////////////////////////////////////////
        list = new ArrayList<AShape>();

        makeScene();
    }  
    //------------------- makeScene() ----------------------------
    /**
     * Create the wheels objects that make up the scene.
     */
    private void makeScene()
    {
        /////////////////////////////////////////////////////////////////
        // 1. copy WheelsApp.makeScene() here
        // 2. Change wheels object names (Rectangle, Ellipse, Line) 
        //    to awt "wrapper" object names (ARectangle, AEllipse, ALine)
        // 3. Change WheelsBot references to AWTBot
        // 4. Add all object references to the ArrayList<AShape> (or Vector) 
        /////////////////////////////////////////////////////////////////

        ARectangle rect1 = new ARectangle( Color.BLUE );
        rect1.setLocation( 100, 100 );
        rect1.setSize( 40, 40 );
        
        ARectangle rect2 = new ARectangle( Color.RED );
        rect2.setLocation( 200, 200 );
        rect2.setSize( 20, 60 );
        
        ALine line1 = new ALine();
        line1.setColor( Color.BLACK );
        line1.setPoints( 120, 120, 210, 230 );
        
        AEllipse ell1 = new AEllipse( Color.CYAN );
        ell1.setLocation( 10, 400  );
        ell1.setSize( 40, 10 );
        
        AEllipse ell2 = new AEllipse( Color.MAGENTA );
        ell2.setLocation( 400, 400 );
        ell2.setSize( 30, 30 );
        
        ALine line2 = new ALine();
        line2.setColor( Color.BLACK );
        line2.setPoints( 25, 405, 415, 415 );  
        
        
        AWTBot robot1 = new AWTBot( Color.YELLOW );
        robot1.setLocation( 450, 300 );        
        AWTBot robot2 = new AWTBot( Color.CYAN );
        robot2.setLocation( 400, 20 );
        
          ARoundRectangle rr1 = new ARoundRectangle( 300 , 100 );
           ARoundRectangle rr2 = new ARoundRectangle( 300 , 200 );
        
        list.add(rect1);
        list.add(rect2);
        list.add(line1);
        list.add(ell1);
        list.add(ell2);
        list.add(line2);
        list.add(robot1);
        list.add(robot2);
        list.add(rr1);
        list.add(rr2);
    
    }
    //------------- paintComponent ---------------------------------------
    /**
     * This method is called from the Java environment when it determines
     * that the JPanel display should be updated; you need to 
     * make appropriate calls here to re-draw the graphical images on
     * the display. Each object created in the constructor above should 
     * have its "fill" and/or "draw" methods invoked with a Graphics2D 
     * object. The Graphics object passed to paintComponent will be a 
     * a Graphics2D object, so it can be coerced to that type and
     * passed along to the "display" method of the objects you created.
     * 
     * Note that "display" is not an awt method, but a convenience
     * method defined by the "wrapper" classes. The display method 
     * passes the graphical objects to both the Graphics2D.fill and
     * Graphics2D.draw methods, except for ALine graphical objects 
     * which cannot be "filled".
     * 
     * @param aBrush java.awt.Graphics   Java graphics context
     */
    public void paintComponent( Graphics aBrush )
    {
        super.paintComponent( aBrush );
        Graphics2D brush2D = (Graphics2D) aBrush; // coerce to Graphics2D
        
        ///////////////////////////////////////////////////////////////////
        // foreach awt graphics object instance variable, var:
        //    var.display( brush2D );
        ///////////////////////////////////////////////////////////////////
        
        for(int i = 0; i < list.size(); i++) {
         list.get(i).display( brush2D);   
        }
        

    
    }
    //-------------------- main --------------------------------------------
    /**
     * Invoke SwingApp.main. A convenience start mainly for drjava.
     * 
     * @param args String[]  command line arguments
     */
    public static void main( String[] args )
    {
        SwingApp.main( args );
    }
}
