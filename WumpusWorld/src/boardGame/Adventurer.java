package boardGame;
import java.util.Scanner;


public class Adventurer {
	public static void main(String[] args) {
		char looper = 'Y', move;
					
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Hello!  Welcome to the game Wumpus World.  You are a fellow adventurer on a 4x4 gameboard.\n"
				+ "In this gameboard, there are 3 Pits randomly placed, each pit is surrounded by breezes\n(one to the north, south, east, and west),"
				+ "and there is also a Wumpus, an evil creature that takes the trait of your most feared thing.\nIf you are near a Wumpus "
				+ "you should smell a stench eminating around it (just like the pit breezes).\nYour goal is to navigate this gameboard, only knowing what"
				+ "is in your current square, and to find the randomly placed gold.\nDONT FALL INTO ANY PITS, and "
				+ "DONT GET EATEN BY THE WUMPUS!\n\n");
		
		
		 while(Character.toUpperCase(looper) == 'Y'){
			WumpusWorld ww = new WumpusWorld();
			
			while(!ww.wumpusFound() ^ !ww.pitFound() ^ !ww.goldFound()) {
				
				System.out.println("\n\nYou are located at [" + ww.getPX() + "] [" + ww.getPY() + "] on the board.  "
						+ "This square contains " + ww.getCurrentSquare() + "\n" + ww.getSquareMessage() + "\n\n"
						+ "Enter N to move NORTH, S to move SOUTH, E to move EAST, "
						+ "and W to move WEST.\nPlease enter the direction you would "
						+ "like to move (entering C allows for a peek at the board): ");
				
				move = input.next().charAt(0);
				
				ww.movePlayer(move);
				
			}
			
			if(ww.wumpusFound()) {
				ww.displayBoard();
				System.out.println("\nYou got eaten by the WUMPUS!  Good luck next time Adventurer\n");
			}
			else if(ww.pitFound()) {
				ww.displayBoard();
				System.out.println("\nYou fell into a treacherous PIT and died! So long Adventurer\n");
			}
			else {
				ww.displayBoard();
				ww.goldFoundMessage();
			}
			
			System.out.println("\nWould you like to play another game?<Y,N> ");
			
			looper = input.next().charAt(0);
		}
		
		System.out.println("\nCome back soon Adventurer, theres more undiscoverd treasures to plunder\n"
				+ "from the hands of them dirty Wumpus'!  Just remember to keep avoiding dastardly Pits!");
	
	}
}

