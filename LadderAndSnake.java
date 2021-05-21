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
	public class LadderAndSnake {
	  /**
	   * board array which includes 1~100 spaces (I put it as 101 because I don't want to start from 0)
	   */
	  private int [] board = new int [101];
	  private int numberOfPlayers;
	  /**
	   * array which has dice values
	   */
	  private int [] dicevalue; 
	  /**
	   * array which includes player's name
	   */
	  private String []players;
	  /**
	   * array which includes the players' location on the board.
	   */
	  private int [] location;
	  /**
	   * String that player presses to continue
	   */
	  private String turn;
	  /**
	   * boolean value which is used to finish this game.
	   */
	  private boolean finsih= true;   
	  /**
	   * dice value
	   */
	  private int dice;
	  /**
	   * boolean which indicates occurrence of duplicates.
	   */
	  private boolean has_duplicate;                 //declare instant variables
	  
	  /**
	   * constructor
	   * @param k which is a number of players that user input
	   */
	   public LadderAndSnake(int k) {          //constructor 
		   this.numberOfPlayers= k;            //int k is the value that users entered
		   dicevalue =new int[(numberOfPlayers+1)];
		   players = new String[(numberOfPlayers+1)];
		   location  = new int[(numberOfPlayers+1)]; //declare the length of the arrays (I put +1 because I used (i=1;i<=numberOfPlayer;i++)
		   
	   
		   
		   for(int i =1; i<=numberOfPlayers;i++)
		   {System.out.print("Enter the player "+ i + "'s name (donnot put space between):");
		    Scanner u = new Scanner(System.in);
		    String name = u.next();
			   players[i]= name;}     //Now players can enter their name!
		   
		   for(int i =1; i<=numberOfPlayers;i++)
			  location[i]= 0;  //all players start from "0"
		    play();   //I set the play method private and put it here to make it more secure and able to be operated in another class.
	   }
	   
	  
	  /**
	   *  method for flip dice
	   * @return the random dice value
	   */
	   private int flipDice() {       //method to get random number between 1~6.
		   this.dice =(int)(Math.random()*6 +1);
		   return this.dice;
	   } 
	   
	   /**
	    * main play method to run the game.
	    */
	  private void play() {
		   System.out.println("Game is Played by "+ numberOfPlayers + " players");
		   System.out.println("");
		   System.out.println("Now deciding which player will start playing;");  //decide who will be the first
		   int [] order = new int[numberOfPlayers];
		   
		   for(int i =1; i<=numberOfPlayers;i++)
		   { int temp = flipDice();    //call flipDice method.
			 dicevalue[i] = temp;      //every player has their own dice value randomly.
			 System.out.println(players[i]+" got a dice value of "+ dicevalue[i]);}
		 
		        //call this method to consider if there is duplicated value or not.
		  
		    order= getOrderOfRanking(dicevalue, players);
		    System.out.println("");
		    System.out.println("Reached final decision on order of playing: ");
		      for(int i=1; i<=numberOfPlayers;i++)
		    	{System.out.println("["+i+" P: "+players[order[i]]+"]");}       //order decided.
		    System.out.println(""); 
		    System.out.println("");
		    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<Game Start>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    
		   
		      
		  do { for(int i =1; i<=numberOfPlayers;i++)
			   { dicevalue[i] = flipDice();    //get a random number(1~6)
			    for( int k= 1;k<=100;k++)
			        board[k]= k; //we have to write this here because when we don't write this, the changed value after board(int i) method
			                     //will remain still.
				 System.out.print(players[order[i]]+" got a dice value of "+ dicevalue[i]+ "; ");
				 int place= location[i]+dicevalue[i];       //we have to add two value, which are current location and new dice value.
				 if(place>100)
					 {System.out.println("Your location is over location "+ (place-100)+ " more, so it will be deducted from 100: Your location is now "
							             + (100-(place-100)));
					 place= 100-(place-100);}  // if our location is over 100th, then we have to subtract the amount exceeds 100 from 100.
				 board(place);          //then put it into the board method which works on ladder and snakes.
				 location[i]=board[place];   //location is set now  
			     System.out.println("Press any small letter key to continue the next turn.");
		         Scanner jj = new Scanner(System.in);
			     turn = jj.next();
			     if( turn != "E")
			    	  continue;} //if they press any lower case letter, next turn.
		         System.out.println("----------------------------------------------------------------------");
		         System.out.println("Game not over; flipping again");
		          }while(finish());  //this method is repeated until some player reaches 100.
		    
		  
		   
	   }	  


	  /**
	   * method to get the maximum index which is not duplicated. 
	   * @param arr_main  which is the array of dice value.
	   * @param ignore_val which distinguishes the used number and not. 
	   * @param players which is the array of names of players
	   * @return the maximum index
	   */
	  private int getMaxIndex(int[] arr_main, int ignore_val,String [] players) { 
		    
			int[] arr = arr_main.clone();  // make a clone method 
			int index = -1;
	       
			for(int i = 1; i < arr.length; i++) {  
				 has_duplicate = false;
				if(arr[i] != ignore_val)    //it should be different from -2 because that value is for already chosen one.
					{for (int j = i+1; j < arr.length; j++) 
						{if(arr[i] == arr[j])   
							{arr[j] = -1;              //when there is duplicated one, then set it as -1.
							has_duplicate = true;}     //turn has_duplicate value to true. 
						
						}
					 if(has_duplicate) 
						 {arr[i] = -1;             
					     break;}}          }  
			
			
			
			int temp = 0;            //to distinguish between the duplicated one and not.
			for(int i = 1; i < arr.length; i++) 
				if(arr[i] > temp)          // this means we only choose not duplicated ones. 
					{temp = arr[i];       //compare the values, and get the index of the highest value. 
					index = i;}
				
		    return index;    
		}
		
		/**
		 * check everything is ordered. 
		 * @param arr_main call the array which is rank.
		 * @return boolean all_positive which indicates there is duplicated one or not.
		 */
		private boolean allPositve(int[] arr_main) {
			
			boolean all_positive = true;
			for(int i = 1; i < arr_main.length; i++) {
				if(arr_main[i] < 0) {                     //when they are still duplicate, all_positive remains false. 
					all_positive = false;
					break;}}
				
			return all_positive;
			
		}
		
		
		/**
		 * method for getting a order
		 * @param arr_main which is the dicevalue array
		 * @param players which includes the name of the players
		 * @return the final order of players
		 */
		private int[] getOrderOfRanking(int[] arr_main, String [] players) {
			int[] arr = arr_main.clone();       //array arr is same as dice value array.
			int[] rank = new int[arr.length];
			
			//set all the elements in array to -1
			for(int i = 1; i < arr.length; i++) 
				{rank[i] = -1;}  
			
			
			int track = 1;  //index of rank starts from 1
			for(;;) {
				if(allPositve(rank))   //when every rank value is changed to real index, it stops.
					break;
				
				int index = getMaxIndex(arr, -2, players);   //call max index
				
				if (index != -1) {         
					rank[track] = index;
					arr[index] = -2; // we don't have to care this anymore because it's already ordered
					track++;}         
					
			   else {
					// Now lets check for the occurrence of duplicates
					int[] duplicate_index = new int[arr.length];
					
					//set all the elements in array to -1
					for(int i = 1; i < arr.length; i++) {
						duplicate_index[i] = -1;
					}
					
					int parent = 0;   //just temporary
					int counter = 1;  //index of duplicate_index starts from 1
					
					boolean has_atleast_one_duplicate = false;
					
					for(int i = 1; i < arr.length; i++) {
						parent = i;                                   //set parent to i
						if(arr[i] != -2)                             //check whether it is not -2 which means its already ordered
						  {for(int j = i+1; j < arr.length; j++) {
							if(arr[i] == arr[j]) {
								has_atleast_one_duplicate = true;
								duplicate_index[counter] = j;         //if they are duplicate value, set into duplicate_index
	                            counter++;}}
							}
						if(has_atleast_one_duplicate)
						break;
					}
					
					if(has_atleast_one_duplicate)
						duplicate_index[counter] = parent;
					
					System.out.println("There is a tie between: "); //print the players who are tied.
					for(int i: duplicate_index) //for all value in duplicate_index
					{ if(i != -1&& players[i]!=null)   //we should put players[i]!= null because there is players[0] which is null.
						System.out.println(players[i]+" ");}
					
					
					
					for(int i: duplicate_index) //for all value in duplicate_index
					{ if(i != -1&& players[i]!=null)
							{int rand_val = (int) (Math.random() * 6) ;  //give a new value
							  arr[i]= rand_val+1;        //this is same as arr[duplicate_index[i]]
							  System.out.println("\""+players[i]+"\""+ "got the new dice value of: "+ (arr[i]));
							  }}
					
				}
			   }
			
			return rank;
		}

		
		
		/**
		 * method of special rules of board including snakes and ladders
		 * @param i is the current location in the board
		 */
	    private void board(int i) {
			 
			     if(i==4)               //case of ladder
			         {board[4]=14;
			          System.out.println("gone to square " + i + " then up to square "+ board[4]);
			         }
			     else if(i==9)
			         {board[9]=31;
			          System.out.println("gone to square " + i + " then up to square "+ board[9]);
			          }
			     else if(i==1)
		             {board[1]=38;
		              System.out.println("gone to square " + i + " then up to square "+ board[1]);
		          }  
			     else if(i==21)
			         {board[21]=42;
			          System.out.println("gone to square " + i + " then up to square "+ board[21]);
			         }
			     else if(i==28)
			         {board[28]=84;
			          System.out.println("gone to square " + i + " then up to square "+ board[28]);
			          }
			     else if(i==36)
			         {board[36]=44;
			          System.out.println("gone to square " + i + " then up to square "+ board[36]);
			         }
			     else if(i==51)
			         {board[51]=67;
			          System.out.println("gone to square " + i + " then up to square "+ board[51]);
			          }
			     else if(i==71)
			         {board[71]=91;
			          System.out.println("gone to square " + i + " then up to square "+ board[71]);
			         } 
			     else if(i==80)
			         {board[80]=100;
			          System.out.println("gone to square " + i + " then up to square "+ board[80]);
			          System.out.println("Finally reached 100!!!Congratulation!!!You won!!");
			          System.exit(0);
			         }
			     else if(i==16)   //case of snakes
			         {board[16]=6;
			          System.out.println("gone to square " + i + " then down to square "+ board[16]);
			          }
			     else if(i==48)
			         {board[48]=30;
			          System.out.println("gone to square " + i + " then down to square "+ board[48]);
			          }
			     else if(i==64)   
			         {board[64]=60;
			          System.out.println("gone to square " + i + " then down to square "+ board[64]);
			          }
			     else if(i==79)   
			         {board[79]=19;
			          System.out.println("gone to square " + i + " then down to square "+ board[79]);
			         }
			     else if(i==93)   
			         {board[93]=68;
			          System.out.println("gone to square " + i + " then down to square "+ board[93]);
			          }
			     else if(i==95)   
			         {board[95]=24;
			          System.out.println("gone to square " + i + " then down to square "+ board[95]);
			          }
			     else if(i==97)   
			         {board[97]=76;
			          System.out.println("gone to square " + i + " then down to square "+ board[97]);
			          }
			     else if(i==98)   
			         {board[98]=78;
			          System.out.println("gone to square " + i + " then down to square "+ board[98]);
			         }
			     else if(i==100)
			         {System.out.println("Finally reached 100!!!Congratulation!!!You won!!");
			          System.exit(0);}
			     else System.out.println("now in square "+ board[i]);
			    }
		   	  
		 /**
		  * method to check somebody finished the game  
		  * @return boolean value which returns false when the game is over.
		  */
		 private boolean finish() {
			 for(int i =1; i<=numberOfPlayers;i++) 
				if(location[i]==100)
					this.finsih= false;
				return this.finsih;  //this game is finished until somebody reaches 100.
		 }
		 
		 
		   
	   
	   
	}

