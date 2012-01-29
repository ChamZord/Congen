/**
 * WriteUp.java:
 *   Save the proven theorem in a reader-friendly format, indicating which
 *   variable is used for induction.
 * 
 * @author Dan Shea
 * Ground Up Software, Inc.
 */

import java.util.*;
import java.io.*;

public class WriteUp
{
   //--------------- instance variables ---------------------------------------
   String bfs = "";
   
   //------------------ constructor -------------------------------------------
   /**
    *  Write the theorem to a new file
    */
   public WriteUp( String s )
   {
      try {
         bfs = "Let\n\n";
         File inFile = new File( "./proven_theorems/" + s + ".thy" );
         Scanner scan = new Scanner( inFile );
         String temp;
         String fluff;
         int pipe, first, second, third, fourth;
         
         // scan the file and parse the appropriate lines accordingly
         while ( scan.hasNextLine() )
         {
            temp = scan.nextLine();
            if ( temp.startsWith( "datatype T =" ) )
            {
               pipe = temp.indexOf( "|" );
               first = temp.indexOf( "\"" );
               second = temp.indexOf( "\"", first + 1 );
               third = temp.indexOf( "\"", second + 1 );
               fourth = temp.indexOf( "\"", third + 1 );
               fluff = temp.substring( first + 1, second );
               if ( pipe > third )
               {
                  bfs += "T = C_a (" + fluff + ",";
                  fluff = temp.substring( third + 1, fourth );
                  bfs += fluff + ") | C_b (";
               }
               else
                  bfs += "T = C_a (" + fluff + ") | C_b (";
               first = temp.indexOf( "\"", pipe + 1 );
               second = temp.indexOf( "\"", first + 1 );
               third = temp.indexOf( "\"", second + 1 );
               fluff = temp.substring( first + 1, second );
               if ( third == -1 )
                  bfs += fluff + ")\n\n";
               else
               {
                  bfs += fluff + ",";
                  fourth = temp.indexOf( "\"", third + 1 );
                  fluff = temp.substring( third + 1, fourth );
                  bfs += fluff + ")\n\n";
               }
            }
            else if ( temp.startsWith( "datatype T_1 =" ) )
            {
               pipe = temp.indexOf( "|" );
               first = temp.indexOf( "\"" );
               second = temp.indexOf( "\"", first + 1 );
               third = temp.indexOf( "\"", second + 1 );
               fourth = temp.indexOf( "\"", third + 1 );
               fluff = temp.substring( first + 1, second );
               if ( pipe > third )
               {
                  bfs += "T_1 = C_a (" + fluff + ",";
                  fluff = temp.substring( third + 1, fourth );
                  bfs += fluff + ") | C_b (";
               }
               else
                  bfs += "T_1 = C_a (" + fluff + ") | C_b (";
               first = temp.indexOf( "\"", pipe + 1 );
               second = temp.indexOf( "\"", first + 1 );
               third = temp.indexOf( "\"", second + 1 );
               fluff = temp.substring( first + 1, second );
               if ( third == -1 )
                  bfs += fluff + ")\n";
               else
               {
                  bfs += fluff + ",";
                  fourth = temp.indexOf( "\"", third + 1 );
                  fluff = temp.substring( third + 1, fourth );
                  bfs += fluff + ")\n";
               }
            }
            else if ( temp.startsWith( "datatype T_2 =" ) )
            {
               pipe = temp.indexOf( "|" );
               first = temp.indexOf( "\"" );
               second = temp.indexOf( "\"", first + 1 );
               third = temp.indexOf( "\"", second + 1 );
               fourth = temp.indexOf( "\"", third + 1 );
               fluff = temp.substring( first + 1, second );
               if ( pipe > third )
               {
                  bfs += "T_2 = C_c (" + fluff + ",";
                  fluff = temp.substring( third + 1, fourth );
                  bfs += fluff + ") | C_d (";
               }
               else
                  bfs += "T_2 = C_c (" + fluff + ") | C_d (";
               first = temp.indexOf( "\"", pipe + 1 );
               second = temp.indexOf( "\"", first + 1 );
               third = temp.indexOf( "\"", second + 1 );
               fluff = temp.substring( first + 1, second );
               if ( third == -1 )
                  bfs += fluff + ")\n\n";
               else
               {
                  bfs += fluff + ",";
                  fourth = temp.indexOf( "\"", third + 1 );
                  fluff = temp.substring( third + 1, fourth );
                  bfs += fluff + ")\n\n";
               }
            }
            else if ( temp.startsWith( "fun f ::" ) )
            {
               bfs += "f : ";
               first = temp.indexOf( "\"" );
               second = temp.indexOf( "=", first + 1 ) - 1;
               third = temp.indexOf( "=", second + 2 ) - 1;
               fluff = temp.substring( first + 1, second );
               bfs += fluff + " X ";
               fluff = temp.substring( second + 4, third );
               bfs += fluff + " -> ";
               fluff = temp.substring( third + 4, temp.lastIndexOf( "\"" ) );
               bfs += fluff + "\n";
            }
            else if ( temp.startsWith( "  \"f" ) )
            {
               fluff = "f(" + temp.substring( temp.indexOf( "f" ) + 2,
                                             temp.indexOf( "=" ) - 1 ) + ")";
               if ( fluff.endsWith( ")" ) )
                  fluff = fluff.substring( 0, fluff.length() - 1 );
               fluff = fluff.replaceAll( "x ", "x," );
               fluff = fluff.replaceAll( "y ", "y," );
               fluff = fluff.replaceAll( "z ", "z," );
               fluff = fluff.replaceAll( "\\) ", ")," );
               fluff = fluff.replaceAll( "\\(C", "C" );
               fluff = fluff.replaceAll( "a ", "a(" );
               fluff = fluff.replaceAll( "b ", "b(" );
               fluff = fluff.replaceAll( "c ", "c(" );
               fluff = fluff.replaceAll( "d ", "d(" );
               bfs += fluff + ") = ";
               fluff = temp.substring( temp.indexOf( "=" ) + 2,
                                      temp.lastIndexOf( "\"" ) );
               if ( fluff.startsWith( "(" ) )
                  fluff = fluff.substring( 1, fluff.length() );
               if ( fluff.endsWith( ")" ) )
                  fluff = fluff.substring( 0, fluff.length() - 1 );
               fluff = fluff.replaceAll( "x ", "x," );
               fluff = fluff.replaceAll( "y ", "y," );
               fluff = fluff.replaceAll( "z ", "z," );
               fluff = fluff.replaceAll( "\\) ", ")," );
               fluff = fluff.replaceAll( "\\(C", "C" );
               fluff = fluff.replaceAll( "a ", "a(" );
               fluff = fluff.replaceAll( "b ", "b(" );
               fluff = fluff.replaceAll( "c ", "c(" );
               fluff = fluff.replaceAll( "d ", "d(" );
               bfs += fluff + ")\n";
            }
            else if ( temp.startsWith( "| \"f" ) )
            {
               fluff = "f(" + temp.substring( temp.indexOf( "f" ) + 2,
                                             temp.indexOf( "=" ) - 1 ) + ")";
               if ( fluff.endsWith( ")" ) )
                  fluff = fluff.substring( 0, fluff.length() - 1 );
               fluff = fluff.replaceAll( "x ", "x," );
               fluff = fluff.replaceAll( "y ", "y," );
               fluff = fluff.replaceAll( "z ", "z," );
               fluff = fluff.replaceAll( "\\) ", ")," );
               fluff = fluff.replaceAll( "\\(C", "C" );
               fluff = fluff.replaceAll( "a ", "a(" );
               fluff = fluff.replaceAll( "b ", "b(" );
               fluff = fluff.replaceAll( "c ", "c(" );
               fluff = fluff.replaceAll( "d ", "d(" );
               bfs += fluff + ") = ";
               fluff = temp.substring( temp.indexOf( "=" ) + 2,
                                      temp.lastIndexOf( "\"" ) );
               if ( fluff.startsWith( "(" ) )
                  fluff = fluff.substring( 1, fluff.length() );
               if ( fluff.endsWith( ")" ) )
                  fluff = fluff.substring( 0, fluff.length() - 1 );
               fluff = fluff.replaceAll( "x ", "x," );
               fluff = fluff.replaceAll( "y ", "y," );
               fluff = fluff.replaceAll( "z ", "z," );
               fluff = fluff.replaceAll( "f ", "f(" );
               fluff = fluff.replaceAll( "\\) ", ")," );
               fluff = fluff.replaceAll( "\\(C", "C" );
               fluff = fluff.replaceAll( "a ", "a(" );
               fluff = fluff.replaceAll( "b ", "b(" );
               fluff = fluff.replaceAll( "c ", "c(" );
               fluff = fluff.replaceAll( "d ", "d(" );
               bfs += fluff + ")\n\nThen\n\n";
            }
            else if ( temp.startsWith( "lemma" ) )
            {
               boolean trimEnd = false;
               bfs += "f(";
               if ( temp.substring( temp.indexOf( "f" ) - 1 ).
                      startsWith( ")" ) )
                  trimEnd = true;
               fluff = temp.substring( temp.indexOf( "f" ) + 2,
                                      temp.indexOf( "=" ) - 1 );
               if ( trimEnd )
                  fluff = fluff.substring( 0, fluff.length() - 1 );
               fluff = fluff.replaceAll( "x ", "x," );
               fluff = fluff.replaceAll( "y ", "y," );
               fluff = fluff.replaceAll( "z ", "z," );
               fluff = fluff.replaceAll( "\\(f", "f" );
               fluff = fluff.replaceAll( "f ", "f(" );
               fluff = fluff.replaceAll( "\\) ", ")," );
               bfs += fluff + ") = f(";
               fluff = temp.substring( temp.indexOf( "=" ) + 5,
                                      temp.lastIndexOf( "\"" ) );
               trimEnd = false;
               if ( temp.charAt( temp.indexOf( "=" ) + 2 ) == '(' )
                  trimEnd = true;
               if ( trimEnd )
                  fluff = fluff.substring( 0, fluff.length() - 1 );
               fluff = fluff.replaceAll( "x ", "x," );
               fluff = fluff.replaceAll( "y ", "y," );
               fluff = fluff.replaceAll( "z ", "z," );
               fluff = fluff.replaceAll( "\\(f", "f" );
               fluff = fluff.replaceAll( "f ", "f(" );
               fluff = fluff.replaceAll( "\\) ", ")," );
               bfs += fluff + ")\n\n";
            }
            else if ( temp.startsWith( "by" ) )
            {
               fluff = "" + temp.charAt( 11 );
               bfs += "Proof by induction on " + fluff;
            }
         }
         
         System.out.println( bfs );
         FileWriter outFile = new FileWriter( "./writeups/" + s );
         PrintWriter out = new PrintWriter( outFile );
         out.println( bfs );
         out.close();
      } catch ( Exception e ) {
         e.printStackTrace();
      }
   }
   
   //--------------------- main -----------------------------------------------
   /**
    * the startup function
    */
   public static void main( String[] args )
   {
      new WriteUp( args[ 0 ] );
   }
}