package p6;

/**
 * Interpreter.java - parses string input representing an infix arithmetic
 *                 expression into tokens, and builds an expression tree.
 *                 The expression can use the operators =, +, -, *, /, %.
 *                 and can contain arbitrarily nested parentheses.
 *                 The = operator is assignment and must be absolutely lowest
 *                 precedence.
 * March 2013
 * rdb
 */
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Interpreter //extends JFrame
{
   //----------------------  class variables  ------------------------

   //---------------------- instance variables ----------------------
   private boolean      _printTree = true;  // if true print tree after each
                                           //    expression tree is built
    private boolean _printToggle = false;

   //if its a valuable we look it up in the symbol tree

    private String treememory = "";

   private SymbolTable _symbolTable;
   //----------- constants
   
   //--------------------------- constructor -----------------------
   /**
    * If there is a command line argument use it as an input file.
    * otherwise invoke an interactive dialog.
    */
   public Interpreter( String[] args ) 
   {
      _symbolTable = SymbolTable.instance();
      if ( args.length > 0 )
         processFile( args[ 0 ] );
      else
         interactive();      
   }
   //--------------------- processFile -----------------------------
   /**
    * Given a String containing a file name, open the file and read it.
    * Each line should represent an expression to be parsed.
    */
   public void processFile( String fileName )
   {

      try
      {
         Scanner s1 = new Scanner( new File( fileName ) );
         while( s1.hasNextLine() )
         {
            String s = s1.nextLine();
            //System.out.println( s );
           String s11 =  processLine( s );
            System.out.println( s11 );
             if( _printTree == true ){
                 System.out.println( treememory );
             }
         }


      } catch ( FileNotFoundException fe )
      {
         System.out.println( "Processing File did not work" );
      }


      //infix to tree algorithm

   }
   //--------------------- processLine -----------------------------
   /**
    * Parse each input line -- it shouldn't matter whether it comes from
    * the file or the popup dialog box. It might be convenient to return
    * return something to the caller in the form of a String that can
    * be printed or displayed.
    */
   public String processLine( String line )
   {
      //System.out.println(  "Input: " + line );
      String trimmed = line.trim();
      if ( trimmed.length() == 0 || trimmed.charAt( 0 ) == '#' ) {
          return line;
      } else if( trimmed.charAt( 0 ) == '@'){
          String message = "";
          Scanner s1 = new Scanner( trimmed );
          String firstCommand = s1.next();
          String secondCommand = "";
          if( s1.hasNext() ) {
              secondCommand = s1.next();
          }
          //System.out.println( "first " + firstCommand );
          //System.out.println( "second " + secondCommand );
          if( firstCommand.equals( "@print" ) ){
              if( secondCommand.equals("on") || secondCommand.equals("ON") ){
                  _printTree = true;
                  message = "-- Print Command On";
              } else if( secondCommand.equals("off") || secondCommand.equals("OFF") ){
                  _printTree = false;
                  message = "-- Print Command Off";
              } else if( secondCommand.equals("") ){
                  message = "-- Toggling on for previous tree";
                  message = message + "\n" + treememory;
              } else {
                  message = "-- Unrecognized Command for Print";
              }

          } else if( firstCommand.equals( "@lookup" ) ){

              if( secondCommand.equals("") ){
                  message = "--Full Symbol Table";
                 String table =  _symbolTable.toString();
                  message = message + "\n" + table;
                  //System.out.println( table );
              } else {
                  Float f2 = _symbolTable.getValue( secondCommand );
                  String results = "";
                  results = "{ " + secondCommand + " : " + f2 + "}";
                  message = "-- Lookup for " + secondCommand + ",";
                  while( s1.hasNext() ){
                      String snext = s1.next();
                      message = message + snext + ",";
                     Float f1 = _symbolTable.getValue( snext );
                      results = results + "\n" + "{ " + snext + " : " + f1 + "}";

                  }
                  message = message + "\n" + results;
              }

          } else {
              message = "--Unreconignized Meta Command!";
          }
          return line + message;
      }
      else
      {
          System.out.println( "Processing: " + trimmed );
         return processExpr( trimmed );

      }
   }

   //------------------ processExpr( String ) -------------------------
   /**
    * Get all fields in the expression, and
    * generate tokens for all of them, and
    * return a String representation of all of them.
    *
    * @param line String.
    * @return s String.
    */
   public String processExpr( String line )
   {
      Scanner s1 = new Scanner( line );
       String answer = "";
       //Pre Test To ensure everything is good
       while( s1.hasNext() )
       try{
           EToken e90 = TokenFactory.makeToken( s1.next() );
       } catch ( Exception e1){
           System.out.println( e1.getMessage() );
           return "";
       }


      Scanner s2 = new Scanner( line );
      String message = "";

      Stack<EToken> opStack = new Stack<EToken>();
      Stack<Node> randStack = new Stack<Node>();
       String l1 = line.trim();
      String stuff = "";
      while( s2.hasNext() ){
          EToken q1 = null;
          String s21 = "";
          try {
              s21 = s2.next();
              q1 = TokenFactory.makeToken( s21 );
              //System.out.println( "Succesfully making " + s21 );
          } catch (Exception e) {
              System.out.println( "Error Creating Token: " + s21 );
              break;
          }

          if (q1 instanceof Operand) {
              Node n1 = new Node( q1 );
              randStack.push(n1);
          } else if ( q1.op().equals("(") ) {

              opStack.push( q1 );
          } else if (q1.op().equals(")")) {
              while (!opStack.top().op().equals("(")) {
                  Node n1 = new Node(opStack.pop());
                  //System.out.println()
                  n1.right = randStack.pop();
                  n1.left = randStack.pop();
                  randStack.push(n1);

              }
              opStack.pop(); //pop "("

          } else {
              while (!opStack.isEmpty() && proc( opStack.look(0) ) >= proc( q1 )) {
                  Node n1 = new Node(opStack.pop());
                  n1.right = randStack.pop();
                  n1.left = randStack.pop();
                  randStack.push(n1);
              }
              opStack.push(q1);


          }// end of while loop

          }
       while( opStack.size() > 0 ){
           Node n1 = new Node( opStack.pop() );
           n1.right = randStack.pop();
           n1.left  = randStack.pop();
           randStack.push( n1 );

       }
       //print if _printTree (instance varible) is on
       /*
        if( _printTree || _printToggle ){
            _printToggle = false;
            randStack.top().printTree( randStack.top() , 0 );
            treememory = randStack.top().printval;
           // System.out.println(  treememory );

        }
        */
       randStack.top().printTree( randStack.top() , 0 );
       treememory = randStack.top().printval;

       answer = "Output: " + randStack.top().evaluate( randStack.top() , _symbolTable);
        //System.out.println( "Output: " + answer );

      return answer;
   }

    public void traverse( Node root, int k ){

        String space = " ";
        String message = "";
        for( int i = 0; i < k; i++ ){
            message = message + space;
        }
        System.out.println( message + root.value.op() );

        if( root.left != null ){
            traverse( root.left , k + 1);
        }
        if( root.right != null ){
            traverse( root.right , k + 1 );
        }
    }

public int proc( EToken e ){
   String group1 = "*/%";
   String group2 = "+-";
   String group3 = "=";
   if( group1.contains(  e.op()  )){
      return 3;
   }
   else if( group2.contains(  e.op()  )){
      return 2;
   }
   else if( group3.contains(  e.op()  )){
      return 1;
   }

   else{
      return -1;
   }
}

   //--------------------- interactive -----------------------------
   /**
    * Use a file chooser to get a file name interactively, then 
    * go into a loop prompting for expressions to be entered one
    * at a time.
    */
   public void interactive()
   {
      JFileChooser fChooser = new JFileChooser( "." );
      fChooser.setFileFilter( new TextFilter() );
      int choice = fChooser.showDialog( null, "Pick a file of expressions" );
      if ( choice == JFileChooser.APPROVE_OPTION )
      {
         File file = fChooser.getSelectedFile();
         if ( file != null )
            processFile( file.getName() );
      }
      
      String prompt = "Enter an arithmetic expression: ";
      String expr = JOptionPane.showInputDialog( null, prompt );
      while ( expr != null && expr.length() != 0 )
      {
         String result = processLine( expr );
         JOptionPane.showMessageDialog( null, expr + "\n" + result );
         expr = JOptionPane.showInputDialog( null, prompt );
      }
   }

   //+++++++++++++++++++++++++ inner class +++++++++++++++++++++++++++++++
   //---------------------------- TextFilter -----------------------------
   /**
    * This class is used with FileChooser to limit the choice of files
    * to those that end in *.txt
    */
   public class TextFilter extends javax.swing.filechooser.FileFilter
   {
      public boolean accept( File f ) 
      {
         if ( f.isDirectory() || f.getName().matches( ".*txt" ) )
            return true;
         return false;
      }
      public String getDescription()
      {
         return "*.txt files";
      }
   }
   //--------------------- main -----------------------------------------
   public static void main( String[] args )
   {
      Interpreter app = new Interpreter( args );
   }
}