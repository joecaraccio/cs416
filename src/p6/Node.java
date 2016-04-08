package p6;

/**
 * Created by Skipper on 4/4/2016.
 */
public class Node {

    EToken value = null; //what is contained in this node
    Node left;
    Node right;
    String printval = "";

    public Node(){

    }

    /** Node.
     *  Secondary constructor.
     *
     * @param v EToken.
     */
    public Node( EToken v ){
        value = v;
    }

    public static String evaluate( Node n , SymbolTable s ){



        if ( n.value instanceof Variable ) {
            //System.out.println( n.value.op() + " This is a Varible");
           float f1 = s.getValue( n.value.getop() );
            String s22 = String.valueOf( f1 );
           // System.out.println( f1 );
            //get varibles value from symbol table
            return s22;

        } else if( n.value instanceof  Number ){
           // System.out.println(n.value.op() +" This is a Number");
            return n.value.op();
        } else {
            if( n.value.op().equals("=") ){

                String s1 = evaluate( n.right, s );
                Float f1 = Float.valueOf( s1 );
                s.setValue( n.left.value.op() , f1 );


                return s1;
            } else {

                Float v1 = 0f;
                Float v2 = 0f;
                // System.out.println(" ");
                //System.out.println(n.value.op() +" This is a Operator");
                if (n.left != null) {
                    // System.out.println("Right");
                    String s1 = evaluate(n.left, s);
                    v1 = Float.valueOf(s1);
                    //System.out.println( "Ben Mills " + s1 );

                }
                if (n.right != null) {
                    // System.out.println("Left");
                    String s1 = evaluate(n.right, s);
                    v2 = Float.valueOf(s1);
                    // System.out.println( "Ben Mills " + s1 );

                }
                String op = n.value.op();
                Float finalvalue = 0f;
                if (op.equals("+")) {
                    finalvalue = v1 + v2;
                    // System.out.println( "final value " + finalvalue );
                } else if (op.equals("*")) {
                    finalvalue = v1 * v2;
                } else if (op.equals("-")) {
                    finalvalue = v1 - v2;
                } else if (op.equals("/")) {
                    finalvalue = v1 / v2;
                } else if (op.equals("%")) {
                    finalvalue = v1 % v2;
                }
                String returnme = String.valueOf(finalvalue);
                return returnme;
            }
        }


    }

    public void printTree( Node n, int depth ) {
        String space = "       ";
        String message = "";
        for( int i = 0; i < depth; i++ ){
            message = message + space;
        }

      if( n.left != null ){
          printTree( n.left , depth + 1);
      }
        //System.out.println( message + n.value.op() );
        printval = printval + "\n" + message + n.value.op();
       // return  message + n.value.op();
        if( n.right != null ){
            printTree( n.right , depth + 1 );
        }

    }

}
