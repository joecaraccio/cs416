package p6;


/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand
 *
 * @author Joe Caraccio
 */

public abstract class EToken
{
    /** .
     * Non-standard toString.
     *
     * @return String name.
     */
    abstract public String printable();

    /** toString.
     * .
     * @return String name.
     */
    public String toString()
    {
        return printable();
    }

    abstract public String getop();

    public String op()
    {
        return getop();
    }

    //------------------ main unit test ------------------------
    /** .
     * some basic unit tests.
     *
     * @param args String[]
     */
    public static void main( String[] args )
    {
        try
        {

            EToken plus  = TokenFactory.makeToken( "+" );
            EToken times = TokenFactory.makeToken( "*" );
            EToken a     = TokenFactory.makeToken( "a" );
            EToken one   = TokenFactory.makeToken( "1" );
            EToken t1 = TokenFactory.makeToken( "abc" );
            EToken t2 = TokenFactory.makeToken( "2.2" );
            EToken t3 = TokenFactory.makeToken( "12.32e45" );
            EToken t4 = TokenFactory.makeToken( "sales_tax" );
            EToken t5 = TokenFactory.makeToken( "_tax" );

            //test from rubric
            EToken q1 = TokenFactory.makeToken( "2" );
            EToken q2 = TokenFactory.makeToken( "+" );
            EToken q3 = TokenFactory.makeToken( "a" );
            EToken q4 = TokenFactory.makeToken( "*" );
            EToken q5 = TokenFactory.makeToken( "(" );
            EToken q6 = TokenFactory.makeToken( "3" );
            EToken q7 = TokenFactory.makeToken( "/" );
            EToken q8 = TokenFactory.makeToken( "4" );
            EToken q9 = TokenFactory.makeToken( ")" );

            EToken q10 = TokenFactory.makeToken( "$a" );
            //EToken q11 = TokenFactory.makeToken("*D");


            System.out.println( a + " " + plus + " " + one + " "
                               + times + " " + a );
            //Second Round of testing
            System.out.println( t1 + " " + t2 + " " + t3
                    + " " + t4 + " " + t5 );

            System.out.println( q1 + "" + q2 + "" + q3 + "" + q4
                    + "" + q5  + "" + q6 + "" + q7 + "" + q8 + "" + q9 + "" );

            System.out.println("Hey Joe");

        }
        catch ( Exception e )
        {
            System.out.println( "Bad token: " + e );
        }
    }
}
