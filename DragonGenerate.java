//Merwan M
//Computer Science II
//This program provides the series of turns required to generate a "dragon" and draws it
//DragonGenerate.java
//1/8/2020

import java.util.*;
import java.io.*;
import java.awt.*;

public class DragonGenerate
{
   /**
   *The lowest level that can be requested
   */
   public static final int LEVEL_MIN = 1;
   
   /**
   *The highest level that can be requested
   */
   public static final int LEVEL_MAX = 25;
   
   /**
   *The lowest number of pixels that can be requested for the size of drawing panel
   */
   public static final int DRAWING_MIN = 100;
   
   /**
   *The highest number of pixels that can be requested for the size of drawing panel
   */
   public static final int DRAWING_MAX = 2000;
   
   /**
   *The base name of the output file
   */
   public static final String FILENAME = "dragon";
   
   /**
   *The extension name of the output file
   */
   public static final String EXTENSION = ".txt";
   
   /**
   *Draws the dragon and writes the turns to a file
   */
   public static void main(String[] args)
   {
      intro();
      
      Scanner input = new Scanner(System.in);
      int desiredLevel = MyUtils.getNumber(input, "Enter the level of the fractal you'd like to see (1-25): ", LEVEL_MIN, LEVEL_MAX);
      int drawingSize = MyUtils.getNumber(input, "Enter the size of your drawing panel, in pixels (100-2000): ", DRAWING_MIN, DRAWING_MAX);
      String file = FILENAME + desiredLevel + EXTENSION;
      System.out.println("Path generated, writing to file " + file + "...");
      System.out.println("Drawing curve...");
      
      PrintStream out = MyUtils.createOutputFile(file);
      out.println(levels(desiredLevel));
      
      DragonDraw draw = new DragonDraw(drawingSize);
      draw.drawCurve(file, desiredLevel);
   }
   
   /**
   *Prints the intro
   */
   public static void intro()
   {
      System.out.println("This program will generate a fractal called the Dragon Curve");
      System.out.println("first explored by John Heighway, Bruce Banks, and William Harter");
      System.out.println("at NASA in the 1960's");
      System.out.println();
   }
   
   /**
   *Generates the series of turns for any level
   *
   *@param level the level for which to generate the turns
   *@return the string that represents the turns.
   */
   public static String levels(int level)
   {
      if(level <= 1)
      {
         return "R";
      }
      
      else
      {
         String previousLevel = levels(level-1);
         return previousLevel + "R" + complement(previousLevel);
      }
   }
   
   /**
   *Generates the complement of a series of turns
   *
   *@param initial the initial string to take the complement of
   *@return the complement of the original string
   */
   public static String complement(String initial)
   {
      String reversed = reverse(initial);
      String complement = "";
      for(int i = 0; i < reversed.length(); i++)
      {
         if(reversed.charAt(i) == 'L')
         {
            complement += "R";
         }
         
         else
         {
            complement += "L";
         }
      }
      return complement;
   }
   
   /**
   *Reverses any given string
   *
   *@param initial the string to reverse
   *@return the reversed string
   */
   public static String reverse(String initial)
   {
      String reversedWord = "";
      for(int i = 0; i < initial.length(); i++)
      {
         //i starts at 0, and because
         //if we are trying to get the index of a word and the index equals length of word
         //An out of bounds exception is thrown 
         //so we need to subtract one more to get the last char.
         reversedWord += initial.charAt(initial.length()-i-1); 
      }  
      return reversedWord;
   }
}