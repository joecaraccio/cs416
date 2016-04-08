package p6;

/**
 * Created by Joe on 3/26/2016.
 * 
 * @author Joe Caraccio
 */
public class Number extends Operand
{

    private Float token;

    /** Number.
     * 
     * @param s float.
     * */
    public Number( float s )
    {
        //System.out.println("Number");
        token = s;
    }

    /** getToken().
     *
     *
     * @return token float.
     */
    public float getToken()
    {
        return token;
    }

    
    /** printable().
     * 
     * @return returnstring String.
     * */
    public String printable() 
    {
        int tokenint =  Math.round( token );
        String returnstring = "@" +  tokenint;

        return returnstring;
    }
    public String getop(){
        String floatString = String.valueOf( token );
        return floatString;
    }
}
