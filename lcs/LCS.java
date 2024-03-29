/* Name: Nicholas Walsh
 * Dr. Andrew Steinberg
 * COP3503 Fall 2021
 * Programming Assignment 3
 */

package lcs;

import java.lang.String;

public class LCS
{
  private String lcs = "";

  private String message1 = "";
  private String message2 = "";

  private int m,n,i,j;

  private int mapA[][]; //Map of all possible LCS's
  private String mapB[][]; //Map of correct route to find LCS

  public LCS()
  {

  }

  public LCS(String message1, String message2)//Loaded lcs constructor
  {
    this.message1 += "-";//Inserting empty subsequence operator at the beginning of the message
    this.message2 += "-";

    this.message1 += message1;
    this.message2 += message2;

    this.m = message1.length() + 1;
    this.n = message2.length() + 1;

    mapA = new int[m][n];
    mapB = new String[m][n];
  }

  //Loops through mapB and stores the correct subsequence character according to the route placed in mapB
  public String getLCS()//Print LCS algorithm we discussed in class
  {
    while(i != 0 && j != 0)
    {
      if((mapB[i][j]).equals("diag"))
      {
        lcs += message1.charAt(i);
        i = i-1;
        j = j-1;
      }
      else if((mapB[i][j]).equals("up"))
      {
        i = i-1;
      }
      else
      {
        j = j-1;
      }
    }

    lcs = reverse(lcs); //Reversing order of the string to match output detailed in the runner file
    return lcs;
  }

  /*
  Uses mapA array to test all possible subsequences between message1 and message2.
  Uses mapB array to hold the route we must take through the two strings to generate
  the greatest common subsequence.
  */
  public void lcsDynamicSol()
  {
    for(i = 0; i < m; i++)//Pre filling array
    {
      mapA[i][0] = 0;
    }

    for(j = 1; j < n; j++)//Pre filling array
    {
      mapA[0][j] = 0;
    }

    for(i = 1; i < m; i++)
    {
      for(j = 1; j < n; j++)
      {
        if(this.message1.charAt(i) == this.message2.charAt(j))
        {
          mapA[i][j] = mapA[i-1][j-1] + 1;
          mapB[i][j] = "diag";
        }
        else if(mapA[i-1][j] >= mapA[i][j-1])
        {
          mapA[i][j] = mapA[i-1][j];
          mapB[i][j] = "up";
        }
        else
        {
          mapA[i][j] = mapA[i][j-1];
          mapB[i][j] = "left";
        }
      }
    }
    i--;//i and j values are decremented because the size of i and j must align with the size of the 2d array
    j--;
  }

  //Method to reverse the order of a string using the StringBuilder class
  public static String reverse(String str)
  {
    StringBuilder output = new StringBuilder(str);
    output.reverse();
    return output.toString();
  }

}
