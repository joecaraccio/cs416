package p4;

/**
 * BoggleBoard.java - A skeleton class for implementing the board for
 *                    the game of Boggle
 *
 * @author jb
 * Summer 2010
 *
 * 02/19/11 rdb: minor formatting and style edits
 */

import java.io.*;
import java.util.*;

public class BoggleBoard
{
   //--------------------- instance variables -------------------------
   Tile[][]          board;
   int               nCols;
   int               nRows;
   ArrayList<String> letters;

   //------------------ constructor -----------------------------------
   /**
    * Arguments:
    *        lettersOnBoard: rows x cols letters to be shown on the board
    *        rows: number rows of letters
    *        cols: number columns of letters
    */
   BoggleBoard( ArrayList<String> lettersOnBoard, int rows, int cols )
   {
      letters = lettersOnBoard;
      nCols = cols;
      nRows = rows;

      ////////////////////////////////////////////////////////////////
      // 1. Need to create the board and "populate" it with the letters
      //    passed in the ArrayList. Assign entries from letters by row!
      //    That is, do all of row 0, then row 1, etc.
      // 2. For each Tile in the board, need to create a list of its
      //    valid neighbors (in all 8 directions). Remember that tiles
      //    on the boundaries don't have 8 neighbors.
      /////////////////////////////////////////////////////////////////

   }
   //---------------------- getWordCount() -----------------------------
   /**
    * return the number of words found in the last solution.
    * if findWords has not yet been called, returns -1;
    */
   public int getWordCount()
   {
      /////////////////////////////////////////////////////////////////
      // return the number of words found in last call to findWords()
      //    or -1 if no call yet made
      /////////////////////////////////////////////////////////////////
      return -1;
   }

   //------------------------- findWords() -----------------------------
   /**
    * Find all the valid words in this board (based on current parameter
    *    settings).
    * As words are found, add them to a Java TreeSet object (which sorts
    *    them alphabetically for you) -- see Java API documentation.
    * Return the words in a single String, separated by commas with
    *    10 words per line (except last line).
    * Most of the work is done by the private recursive method,
    *    findWords( String, Tile )
    */
   public String findWords()
   {
      //////////////////////////////////////////////////////////////////
      // For each tile in the board
      //    findWords( TreeSet, "", tile ) to find all words that start there
      // Convert the TreeSet into a single String with 10 words per line,
      //    separated by commas.
      // return this string.
      //////////////////////////////////////////////////////////////////

      return "";
   }

   //---------------- findWords( TreeSet<String>, String, Tile ) -----------
   /**
    * Given a partial word ending at a neighbor of the tile passed in,
    *    add the tile's letter to the partial word, and check if it is a word
    *    and if it terminates the search along this path; recurse if not.
    */
   private void findWords( TreeSet<String> foundWords, String word, Tile t )
   {
      ///////////////////////////////////////////////////////////////////
      //  if tile has not been visited (on this path)
      //     set tile's status as visited
      //     add tile's letter to word
      //     lookup word using search method of Boggle.dictionary
      //     if it is a word
      //        add it to the TreeSet
      //     if it is a word or it might begin another word
      //        get neighbor tiles of this tile
      //        for each neighbor of this tile
      //           invoke findWords(...) recursively
      //     reset the tiles visited flag to false
      ////////////////////////////////////////////////////////////////////

   }
   //-------------------- toString() ---------------------------------------
   /**
    * convert the board to a String representation.
    */
   public String toString()
   {
      StringBuffer out = new StringBuffer();
      for ( int r = 0; r < nRows; r++ )
      {
         for ( int c = 0; c < nCols; c++ )
         {
            out.append( board[ r ][ c ].getLetter() + "\t" );
            if ( board[ r ][ c ] .getLetter().length() == 1 )
               out.append( " " );        // add another blank for most letters
         }
         out.append( "\n" );
      }
      return out.toString();
   }
   //+++++++++++++++++++++++ main: invoke application ++++++++++++++++++++++
   public static void main( String [] args )
   {
      Boggle.main( args );
   }
}
