package p5;

/**
 * Created by Joe on 3/26/2016.
 * 
 * @author Joe Caraccio
 */
public class Variable extends Operand 
{

    private String s1;
    /** Variable.
     * 
     * @param s String.
     * */
    public Variable( String s )
    {
        s1 = s;
    }

    /** printable.
     * 
     * @return returnstring String.
     * */
    public String printable()
    {
        String returnstring = "@" +  s1;
        return returnstring;
    }
}
