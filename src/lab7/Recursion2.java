package lab7;
//+++++++++++++++++++++++++++++ Recursion2.java +++++++++++++++++++++++++++
import java.util.*;
/**
 * Recursion2.java: Lab 7.
 * @author Joe Caraccio
 * 
 * Last edit: 02/09/15: made stylecheck-compatible.
 */

public class Recursion2
{
    //------------------------- sum( int[], int ) --------------------
    /**
     * Return the sum of the first n elements of the array.
     * 
     * Hint: The sum of the first n elements of the array is the 
     *       sum of the last element of the array plus the sum of the
     *       first n - 1 elements of the array.
     * 
     * Recursive solution means: NO LOOPS, NO INSTANCE VARIABLES.
     * @param vals int[]  The input array 
     * @param n int       The portion of the array to be summed
     * @return int
     */
    public static int sum( int[] vals, int n )
    {
        //--------- start sum -----------------
        if( n == vals.length )
        {
            n = n - 1;
        }
        if( n - 1 == -1 )
        {
            return vals[n];
        }
        else {
            return vals[n] + sum( vals , n - 1 );
        }

    } //--------- end sum -----------------
    
    //-------------------------- pow( float, int ) -------------------
    /**
     * Return a float raised to the power exp.
     * 
     * Recursive solution means: NO LOOPS, NO INSTANCE VARIABLES.
     * @param num float
     * @param exp int
     * @return float
     */
    public static float pow( float num, int exp )
    {
        //--------- start pow -----------------
        //****** COMMENT OUT THIS PRINT STATEMENT *****************


        if( exp == 0 )
        {
            return 1;
        }else if( exp == 1 )
        {
            return num;
        }
        else {

            return pow( num , exp -1  ) * num;
        }
    } //--------- end pow -----------------
    
    //---------------------- ordered( int[], int ) --------------------
    /**
     * Check if the first n elements of the integer array are ordered
     *   from small to large.
     * 
     * Hint: The first n are ordered IF
     *                  the n-th is larger than or equal to the (n-1)st.
     *              AND the first n-1 are ordered.
     * 
     * Hint: There are 2 base cases: 
     *            a "success" case - we've tested all the elements
     *            a "failure" case when we've found a failure of order
     * 
     * Recursive solution means: NO LOOPS, NO INSTANCE VARIABLES.
     * @param vals int[]
     * @param n int
     * @return boolean
     */
    public static boolean ordered( int[] vals, int n )
    {
        if( n == vals.length )
        {
            n = n -1;
        }
        //array is arranged smallest to largest
        //works backwards on the array, checks if n is greater than n-1
        if( n - 1 == -1 ) //reached end with success
        {
            //success case
            return true;
        } else if (  vals[n - 1] > vals[ n  ])
        {
            return false;
        } else {
            return ordered( vals, n - 1 );
        }
        
    } //--------- end ordered -----------------
    
    //---------------------- charPairTest( String ) ----------------------
    /**
     * Check if a String is composed of sequential pairs of identical 
     * letters.
     *     Examples:   aa,  aaBBccAAaa
     * 
     * A 1 character string should fail the charPairTest; 
     * a 0-length string should pass it
     * 
     * Hint: A string passes charPairTest if the first 2 characters match 
     *            AND the rest of the string passes the charPairTest
     * 
     * Hint: There are 3 base cases:
     *        1. "success" case: we've tested all the elements
     *        2. "failure" case: we've found a failure of a pair match
     *        3. "failure" case: there is an "extra" character that
     *              cannot be paired with another.
     * 
     * Recursive solution means: NO LOOPS, NO INSTANCE VARIABLES.
     * @param s String
     * @return boolean
     */
    public static boolean charPairTest( String s )
    {


        //--------- start charPairTest -----------------
        //System.out.println(" ");
        //System.out.println("Does " + s.substring( 0 , 1 ) +" equal " + s.substring( 1, 2 ));
        //System.out.println( "Next substring would be " +  s.substring(2 ));
         if( s.length() <= 1 )
    {
        return false;
    }
        else if ( s.substring(0,1).equals( s.substring( 1,2)) && s.length() == 2 )
        { //if they equal and they are the last left in the string
            return true;
        }

        else if( !s.substring( 0 , 1).equals( s.substring(1,2 )) )
        {
            return false;
        }

        else{
        return charPairTest( s.substring(2) );
    }

    } //--------- end charPairTest -----------------
    
    //----------------- exchangePairs( String ) --------------------
    /** 
     * Exchange sequential pairs of letters in a string.
     *  eg. input:  abcdefg
     *     output:  badcfeg
     * @param s String
     * @return String
     */
    public static String exchangePairs( String s )
    {
        String temp = null;
        //--------- start exchangePairs -----------------
        if( s.length() < 2 )
        {
            temp = s;
            return temp;
        }
        else
        {
            temp = s.substring( 1 ,2 ) + s.substring( 0 , 1 );
            return temp + exchangePairs(s.substring( 2 ));
        }


    } //--------- end exchangePairs -----------------
}