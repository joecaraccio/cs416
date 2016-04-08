package p6;

/**
 * Created by Joe on 3/26/2016.
 * 
 * @author Joe Caraccio.
 */
public class Operator extends EToken
{
    
    private String operator;
    
    /** Operator.
      * 
      * @param s String.
      * */
    public Operator( String s )
    {
        //System.out.println( "Operator");
        operator = s;
        
    }

    /** getop.
     *
     * @return operator String.
     */
    public String getop(){
        return operator;
    }
    
    /** printable().
      * 
      * @return printformat String.
      * */
    public String printable()
    {
        //System.out.println("Printable");
        String printformat = "<" + operator + ">";
        
        return printformat;
    }
    
    
}
