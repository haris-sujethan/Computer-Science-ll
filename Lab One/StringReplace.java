/**
   Encodes a string by replacing all letters "i" with "!" and
   all letters "s" with "$". Use the replace method of class String. 
   Do this with variable town, assigning the result to variable townEncoding, 
   and with the variable sentence, assigning the result to variable sentenceEncoding.
 */

public class StringReplace
{
    public static void main(String[] args)
    {
        String town = "Mississauga" ;
        String townEncoding = town.replace("i","!");
        townEncoding = townEncoding.replace("s","$");
        String sentence = "This is a simple sentence." ;
        String sentenceEncoding =sentence.replace("s","$");
        sentenceEncoding = sentenceEncoding.replace("i","!");
	//-----------Start below here. To do: approximate lines of code = 4
	// 1. Assign to townEncoding the result of applying the replace method to town to replace "i" with "!" ; 
	
	//2. now replace s to $ in string townEncoding ; 
	
	//3. assign to sentenceEncoding the result of applying replace() on sentence to replace "i" with "!".  
	
	//4. now replace s with $ in string sentenceEncoding.
	
	//-----------------End here. Please do not remove this comment. Reminder: no changes outside the todo regions.
	System.out.println("Encoding of town:" + townEncoding) ;
	System.out.println("Encoding of sentence:" + sentenceEncoding) ;
        System.out.println("Expect:") ;
        System.out.println("Encoding of town: M!$$!$$auga" ) ;
	System.out.println("Encoding of sentence: Th!$ !$ a $!mple $entence.") ;
    }
}
