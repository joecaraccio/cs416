package p4;

/**
 * Tile for BoggleBoard -- skeleton
 *
 * Knows its letter and its location on the board, but you
 *    may want to revise the the "letter" handling to
 *    account for the fact that "q" is really "qu".
 *
 * Needs to know about its visited status
 * and who its neighboring tiles are.
 *
 * @author jb
 * Summer 2010
 *
 * 02/19/11 rdb: Formatting and style edits
 */

import java.util.*;

public class Tile
{
    //---------------------- instance variables -------------------------
    private String          letter;
    private int             row, col;
    private Vector<Tile> neighbors;
    private String status;

    //------------------ constructor --------------------------------------
    public Tile( int r, int c, String let )
    {
        row = r;
        col = c;
        letter = let;
        status = "unvisited";
    }

    //------------------ getCol() --------------------------------------
    /**
     * returns the col location
     */
    public int getCol()
    {
        return col;
    }
    //------------------ getRow() --------------------------------------
    /**
     * returns the row location
     */
    public int getRow()
    {
        return row;
    }
    //------------------ getLetter( ) ----------------------------
    /**
     * Returns the letter this tile contains
     */
    public String getLetter()
    {
        return letter;
    }
    //--------------------------- toString() -------------------------
    public String toString()
    {
        return letter;
    }

    public void setNeighbors( Vector<Tile> n )
    {
        neighbors = n;
    }

    public void setStatus( String s )
    {
        status = s;
    }

    public String getStatus()
    {
        return status;
    }

    public Vector<Tile> getNeighbors()
    {
        return neighbors;
    }
}