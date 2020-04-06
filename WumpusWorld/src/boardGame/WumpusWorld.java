package boardGame;

import java.util.Random;
import java.util.Scanner;

public class WumpusWorld {
	private Square[][] gameBoard;
	private int pX = 0;
	private int pY = 0;
	private int difficulty;
		
	
	public WumpusWorld(){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Before we begin, what difficulty would you like to play?\n"
				+ "1.) Easy (4x4)\n2.) Medium (5x5)\n3.) Hard (6x6)\n<1,2,3>? ");
		difficulty = input.nextInt();
		
		switch(difficulty) {
		case 1:
			gameBoard = new Square[4][4];
			break;
		case 2:
			gameBoard = new Square[5][5];
			break;
		case 3:
			gameBoard = new Square[6][6];
			break;
		default:
			gameBoard = new Square[4][4];
			break;
		}
		
		randomBoard(gameBoard, difficulty);
		
		breezeStenches(gameBoard);
		
		pX = gameBoard.length - 1;
		pY = 0;
		//Dropping player into gameBoard
		gameBoard[pX][pY].setSquare("Adventurer");
		
		
	}

	
	
	/*Method that will populate gameBoard with pits, wumpus, gold, and player.
	* It will start off by filling the gameBoard with "Safe" squares, then populate the array,
	* making sure not to populate board over Adventurer/Start position. It does this by plotting 
	* all the  3 Pits, Wumpus, and Gold, all of which are contained
	* inside a string array.  It then uses a for loop that will check if not empty, 
	* if not empty it de-increments the looper variable, continues back to top of for loop
	* re randomizes a location, and trys to plot the current string array location, which is 
	* also using the looper variable to determine which string to plot..
	*/
	private void randomBoard(Square[][] gameBoard, int difficulty) {
		int row, col;
		Random random = new Random();
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard[i].length; j++)
				gameBoard[i][j] = new Square();
		}
		
		gameBoard[gameBoard.length - 1][0].setSquare("Start");
		
		switch(difficulty) {
		default:
			String [] easy = {"Pit", "Pit", "Pit", "Wumpus", "Gold"};
			
			for(int i = 0; i < easy.length; i++) {
				row = random.nextInt(gameBoard.length);
				col = random.nextInt(gameBoard.length);
				
				if(gameBoard[row][col].toString().indexOf("Safe") == -1) {
					--i;
					continue;
				}
				else {
					gameBoard[row][col].setSquare((easy[i]));
				}
			}
			break;
		case 2:
			String [] medium = {"Pit", "Pit", "Pit", "Pit", "Wumpus", "Wumpus", "Gold"};
			
			for(int i = 0; i < medium.length; i++) {
				row = random.nextInt(gameBoard.length);
				col = random.nextInt(gameBoard.length);
				
				if(gameBoard[row][col].toString().indexOf("Safe") == -1) {
					--i;
					continue;
				}
				else {
					gameBoard[row][col].setSquare((medium[i]));
				}
			}
			break;
			
		case 3:
			String [] hard = {"Pit", "Pit", "Pit", "Pit", "Pit", "Pit", "Wumpus", "Wumpus", "Wumpus", "Gold", "Gold"};
			
			for(int i = 0; i < hard.length; i++) {
				row = random.nextInt(gameBoard.length);
				col = random.nextInt(gameBoard.length);
				
				if(gameBoard[row][col].toString().indexOf("Safe") == -1) {
					--i;
					continue;
				}
				else {
					gameBoard[row][col].setSquare((hard[i]));
				}
			}
			break;
		}
	}
	

	

	
	/*Method that iterates through the 2D gameBoard checking
	 * if each cell contains "Pit" or "Wumpus". It self propagates 
	 * the breezes and stenches accordingly.  It will
	 * also make sure it wont try and place the breezes and stenches 
	 * out of the current board bounds.
	 */
	private void breezeStenches(Square[][] gameBoard) {
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				
				if(gameBoard[i][j].toString().indexOf("Pit") != -1) {
					
					if(i == gameBoard.length - 1) {
						
						if(j == gameBoard[i].length - 1) {
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
						}
						
						else if(j == 0) {
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
						
						else {
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
					}
						
					else if(i == 0) {
						
						if(j == gameBoard[i].length - 1) {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
						}
						
						else if(j == 0) {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
						
						else {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
					}
							
					else if(j == gameBoard[i].length - 1) {
						
						if(i == gameBoard.length - 1) {
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
						}
						
						else if(i == 0) {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
						}
						
						else {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][--j].setSquare("Breeze");
							j++;
						}
					}
							
					else if(j == 0) {
						
						if(i == gameBoard.length - 1) {
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
						
						else if(i == 0) {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
						
						else {
							gameBoard[++i][j].setSquare("Breeze");
							i--;
							gameBoard[--i][j].setSquare("Breeze");
							i++;
							gameBoard[i][++j].setSquare("Breeze");
							j--;
						}
					}
					
					else {
						gameBoard[++i][j].setSquare("Breeze");
						i--;
						gameBoard[--i][j].setSquare("Breeze");
						i++;
						gameBoard[i][--j].setSquare("Breeze");
						j++;
						gameBoard[i][++j].setSquare("Breeze");
						j--;
					}
				}		
/*********************************************************************************************/	
				if(gameBoard[i][j].toString().indexOf("Wumpus") != -1) {
					
					if(i == gameBoard.length - 1) {
						if(j == gameBoard[i].length - 1) {
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][--j].setSquare("Stench");
							j++;
						}
						
						else if(j == 0) {
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
						
						else {
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][--j].setSquare("Stench");
							j++;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
					}
						
					else if(i == 0) {
						
						if(j == gameBoard[i].length - 1) {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[i][--j].setSquare("Stench");
							j++;
						}
						
						else if(j == 0) {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
						
						else {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[i][--j].setSquare("Stench");
							j++;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
					}
							
					else if(j == gameBoard[i].length - 1) {
						
						if(i == gameBoard.length - 1) {
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][--j].setSquare("Stench");
							j++;
						}
						
						else if(i == 0) {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[i][--j].setSquare("Stench");
							j++;
						}
						
						else {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][--j].setSquare("Stench");
							j++;
						}
					}
							
					else if(j == 0) {
						
						if(i == gameBoard.length - 1) {
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
						
						else if(i == 0) {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
						
						else {
							gameBoard[++i][j].setSquare("Stench");
							i--;
							gameBoard[--i][j].setSquare("Stench");
							i++;
							gameBoard[i][++j].setSquare("Stench");
							j--;
						}
					}
					
					else {
						gameBoard[++i][j].setSquare("Stench");
						i--;
						gameBoard[--i][j].setSquare("Stench");
						i++;
						gameBoard[i][--j].setSquare("Stench");
						j++;
						gameBoard[i][++j].setSquare("Stench");
						j--;
					}
				}
			}
		}
	}
	

	
	
	//get the X location of the array
	public int getPX() {return pX;}
	
	
	
	//get the Y location of the array
	public int getPY() {return pY;}
	
	
	
	//returns what is currently located inside of the square
	public String getCurrentSquare() {return gameBoard[pX][pY].toString();}

	
	
	
	/*Method that takes in a character value for a switch statement.
	 * The inputed character determines where the player will move, N S E W.
	 * I have it so that the player will be removed from the current square
	 * and be moved to the next desired location, so there is only one player
	 * on the board at a time.
	 */
	public void movePlayer(char move) {
		switch(Character.toUpperCase(move)) {
		case 'N':
			if(pX == 0) {
				System.out.println("\n\nYOU MOVED INTO A WALL!");
				break;
			}
			else {
				gameBoard[pX][pY].removeAdven(gameBoard[pX][pY]);;
				gameBoard[--pX][pY].setSquare("Adventurer");
			}
			break;
			
		case 'S':
			if(pX == gameBoard.length - 1) {
				System.out.println("\n\nYOU MOVED INTO A WALL!");				
				break;
			}
			else {
				gameBoard[pX][pY].removeAdven(gameBoard[pX][pY]);
				gameBoard[++pX][pY].setSquare("Adventurer");
			}
			break;
			
		case 'E':
			if(pY == gameBoard.length - 1) {
				System.out.println("\n\nYOU MOVED INTO A WALL!");
				break;
			}
			else {
				gameBoard[pX][pY].removeAdven(gameBoard[pX][pY]);
				gameBoard[pX][++pY].setSquare("Adventurer");
			}
			break;
			
		case 'W':
			if(pY == 0) {
				System.out.println("\n\nYOU MOVED INTO A WALL");
				break;
			}
			else {
				gameBoard[pX][pY].removeAdven(gameBoard[pX][pY]);
				gameBoard[pX][--pY].setSquare("Adventurer");
			}
			break;
			
		case 'C':
			displayBoard();
			break;
			
		default:
			System.out.println("\n\nYOU DID NOT ENTER A PROPER DIRECTION!");
			break;
		}
	}
	
	
	
	
	/*Method that returns a string message based off of what could be in the square
	 *  with the player, modified for bigger/harder game.
	 */
	public String getSquareMessage() {
		
		if(gameBoard[pX][pY].toString().indexOf("BreezeBreeze") != -1 && gameBoard[pX][pY].toString().indexOf("Stench") != -1) {
			return "\n\tA torrential BREEZE blows a STENCH right into your face!!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("BreezeStenchBreeze") != -1) {
			return "\n\tA torrential BREEZE blows a STENCH right into your face!!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchStench") != -1 && gameBoard[pX][pY].toString().indexOf("Breeze") != -1) {
			return "\n\tA BREEZE blows a very foul STENCH right into your face!!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchBreezeStench") != -1) {
			return "\n\tA BREEZE blows a very foul STENCH right into your face!!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("BreezeBreezeBreeze") != -1) {
			return "\n\tYoure in a virtual hurricane!! Grab onto something!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchStenchStench") != -1) {
			return "\n\tYou are surrounded by the foulest STENCH any creature has ever smelled, DEATH surrounds you!!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("BreezeBreeze") != -1) {
			return "\n\tYou are surrounded by a torrential BREEZE!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchStench") != -1) {
			return "\n\tTheres a very foul STENCH in the air, be wary of Wumpus'!!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("Breeze") != -1 && gameBoard[pX][pY].toString().indexOf("Stench") != -1) {
			return "\n\tA BREEZE is bringing a STENCH right by your nose!";
		}
		else if(gameBoard[pX][pY].toString().indexOf("Breeze") != -1) {
			return "\n\tThere is a slight BREEZE flowing past your face.";
		}
		else if(gameBoard[pX][pY].toString().indexOf("Stench") != -1) {
			return "\n\tTheres a slight STENCH in the air.";
		}
		else {
			return "\n\tYou are in a SAFE space.";
		}
		
	}
	
	
	
	
	/*Method that will check if the current square contains Wumpus.
	 * if it finds Wumpus, it will return true.
	 */
	public boolean wumpusFound() {
		if(this.gameBoard[pX][pY].toString().indexOf("Wumpus") != -1)
			return true;
		else
			return false;
	}
	
	
	
	
	/*Method that will check if the current square contains Pit.
	 * if it finds Pit, it will return true.
	 */
	public boolean pitFound() {
		if(this.gameBoard[pX][pY].toString().indexOf("Pit") != -1)
			return true;
		else
			return false;
	}
	
	
	
	
	/*Method that will check if the current square contains Gold.
	 * if it finds Gold, it will return true.
	 */
	public boolean goldFound() {
		if(this.gameBoard[pX][pY].toString().indexOf("Gold") != -1)
			return true;
		else
			return false;
	}
	
	
	
	
	/*Method that holds display messages for situations the Adventurer could find himself
	 * in when finding the gold.
	 */
	public void goldFoundMessage() {
		if(gameBoard[pX][pY].toString().indexOf("BreezeBreeze") != -1 && gameBoard[pX][pY].toString().indexOf("Stench") != -1) {
			System.out.println("With an evil Wumpus to one side, a deep and dangerous pit crawling with monsters\n"
					+ "to the other side and infront, most men would wet themselves at the thought!  But not you Adventurer!\n"
					+ "you faced these threats with courage and valor, overcoming dangers most men wouldn't see in TEN life times!\n"
					+ "Bask in the glory and gold for as long as it lasts, you've earned it!\n");
		}
		else if(gameBoard[pX][pY].toString().indexOf("BreezeStenchBreeze") != -1) {
			System.out.println("On top of Mount Wump, you, Adventurer,  had an epic showdown with the GrandMaster Wumpus!!\n"
					+ "Although the battle was long and bloody, and the Wumpus never seemed to let up, you SLAID the BEAST!\n"
					+ "Traveling to the final peak, you enter the dead GrandMasters lair, and discover he held a horde of the\n"
					+ "most valueable treasure in the land!  Be carful carrying all the gold down the mountain, one of the\n"
					+ "GrandMasters minions may be laying in the shadows waiting to pounce!\n");
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchStench") != -1 && gameBoard[pX][pY].toString().indexOf("Breeze") != -1) {
			System.out.println("With a crevase crawling with monster to one side, a Wumpus to the front and other side,\n"
					+ "you fought a glorious battle.  Dodging the Wumpuses, avoiding the Pit of Monsters, you secured that gold horde!\n"
					+ "Return to the Guild to collect your mission reward, Adventurer!!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchBreezeStench") != -1) {
			System.out.println("Surrounded by Wumpus', and a pit to your back, not once did you back down Adventurer!\n"
					+ "You stood your ground and battled off the grotesque beasts, never losing your footing.\n"
					+ "Fight, Adventurer!  Keep fighting for that glorious, glorious gold.");
		}
		else if(gameBoard[pX][pY].toString().indexOf("BreezeBreezeBreeze") != -1) {
			System.out.println("On top of Mount Wump, you sneak into the layer of the GrandMaster Wumpus.\n"
					+ "Slipping and sliding from shadow to shadow you go unseen, edging closer and closer to that\n"
					+ "gold horde.  Quick Adventurer! Quickly gather the treasures before you, before the GrandMaster comes back!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchStenchStench") != -1) {
			System.out.println("You have found yourself in an impossible situation, Adventurer, surrounded by 3 extremly dangerous Wumpus'.\n"
					+ "How you possible overcame this battle is a loss to me.  No words could describe the epic feats you performed here\n"
					+ "today to retreive that gold!  Take the gold, Adventurer, and live the life you've always dreamed!!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("Breeze") != -1 && gameBoard[pX][pY].toString().indexOf("Stench") != -1) {
			System.out.println("Fighting off a Wumpus while balancing at the edge of a pit, you showed true master-swordsmanship, Adventurer!\n"
					+ "Nobody will ever doubt you after watching that contest of skill!  Take that gold Adventurer, and keep up the hard work!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("BreezeBreeze") != -1) {
			System.out.println("Making your way across the long ARETE, you, Adventurer, are taking a leisure stroll, avoiding all the monsters down below.\n"
					+ "Your trip comes to an end, when the ARETE begins to flatten out but just before coming down off the peak,\n"
					+ "you see something sparkling sticking out of a rock wall.  You examine the shiny thing and discover a bit of gold!\n"
					+ "What a way to end your evening stroll, Adventurer, don't let the monsters below see you have it, they're attracted to gold!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("StenchStench") != -1) {
			System.out.println("Alerted by a strong foul stench, you, Adventurer, sprung to action to defend yourself from danger!\n"
					+ "With a Wumpus to each side, you begin a dance of death, slaying each Wumpus in turn!  Your blade, slicing the air as you\n"
					+ "bring it down on the last Wumpus, cracks a rock on the follow through.  Examining that patch of gravel, you discover\n"
					+ "a small bundle of gold, a fine reward for the danger you just faced!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("Breeze") != -1) {
			System.out.println("Crawling across a ledge, with a massive pit behind that containts monsters, you, Adventurer, come across a cave in a wall.\n"
					+ "Going in side, you discover a horde of Gold!  Take the gold before your discovered, the monsters are drawn to its shinyness!");
		}
		else if(gameBoard[pX][pY].toString().indexOf("Stench") != -1) {
			System.out.println("That dirty Wumpus had nothing on you Adventurer!  You slaid the beast in seconds, giving it no time to react.\n"
					+ "Now grab the gold at its feet and run, Adventurer, run before the smell of blood attracts more Wumpus'!!");
		}
		else {
			System.out.println("I dont't know what difficulties you faced along the way, but you faced no troubles in\n"
					+ "the treasure room Adventurer!  Take the Gold and run while you can!\n");
		}
	}
	
	
	
	/*Method that will display the gameBoard in a neat manner, using a
	 * String.fomat() method, with the max string length of 30.
	 */
	public void displayBoard() {
		String horizontalBorder;
		if(gameBoard.length == 6) {
			horizontalBorder = "--------------------------------        --------------------------------        --------------------------------        --------------------------------        --------------------------------        --------------------------------";
		}
		else if(gameBoard.length == 5) {
			horizontalBorder = "--------------------------------        --------------------------------        --------------------------------        --------------------------------        --------------------------------";
		}
		else {
			horizontalBorder = "--------------------------------        --------------------------------        --------------------------------        --------------------------------";
		}
		
		System.out.println("\n\n");
		
		for(int i = 0; i < gameBoard.length; i++) {
			System.out.println(horizontalBorder);
			for(int j = 0; j < gameBoard[i].length; j++) {
				System.out.print("|" + String.format("%-30s", gameBoard[i][j].toString()) + "|\t");
			}
			System.out.println("");
		}
		System.out.println(horizontalBorder + "\n");
	}

	
}
