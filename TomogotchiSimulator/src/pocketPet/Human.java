package pocketPet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Human extends Mammal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int money;
	private int totalFood;
	private int numberDogs = 0;
	
	private ArrayList<Dog> dogList = new ArrayList<>();
	private Dog dog;
	

	public Human() {
		setName("");
		setWeight(0);
		setHunger(0);
		setSex("");
	}
	
	
	/*
	 * Constructor that takes in 2 arguments
	 */
	public Human(String name, String sex) {
		Random random = new Random();
		
		setName(name);
		setSex(sex);
		setWeight(random.nextInt(200) + 75);
		setHunger(random.nextInt(100) + 1);
		totalFood = ((int)(Math.random() * 100) + 1);
		money = (random.nextInt(100) + 1);
	}
	
	
	/*
	 * Method for human to add a new dog to an arraylist of dogs
	 */
	public void addDog(String name, String sex) {
		Random random = new Random();
		
		double weight = random.nextInt(100) + 20;
		int hunger = random.nextInt(100) + 1;
		int happiness = random.nextInt(100) + 1;
		int cleanliness = random.nextInt(100) + 1;
		
		dog = new Dog(name, weight, hunger, sex, happiness, cleanliness);

		dogList.add(dog);
	}
	
	
	/*
	 * Sets number of dogs to either 1 or 2
	 */
	public void setNumberDogs() {
		Random random = new Random();
		numberDogs = (random.nextInt(2) + 1);
	}
	
	public int getNumberDogs() {return numberDogs;}

	
	/*
	 * Setter and Getter for money
	 */
	public void setMoney(int money) {this.money += money;}
	public int getMoney() {return money;}
	
	
	/*
	 * Setter and Getter for totalFood
	 */
	public void setTotalFood(int totalFood) {this.totalFood += totalFood;}
	public int getTotalFood() {return totalFood;}
	
	
	/*
	 * Method that walks all of the dogs the human owns
	 */
	public void walkDog() {
		for(int i = 0; i < dogList.size(); i++){
			int cleanliness = dogList.get(i).getCleanliness();
			int happiness = dogList.get(i).getHappiness();
			int hunger = dogList.get(i).getHunger();
		
			cleanliness -= (cleanliness * .10);
			happiness += 50;
			hunger += (hunger * .10);
	
			if(cleanliness < 0)
				cleanliness = 0;
			if(happiness > 100)
				happiness = 100;
			if(hunger > 100)
				hunger = 100;
		 
			dogList.get(i).setCleanliness(cleanliness);
			dogList.get(i).setHappiness(happiness);
			dogList.get(i).setHunger(hunger);	
		}
	}
	
	
	/*
	 * Method that feeds all the dogs the human owns
	 */
	public void feedDog() {
		totalFood -= (10 * numberDogs);
		
		for(int i = 0; i < dogList.size(); i++){
			int hunger = dogList.get(i).getHunger();
			int happiness = dogList.get(i).getHappiness();
			int cleanliness = dogList.get(i).getCleanliness();
			
			hunger -= 50;
			happiness += (happiness * .10);
			cleanliness -= (cleanliness * .10);
		
			if(hunger < 0)
				hunger = 0;
			if(happiness > 100)
				happiness = 100;
			if(cleanliness < 0)
				cleanliness = 0;

			dogList.get(i).setHunger(hunger);
			dogList.get(i).setHappiness(happiness);
			dogList.get(i).setCleanliness(cleanliness);	
		}
	}
	
	
	/*
	 * Method that bathes the dogs the human owns
	 */
	public void batheDog() {
		for(int i = 0; i < dogList.size(); i++){
			int cleanliness = dogList.get(i).getCleanliness();
			int happiness = dogList.get(i).getHappiness();
			
			cleanliness = 100;
			happiness += (happiness * .10);
		
			if(happiness > 100)
				happiness = 100;

			dogList.get(i).setCleanliness(cleanliness);
			dogList.get(i).setHappiness(happiness);
		}
	}
	
	
	/*
	 * Method that represents a human "passing time" by either working or doing nothing
	 */
	public void passTheTime() {
		this.money += 10;
		
		for(int i = 0; i < dogList.size(); i++){
			int happiness = dogList.get(i).getHappiness();
			int hunger = dogList.get(i).getHunger();
			
			happiness -= 1;
			hunger += 1;
	 	
			if(happiness < 0)
				happiness = 0;
			if(hunger > 100)
				hunger = 100;
		
			dogList.get(i).setHappiness(happiness);
			dogList.get(i).setHunger(hunger);
		}
		
	}
	
	
	/*
	 * Method that allows human to buy food at cost of money
	 */
	public void buysFood() {
		totalFood += 50;
		money -= 15;
	}
	
	
	/*
	 * If the human needs more money than passTheTime provides, he/she can work overtime
	 */
	public void overtime(int hours) {
		this.money += (5 * hours);
		for(int i = 0; i < dogList.size(); i++){
			int happiness = dogList.get(i).getHappiness();
			int hunger = dogList.get(i).getHunger();
			
			happiness -= (1 * hours);
			hunger += (1 * hours);
	 	
			if(happiness < 0)
				happiness = 0;
			if(hunger > 100)
				hunger = 100;
		
			dogList.get(i).setHappiness(happiness);
			dogList.get(i).setHunger(hunger);
		}
	}
	
	
	/*
	 * Returns a boolean value of whether the dog is loyal or not
	 */
	public boolean dogLoyal() {
		boolean bool = true;
		for(int i = 0; i < dogList.size(); i++) {
			if(dogList.get(i).getLoyalty() < 2) {
				bool = false;
				break;
			}
			else
				bool = true;
		}
		return bool;	
	}
	
	
	/*
	 * Returns the name given to the current dog
	 */
	public String getCurntDog() {
		String curntDog = null;
		for(int i = 0; i < dogList.size(); i++) {
			if(dogList.get(i).getLoyalty() < 2) {
				curntDog = dogList.get(i).getName();
				break;
			}
			else {
				curntDog = dogList.get(i).getName();
			}
		}
			return curntDog;
	}
	
	
	@Override
	public String toString() {
		return "\n\tHUMAN\nName: " + getName() + "\nSex: " + getSex() + "\nWeight: " + getWeight() 
		+ " pounds\nHunger Level: " + getHunger() + "\nTotal Food: " + totalFood + "\nTotal Money: " 
				+ money + "\nTotal Dogs: " + numberDogs + "\n" + dogList.toString().replace("[", "").replace("]", "").replace(",", "");
	}
}

