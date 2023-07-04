import java.util.Scanner;
/*
 * This program contains a static method to find the number
 * of target strings occur in a given input string. It returns this number.
 * Difficulty: Easy
 */
public class GradesStats
{
	public static void main (String[] args)
	{
		 String courseGrades = "B- C+ A+ A B B+ F D+ C- A- B A+";
		 
		 System.out.println("Number of A+ grades is " + countGrades(courseGrades, "A+"));
		 System.out.println("Expected:\nNumber of A+ grades is 2");
		 
		 int numAGrades = 0;
		 //-----------Start below here. To do: approximate lines of code = 1

		 char someChar = 'A';

		 for(int i =0; i<courseGrades.length(); i++){
			 if(courseGrades.charAt(i) == someChar){
				numAGrades++;
			 }
		 }

		 int countGrades = numAGrades;
		 // Use the countGrades() method and compute the total number of A grades where an A grade is one of A- A A+
		 // store the number of A grades in the variable numAGrades


		 
		 //-----------------End here. Please do not remove this comment. Reminder: no changes outside the todo regions.
		 System.out.println("Number of A- A A+ grades is " + numAGrades);
		 System.out.println("Expected:\nNumber of A- A A+ grades is 4");
	}
	
	static int countGrades(String grades, String targetGrade)
	{
		//-----------Start below here. To do: approximate lines of code = 8
		// use a Scanner and loop through the grades string looking for a word equal to targetGrade
		// if one is found, add it to a total and continue until there are no more words. Return the total.
		// For example, if grades = "C+ B- B D C+ A" and targetGrade = "C+" then the method returns 2 


		int count = 0;

		for(int i =0; i<grades.length(); i++){
			if(grades.charAt(i) == targetGrade){
			   count++;
			}
		}
		
		return count;
		
		
		
		
		
		
		
		
		
		//-----------------End here. Please do not remove this comment. Reminder: no changes outside the todo regions.
	}
	
	
}