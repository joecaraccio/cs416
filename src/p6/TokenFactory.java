
package p6;

import java.lang.reflect.Array;
import java.util.*;

/**
 * TokenFactory: a factory class, which makes tokens from String fields.
 * This is an example of a Factory pattern.
 *
 * @author Joe Caraccio
 */
public class TokenFactory
{

    /** makeToken.
     *
     * @param s String.
     * @return e1 Etoken.
     * @throws Exception e.
     * */
    public static EToken makeToken(String s ) throws Exception
    {
        //test what kind of thing this is
        String operators = "+-/*%()=";
        //EToken e = new Number();

        String token = s;
        float value;
        try
        {
            value = Float.parseFloat( token );
            EToken e1 = new Number( value );
            return e1;
        }
        catch ( NumberFormatException e )
        {
            // it's not a number
            //maybe its an operand
            if( operators.contains( s ) && s.length() == 1 )
            {
                EToken e2 = new Operator( s );
                return e2;
            } else
            {
                Boolean isIdentifer = Character.isJavaIdentifierStart(
                        s.charAt( 0 ) );
                int stringAmount = s.length();
                boolean valid = true;
                //Character.isJavaIdentifierPart(char)
                for( int i = 1; i < stringAmount; i++ )
                {
                    //System.out.println( s.charAt( i ) );
                    boolean temp = Character.isJavaIdentifierPart(
                            s.charAt( i ) );
                    if( temp == false )
                    {
                        valid = false;
                    }
                }

                if( isIdentifer && valid )
                {
                    EToken e4 = new Variable( s );
                    return e4;
                } else
                {
                    //System.out.println("lets throw something for s");
                    boolean falseVarible = false;
                    int stringAmount2 = s.length();
                    boolean containsOp = false;
                    for( int i = 0; i < stringAmount; i++ )
                    {
                        String word = s.substring( 0 + i, 1 + i );
                        boolean op = operators.contains( word  );
                        //System.out.println( word + " " + op );
                        if( op )
                        {
                            falseVarible = true;
                        }

                    }

                    boolean numfirst = false;
                    boolean num1 = false;
                    boolean num2 = false;

                            if( isNumeric( s.substring( 0 , 1 ) ) ){
                                num1 = true;
                            }

                    //purposely left e out
                    String letters = "abcdfghijklmnopqrstuvwzyz";
                    char[] c1 = letters.toCharArray();
                    for( int i = 0; i < c1.length; i++ ){
                        if( s.contains( String.valueOf( c1[i]) ) ){
                            num2 = true;
                        }
                    }
                    if( num1 == true && num2 == true ){
                        numfirst = true;
                    }


                    boolean lettered = false;
                    String token2 = s;
                    float value2;
                    try
                    {
                        value2 = Float.parseFloat( token2 );
                        for( int i = 0; i < stringAmount; i++ )
                        {
                            String word = s.substring(0 + i, 1 + i);
                            if( letters.contains( word ) )
                            {
                                lettered = true;
                            }

                        }

                    }
                    catch ( NumberFormatException e2 )
                    {

                    }
                    if( falseVarible )
                    {
                        //System.out.println("HEY S");
                        throw new Exception( "Token Value " + " ' " + s
                                + " ' " + " is not a valid token." +
                                " Looks like it contains an operator" +
                                " and additional characters, operators must be seperate. Check Tokens Spacing" );
                    } else if( lettered )
                    {
                        throw new Exception( "Token Value " + " ' " + s
                                + " ' " + " is not a valid token." +
                                " Looks like it contains letters and numbers" +
                                " Identifers cannot start with a number"
                                + "and only e can be in numbers"
                        );
                    } else if( numfirst ){
                        throw new Exception( "Token Value " + " ' " + s
                                + " ' " + " is not a valid token." +
                                " Looks like it starts with a Number but then has letters, Tokens must follow Java Identifer Rules");

                    }
                    else
                    {
                        throw new Exception( "Token Value " + " ' " + s
                                + " ' " + " is not a valid token" );
                    }
                }

            }


        }




    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
