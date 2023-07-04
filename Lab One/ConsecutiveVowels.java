import java.util.Scanner;
/*
 * This program determines whether a word contains more than 1 vowel in a row
 * If it does, it prints the word 
 * For example, 
 * if the word = "field" then "field" is printed ("ie")
 *             = "queue" then "queue" is printed ("ueue")
 */
public class ConsecutiveVowels 
{
   public static void main(String[] args) 
   {
  	  String  words = "field quick abdicate mood about cattle"; 
      Scanner in = new Scanner(words);
     
      while (in.hasNext())
  	  {
  	  	String word = in.next();
        
        //-----------Start below here. To do: approximate lines of code = 9
        // Use a for loop and go through each character of the strong word 
        // check if the character is one of 'a' 'e' 'i' 'o' 'u' 
        // if it is, record (in a variable) the index where this occurred
  	  	// if another vowel is encountered, check to see if the index is one more
  	  	// if so, print the word. If not, update the index 
        
        
        
          	//Check each letter of word to see if a vowel, 
            // when comparing characters don't forget to use ==  
        	  // Hint: use class String method charAt() to get the next character
        	  // e.g. word.charAt(3) returns a char at position 3
        
        
        
        
        
        
        
        
        
        
        
        
        //-----------------End here. Please do not remove this comment. Reminder: no changes outside the todo regions.
  	  }
      System.out.println("Expected:\nfield\nquick\nmood\nabout");
   }
}