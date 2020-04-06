package pocketPet;

import java.io.Serializable;

public class Dog extends Mammal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int happiness = 0;
	private int cleanliness = 0;
	private int loyalty = 1;
	
	
	public Dog() {
		setName("");
		setWeight(0);
		setHunger(0);
		setSex("");
	}

	
	/*
	 * Constructor that takes in 6 arguments
	 */
	public Dog(String name, double weight, int hunger, String sex, int happiness, int cleanliness) {
		setName(name);
		setWeight(weight);
		setHunger(hunger);
		setSex(sex);
		this.happiness = happiness;
		this.cleanliness = cleanliness;
	}

	
	/*
	 * Sets happiness and changed loyalty any time happiness is changed
	 */
	public void setHappiness(int happiness) {
		this.happiness = happiness;
		setLoyalty();
		}
	
	public int getHappiness() {return happiness;}
	
	
	public void setCleanliness(int cleanliness) {this.cleanliness = cleanliness;}
	public int getCleanliness() {return cleanliness;}

	
	/*
	 * Sets the loyalty of the dog
	 */
	private void setLoyalty() {this.loyalty = Math.max(getHappiness(), (100 - getHunger()));}
	
	
	public int getLoyalty() {
		setLoyalty();
		return loyalty;
		}
	
	
	@Override
	public String toString() {
		return "\n\tDOG\nName: " + getName() + "\nSex: " + getSex() + "\nWeight: " + getWeight() 
		+ " pounds\nHunger Level: " + getHunger() + "\nHappiness Level: " + happiness + "\nCleanliness Level: " 
				+ cleanliness + "\nLoyalty Level: " + getLoyalty();
	}
}

