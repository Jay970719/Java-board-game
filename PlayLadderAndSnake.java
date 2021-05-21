//-------------------------------------------------------------
//Assignment #1
//Part1
//Written by: Youngjae Kim, 40169282
//This is the ladder and snake board game which has a rule that 
//when the user meet the ladder, it can go up to the higher place
//and when they meet the snake, they are going down to the lower place.
//The player who reaches 100th box wins the game. 
//Players who have the highest number of dice will start the game.
//-------------------------------------------------------------

import java.util.Scanner;
/**
 * Youngjae Kim,40169282
 * Comp249	
 * Assignment#1
 * Due 11:59pm, Monday,February 8, 2021
 */
public class PlayLadderAndSnake {
    /**
     * This is a main play method to run this game 
     */
	public static void main(String[] args) {
	  System.out.println("Hello!! Welcome to Ladder and snake game!!"+"\n");
      System.out.println("Enter the # of players for your game – Number must be between 2 and 4 inclusively: ");
      Scanner jay = new Scanner(System.in);
       int number,count=0;
       
       do {number = jay.nextInt();    //users can input the number of players 
           count++;                   //whenever they input, we add 1 to the number
           if(number>4 || number<2)   //if the number they input is outside the boundaries
        	 {if(count<=2)            //different outputs depend on the number of attempts users tried
    		     {System.out.println("Bad Attempt "+ count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively:");
    		      continue;}
    	     if(count==3)
    	         {System.out.println("Bad Attempt "+ count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively. This is your last attempt:");
    	          continue;}
    	     if(count==4)
    	    	 {System.out.println("Bad Attempt "+ count + "! You have exhausted all your chances. Program will terminate! ");
    	    	 System.exit(0);}}
    	 }while(number>4 || number<2);  //repeat the loop while the input is not between 2 and 4
      
      LadderAndSnake obj = new LadderAndSnake(number);   //calling the LadderAndSnake class to run the game. 
                                                         //This "number" will be numberOfPlayers.
      

	}

}