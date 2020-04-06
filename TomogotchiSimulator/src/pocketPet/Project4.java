package pocketPet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Project4 {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		String name;
		String sex;
		int choice;
		int numberHumans = 0;
		int activity = 0;
		String curntHuman = null;

		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		Human h = new Human();
		ArrayList<Human> humanList = new ArrayList<>();
		
		System.out.println("This program will simulate a human taking care of 1 to 2 dogs.  You will start off by getting the "
				+ "\nhumans name and gender, then you will get the dogs name and gender as well.  If at anypoint the dogs loyalty reaches 1"
				+ "\nand you do nothing about it, dont be suprised if your dog(s) leave you.\n");
		
		System.out.println("How many humans? ");
		numberHumans = input.nextInt();
		
		for(int humanLooper = 0; humanLooper < numberHumans; humanLooper++) {
			System.out.println("\n\nEnter humans name: ");
			name = input.next();
			
			System.out.println("Enter humans gender: \n\t1: Male\n\t2: Female\n\t3: Other");
			choice = input.nextInt();
			
			switch(choice) {
			case 1:
				sex = "Male";
				break;
			case 2:
				sex = "Female";
				break;
			default:
				sex = "Other";
				break;
			}
		
			h = new Human(name, sex);
			humanList.add(h);
			
			h.setNumberDogs();
	
			System.out.println("\n" + h.getName() + " owns " + h.getNumberDogs() + " dog(s).");
			
			
			for(int dogLooper = 0; dogLooper < h.getNumberDogs(); dogLooper++) {
				System.out.println("Enter your dogs name: ");
				name = input.next();
				
				System.out.println("Enter your dogs gender: \n\t1: Male\n\t2: Female\n\t3: Other");
				choice = input.nextInt();
				
				switch(choice) {
				case 1:
					sex = "Male";
					break;
				case 2:
					sex = "Female";
					break;
				case 3:
					sex = "Other";
					break;
				default:
					System.out.println("Enter a 1, 2, or 3 for selection.");
					--dogLooper;
					continue;
				}
				
				h.addDog(name, sex);
				
			}
			
			
			
			
			
			
			/*
			 * This block will store the initial humanList inside of a file(added after submitted)
			 */
			try(
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("humanList.dat"))
				){
					output.writeObject(humanList);
				}
			
			/*
			 * This block will store the human object inside of a file(added after submitted)
			 */
			try(
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("h.dat", true))
				){
					output.writeObject(h);
				}
			
					
		}
		
			try(
					//read values from the file
					ObjectInputStream in = new ObjectInputStream(new FileInputStream("h.dat"));
					){
					Human hu = (Human)(in.readObject());
					System.out.print(hu);
					System.out.print("\n\nDONE WITH OBJECT\n\n");
				}
			
			
			try(
					//read values from the file
					ObjectInputStream in = new ObjectInputStream(new FileInputStream("humanList.dat"));
					){
					@SuppressWarnings("unchecked")
					ArrayList<Human> hL = (ArrayList<Human>)in.readObject();
					System.out.print(hL);
					System.out.print("\n\nDoneWithLIST\n\n");
				}
		
			
			
			
			
			
		while(h.dogLoyal()) {
			h.passTheTime();
			
			for(int j = 0; j < humanList.size(); j++) {
				System.out.println(humanList.get(j).toString());
				
				System.out.println("What action would you like for " + humanList.get(j).getName() + "s dog(s)? ");
				System.out.println("\n\t1: Feed Dog(s)\n\t2: Bathe Dog(s)\n\t3: Walk Dog(s)\n\t4: Buy Dog Food\n\t5: Work Overtime\n\t6: Do Nothing\n");
				activity = input.nextInt();
				
				
				curntHuman = humanList.get(j).getName();
				
				switch(activity){
				case 1:
					int totalFood = humanList.get(j).getTotalFood();
					int totalDogs = humanList.get(j).getNumberDogs();
					
					if((totalFood -= (10 * totalDogs)) < 0) {
						System.out.println("You do not have enough dog food to feed " + totalDogs + "(s).  Buy more food first.");
						--j;
						continue;
					}
					else
						humanList.get(j).feedDog();
						break;
					
				case 2:
					humanList.get(j).batheDog();
					break;
					
				case 3:
					humanList.get(j).walkDog();
					break;
					
				case 4:
					int totalMoney = humanList.get(j).getMoney();
					
					if((totalMoney -= 10) < 0) {
						System.out.println("You do not have enough money to buy food, work extra hours.");
						--j;
						continue;
					}
					else
						humanList.get(j).buysFood();
						break;
						
				case 5:
					int hours;
					
					System.out.println("How many extra hours would you like to work($5/h)?");
					hours = input.nextInt();
					
					humanList.get(j).overtime(hours);
					break;
					
				default:
					System.out.println("You did nothing with " + humanList.get(j).getName() + "s dog(s).  To do an activity\nwith the dogs\n");
					break;	
				}
			}
		}
		
		System.out.println(curntHuman + " has lost the trust of " + h.getCurntDog() + ".  The pack has been disbanded from having an untrustworthy leader.\n");
	}
}

