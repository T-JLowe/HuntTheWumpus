/**
 *  @author Travis Lowe & Kyle Whitaker 
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class wumpusMain
{
	/**
	 * Checks for a hazard in adjacent rooms and current room. If a hazard is in the current room the game ends.
	 * 
	 * @param rooms 			Rooms is the array containing all the rooms
	 * @param playerLocation	The players current location
	 * @return					If a hazard is in the players current room, returns true which ends the game. False doesnt end the game
	 */
	public static boolean hazardCheck(room[] rooms, int playerLocation, int playerArrows)
	{
		if(rooms[playerLocation].hasWumpus())
		{
			System.out.println("Welcome the Wumpus. You never stood a chance.");
			return true;
		}
		if(rooms[playerLocation].hasPit())
		{
			System.out.println("Your foot never makes contact with ground. Yet your whole body does a moment later. Splat.");
			return true;
		}
		if(rooms[playerLocation].hasSpiders())
		{
			System.out.println("Arachnids close in around you. The venom paralyzes and you can only hope it kills.");
			return true;
		}
		if(rooms[rooms[playerLocation].getRoom(1)-1].hasWumpus() || rooms[rooms[playerLocation].getRoom(2)-1].hasWumpus() || rooms[rooms[playerLocation].getRoom(3)-1].hasWumpus())
		{
			System.out.println("Your nose detects the smell of unwashed fur. The Wumpus is nearby.");
		}
		if(rooms[rooms[playerLocation].getRoom(1)-1].hasPit() || rooms[rooms[playerLocation].getRoom(2)-1].hasPit() || rooms[rooms[playerLocation].getRoom(3)-1].hasPit())
		{
			System.out.println("The smell of stagnant water wafts from one of the tunnels");
		}
		if(rooms[rooms[playerLocation].getRoom(1)-1].hasSpiders() || rooms[rooms[playerLocation].getRoom(2)-1].hasSpiders() || rooms[rooms[playerLocation].getRoom(3)-1].hasSpiders())
		{
			System.out.println("A salivary clicking is heard in a neighboring room.");
		}
		return false;
	}

	/**
	 * Generate an array of random numbers based on the parameters
	 * 
	 * @param x 	Int x is the number range from x to 2 (eg. if x = 15, it would be 15 to 2)
	 * @param y		Int y is how many numbers are generated
	 * @return		The array of random numbers
	 */
	public static int[] randomNumbers(int x, int y) //int x is the number range
	{
		int[] nums = new int[y];
		
		for(int l = 0; l < nums.length; l++)
		{
			nums[l] = (int)(Math.random() * (x-1) + 2);
		}
		
		for(int i = 0; i < nums.length; i++) 
		{			
			for(int o = i + 1; o < nums.length; o++)
			{
				if(nums[i] == nums[o])
				{
					return randomNumbers(x, y);
                }
			}
		}
		return nums;
	}
	
	/**
	 * Generates the cave (the cave being an array of rooms)
	 * 
	 * @return An array of room objects.
	 * @throws FileNotFoundException
	 */
	static room[] generateCave() throws FileNotFoundException
	{
		Scanner fin = new Scanner(new FileReader("cave.txt"));

		int l = fin.nextInt(); //this gets the amount of rooms
		room [] roomsT = new room[l];
	
		for(int i = 0; i < roomsT.length; i++) 
		{
			int numbers [] = new int[4];
			String desc = null;
			
			numbers[0] = fin.nextInt();
			numbers[1] = fin.nextInt();
			numbers[2] = fin.nextInt();
			numbers[3] = fin.nextInt();
			fin.nextLine(); //nextInt doesn't use the newline character, so this uses it so the next one will get the line containing the description
			desc = fin.nextLine();

			roomsT[i]= new room(numbers, desc);
		}
		
		int[] nums = randomNumbers(l, 6);
		for(int h = 0; h < 6; h++)
		{
			System.out.println(nums[h]);
		}
		roomsT[--nums[0]].setWumpus();
		roomsT[--nums[1]].setSpiders();
		roomsT[--nums[2]].setSpiders();
		roomsT[--nums[3]].setPit();
		roomsT[--nums[4]].setPit();
		roomsT[--nums[5]].setArrows(true);
		
		fin.close();
		return roomsT;
	}

	public static void main(String[] args) throws FileNotFoundException 
	{
		while(true) //This loop handles the game over choice
		{
			Scanner cmdin = new Scanner(System.in); //This scanner handles all the players choices
			room[] rooms = generateCave(); //Generates a cave and stores it in an array
			int playerLocation = 0; 
			int playerArrows = 3; 
			
			while(true) //This loop lets the player keep making choices
			{
				System.out.println("You are in room " + rooms[playerLocation].getRoom(0) + ".");  //print player location
				if(rooms[playerLocation].getArrows()) //Checks if the players room has arrows, if so their arrows are set back to 3
				{
					System.out.println("You find arrows on the floor!");
					rooms[playerLocation].setArrows(false);
					playerArrows = 3;
				}
				System.out.println("You have " + playerArrows + " arrows left."); //prints arrows left
				System.out.println(rooms[playerLocation].getDesc()); //prints room description
				if(hazardCheck(rooms, playerLocation, playerArrows)) //Check the players room for hazards
				{	
					break; //ends game if there is a hazard in the players room
				}
				System.out.println("There are 3 tunnels leading to rooms " + rooms[playerLocation].getRoom(1) + ", " + rooms[playerLocation].getRoom(2) + ", and " +  rooms[playerLocation].getRoom(3) + "."); //
				System.out.println("Move or Shoot?");
				String commandIn = cmdin.next();
				
				if(commandIn.equals("move"))
				{
					System.out.println("Move to which room?");
					//Scanner cmdin2 = new Scanner(System.in);
					int commandIn2 = cmdin.nextInt();
					
					if(commandIn2 == rooms[playerLocation].getRoom(1) || commandIn2 == rooms[playerLocation].getRoom(2) || commandIn2 == rooms[playerLocation].getRoom(3))
					{
						playerLocation = --commandIn2;
					}
					else 
					{
						System.out.println("You can't move to that room from here");
					}
				}
				else if(commandIn.equals("shoot"))
				{
					System.out.println("Shoot towards which room?");
					//Scanner cmdin2 = new Scanner(System.in);
					int commandIn2 = cmdin.nextInt();
					
					if(commandIn2 == rooms[playerLocation].getRoom(1) || commandIn2 == rooms[playerLocation].getRoom(2) || commandIn2 == rooms[playerLocation].getRoom(3))//checks if the player is shooting towards a valid room
					{
						if(playerArrows >= 1) //checks if the player has arrows to shoot
						{
							if(rooms[commandIn2-1].hasWumpus()) //checks if they hit
		                	{
		                	  System.out.println("The arrow rapidly approaches the beast's barrel chest. A booming whimper is followed by a deep thud.");
		                      System.out.println("Congrats hunter, you have slain the wumpus.");
		                      break;
		              		}
							else
			                {
			                  System.out.println("What a waste of an arrow, Robin Hood.");
			                  playerArrows--;
			                  if(playerArrows <= 0) //checks if the player is out of arrows and tells them if so
			                  {
			                	  System.out.println("You've run out of arrows!");
			                  }
			                }
						}
						else //if the player has no arrows it will tell them
						{
							System.out.println("You have no arrows!");
						}
					}
	              	else //user entered something unexpected
	                {
	                  System.out.println("The darkness must be affecting your mind. Make a rational choice.");
	                }
	
				}
				else //user entered something unexpected
				{
					System.out.println("The darkness must be affecting your mind. Make a rational choice.\n");
				}
				//cmdin.close();
			}
			System.out.println("Game Over, would you like to play again? yes or no?");
			String choice = cmdin.next();
			if(choice.equals("yes")) //restarts game
			{
				
			}
			else
			{
				cmdin.close();
				break; //ends game
			}
		}
	}
}
