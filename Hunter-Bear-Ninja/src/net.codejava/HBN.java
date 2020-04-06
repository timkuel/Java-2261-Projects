package net.codejava;
import java.util.Scanner;
import java.util.Random;

public class HBN {

	public static void main(String[] args) {
																	//declaring variables
		final int hunter = 0, bear = 1, ninja = 2;
		int huntcount = 0, bearcount = 0, ninjacount = 0,
			 compchoice = 0, compintervention = 3, saver = 0;
		double win = 0.0, lose = 0.0, tie = 0.0, ratio = 0.0;
		String choice = null, Exit = null;
		String [] arr = {"HUNTER", "BEAR", "NINJA"};				// creating array populated with hand choices
		
																	//String arrays containing the info for the intervention
		String[] huntintervention = {"HUNTER", "NINJA"};
		String[] bearintervention = {"BEAR", "HUNTER"};
		String[] ninjintervention = {"NINJA", "BEAR"};
		
		Random random = new Random();								//creating new random variable
			
		Scanner input = new Scanner(System.in);						//creating scanner object for input
		
		System.out.println("Hello, lets play a game.\n"
				+ "It will be a variation of rock-paper-scissors using "
				+ "Hunter, Bear, and Ninja.\n");
		
		System.out.println("Rules of the game: "
				+ "User selects one of the following choices, "
				+ "and the program,\n\twith some assistance, will try to beat"
				+ " you at the game.\n\n");

	do {																//beginning of game loop				
		System.out.println("1:Hunter who SHOOTS Bear\n2:Bear that " 
				+ "EATS Ninja\n3:Ninja who KILLS Hunter\n\n"
				+ "What would you like to pick as your 'hand'; Hunter, Bear, or Ninja?");
		choice = input.next();
		compchoice = random.nextInt(arr.length);							/*computer generates random number, 0-2 (length of array)
		 																		* which is populated with strings*/

		switch (choice.toUpperCase()) {
		case "HUNTER":														//case where user chooses hunter
			huntcount++;
			bearcount = 0;
			ninjacount = 0;
			if (huntcount >= compintervention && compchoice == bear) {		//computer intervenes if user selects hunter
					compchoice = random.nextInt(huntintervention.length);				//3 times in a row, and then re randomizes number
			}
			if(compchoice != bear && saver > 6) {
				compchoice = 1;
			}
			if(compchoice == bear) {
				System.out.println("\t\t\tHunter shoots Bear, you win!");
				win++;
				saver = 0;
			}
			else if(compchoice == ninja) {
				System.out.println("\t\t\tNinja kills Hunter, you lose!");
				lose++;
				saver += 2;
			}
			else {
				System.out.println("\t\t\tHunter greets Hunter, we have a tie!");
				tie++;
				saver++;
			}
			break;
		
		case "BEAR":														//case where user chooses bear
			bearcount++;
			huntcount = 0;
			ninjacount = 0;
			if(bearcount >= compintervention && compchoice == ninja) {		//computer intervenes if user selects bear,
				compchoice = random.nextInt(bearintervention.length);					//3 times in a row, then re randomizes number,
			}																//user can now only tie or lose.
			if(compchoice != ninja && saver > 6) {
				compchoice = 2;
			}
			if(compchoice == ninja) {
				System.out.println("\t\t\tBear eats Ninja, you win!");
				win++;
				saver = 0;
			}
			else if(compchoice == hunter) {
				System.out.println("\t\t\tHunter shoots Bear, you lose!");
				lose++;
				saver += 2;
			}
			else {
				System.out.println("\t\t\tBears collide, we have a tie!");
				tie++;
				saver++;
			}
			break;

		case "NINJA":														//case where user chooses ninja
			ninjacount++;
			huntcount = 0;
			bearcount = 0;
			if(ninjacount >= compintervention && compchoice == hunter) {	//computer intervenes if user selets ninja,
				compchoice = random.nextInt(ninjintervention.length);					//3 times in a row, re randomizes number,
			}																//so now user can only tie or lose.
			if(compchoice != hunter && saver > 6) {
				compchoice = 0;
			}
			if(compchoice == hunter) {
				System.out.println("\t\t\tNinja kills Hunter, you win!");
				win++;
				saver = 0;
			}
			else if(compchoice == bear) {
				System.out.println("\t\t\tBear eats Ninja, you lose!");
				lose++;
				saver += 2;
			}
			else {
				System.out.println("\t\tNinjas show honor towards eachother, we have a tie!");
				tie++;
				saver++;
			}	
			break;
			
		default:
			System.out.println("You did not enter a proper hand!");
			
			
		}	
		ratio = (win / lose);
		System.out.println("\n\n\t\t\tYour win/loss ratio is " + ratio + "\n\n"		//displaying win loss ratio after each 'hand'
				+ "\t\tWould you like to keep playing? <Y,N>");
		
		Exit = input.next();
	}
	while(!Exit.toUpperCase().equals("N"));
	
		input.close();
		System.out.println("\nYou won " + win + " times, lost " + lose + " times, and tied " + tie + " times!");

	}

}

