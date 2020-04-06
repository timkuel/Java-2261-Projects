package pocketPet;

import java.io.Serializable;

public class Mammal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double weight;
	private int hunger;
	private String sex;
	
	public Mammal(){
		name = "";
		weight = 0;
		hunger = 0;
		sex = "";
	}
	
	/*
	 * Setter and Getter for name
	 */
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	
	
	/*
	 * Setter and Getter for weight
	 */
	public void setWeight(double weight) {this.weight = weight;}
	public double getWeight() {return weight;}
	
	
	/*
	 * Setter and Getter for hunger
	 */
	public void setHunger(int hunger) {this.hunger = hunger;}
	public int getHunger() {return hunger;}
	
	
	/*
	 * Setter and Getter for sex
	 */
	public void setSex(String sex) {this.sex = sex;}
	public String getSex() {return sex;}

}

